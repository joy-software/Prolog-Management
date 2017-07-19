/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionprolog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import jpl.Query;

/**
 * FXML Controller class
 *
 * @author NDJAMA
 */
public class GestionController implements Initializable {

    
    
    @FXML
    private TableView<Type> tabletype;
    @FXML
    private TableColumn<Type,String> notype;
    @FXML
    private TableColumn<Type,String> nomtype;
    
    public static ObservableList<Type> typeData = FXCollections.observableArrayList();
    public static ObservableList<Appareil> appData = FXCollections.observableArrayList();
    public static ObservableList<Facture> factData = FXCollections.observableArrayList();
    public static ObservableList<DetailFacture> detfactData = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Appareil> tableappareil;
    @FXML
    private TableColumn<Appareil,String> noApp;
    @FXML
    private TableColumn<Appareil,String> notypeApp;
    @FXML
    private TableColumn<Appareil,String> nomApp;
    @FXML
    private TableColumn<Appareil,String> prixApp;
    
    @FXML
    private TableView<Facture> tablefacture;
    @FXML
    private TableColumn<Facture,String> nofacture;
    @FXML
    private TableColumn<Facture,String> montantfacture;
    
    @FXML
    private TableView<DetailFacture> tabledetfacture;
    @FXML
    private TableColumn<DetailFacture,String> nodetfacture;
    @FXML
    private TableColumn<DetailFacture,String> noappfact;
    
    @FXML
    private TextField textsearchType;
    @FXML
    private TextField textnumType;
    @FXML
    private TextField texttypType;
    
    @FXML
    private TextField textsearchApp;
    @FXML
    private TextField textnumApp;
    @FXML
    private TextField textnotypApp;
    @FXML
    private TextField textnomsApp;
    @FXML
    private TextField textprrixApp;
    
    @FXML
    private TextField textsearchFact;
    @FXML
    private TextField textnumFact;
    @FXML
    private TextField textmontFact;
    
    @FXML
    private TextField textsearchDetFact;
    @FXML
    private TextField textnumDetFact;
    @FXML
    private TextField textnumappDetFact;
    
    @FXML
    private Button butmodtype;
    @FXML
    private Button butmodApp;
    @FXML
    private Button butmodFact;
    @FXML
    private Button butmodDetFact;
    @FXML
    private Button butsuptype;
    @FXML
    private Button butsupApp;
    @FXML
    private Button butsupFact;
    @FXML
    private Button butsupDetFact;
    
    @FXML
    private HBox boxtyp;
    @FXML
    private HBox boxapp;
    @FXML
    private HBox boxfac;
    @FXML
    private HBox boxdetfac;
    
    @FXML
    private AnchorPane panetyp;
    @FXML
    private AnchorPane panetapp;
    @FXML
    private AnchorPane panefac;
    @FXML
    private AnchorPane panedetfac;
    
    private Type selecttyp;
    private Appareil selectapp;
    private Facture selectfac;
    private DetailFacture selectdetfac;
    
    private GestionProlog myapp;
    private boolean editapp = false;
    private boolean editdetfac = false;
    private boolean editfac = false;
    private boolean edittyp = false;
    public static ArrayList <String> listType = new ArrayList<>();
    public static ArrayList <String> listApp = new ArrayList<>();
    public static ArrayList <String> listFact = new ArrayList<>();
    public static ArrayList <String> listDetFact = new ArrayList<>();
    public static String bdpath = "bd.pl";
    
    
    
    /**
     * permet de remplir nos Data
     */
    public static void fillData()
    {   
        
        Query query = new Query("consult('"+bdpath+"').");
        query.hasSolution();
        
        listApp.add("% la table Appareil d'arité 4");
        listApp.add(":-dynamic(appareil/4).");
        
        listType.add("% la table type d'arité 2");
        listType.add(":-dynamic(type/2).");
        
        listFact.add("% la table facture d'arité 2");
        listFact.add(":-dynamic(facture/2).");
        
        listDetFact.add("%la table DetailFacture d'arité 2");
        listDetFact.add(":-dynamic(detailFacture/2).");

        //on recupere les types
        try{
        	String t4 = "type(X,Y).";
                        Type temp = null;
			Query q4 = new Query(t4);
    			Hashtable[] s4 = q4.allSolutions();
                        for(int i=0; i<s4.length; i++){
                        temp = new Type((""+s4[i].get("X")).replaceAll("'", ""), (""+s4[i].get("Y")).replaceAll("'", ""));
                        typeData.add(temp);
                        listType.add("type(" +temp.getNotype()+","+ "'"+temp.getNomtyme()+"'"+ ").");}
                        
                String t5 = "facture(X,Y).";
                        Facture temp1 = null;
			q4 = new Query(t5);
			s4 = q4.allSolutions();
                        for(int i=0; i<s4.length; i++){
                        temp1 = new Facture((""+s4[i].get("X")).replaceAll("'", ""), (""+s4[i].get("Y")).replaceAll("'", ""));  
                        factData.add(temp1);
    			listFact.add("facture(" +temp1.getNoFact()+","+ temp1.getMontant()+ ").");}
                        
                t5 = "detailFacture(X,Y).";
                        DetailFacture temp2 = null;
			q4 = new Query(t5);
			s4 = q4.allSolutions();
                        for(int i=0; i<s4.length; i++){
                        temp2 = new DetailFacture((""+s4[i].get("X")).replaceAll("'", ""), (""+s4[i].get("Y")).replaceAll("'", ""));
                        detfactData.add(temp2);
                        listDetFact.add("detailFacture(" +temp2.getNoFact()+","+ temp2.getNoApp() + ").");}
                        
                t5 = "appareil(X,Y,Z,A).";
                        Appareil temp3 = null;
			q4 = new Query(t5);
			s4 = q4.allSolutions();
                        for(int i=0; i<s4.length; i++){
                        temp3 = new Appareil((""+s4[i].get("X")).replaceAll("'", ""), (""+s4[i].get("Y")).replaceAll("'", ""),
                                (""+s4[i].get("Z")).replaceAll("'", ""), (""+s4[i].get("A")).replaceAll("'", ""));
                        appData.add(temp3);
                        listApp.add("appareil(" + temp3.getNoApp()+","
                    + temp3.getNoType()+"," + "'"+temp3.getNomApp()+"'"+
                   ","+ temp3.getPrixApp() + ").");}
        	}catch(Exception e){
                    GestionController.printStrace(e);
        }
    }
    
    
    /**
     * constructeur de la classe
     */
    public GestionController() 
    {
        try {
            System.setOut(new PrintStream(new File("text1.txt")));
            System.setOut(new PrintStream(new File("error.txt")));
        } catch (FileNotFoundException ex) {
        GestionController.printStrace(ex);}
        
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param app
     */
    public void setMainApp(GestionProlog app)
    {
        myapp = app;
        fillData();
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // les Types
        notype.setCellValueFactory(cellvalue -> cellvalue.getValue().notypeProperty());
        nomtype.setCellValueFactory(cellvalue -> cellvalue.getValue().nomtypeProperty());
        
        FilteredList<Type> filteredDataType = new FilteredList<>(typeData, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        textsearchType.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataType.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                return choix(person,lowerCaseFilter);
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Type> sortedData = new SortedList<>(filteredDataType);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tabletype.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tabletype.setItems(sortedData);
        
        tabletype.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showtype(newValue,tabletype.getSelectionModel().getSelectedIndex()));
        
        
        
        //les appareils
        noApp.setCellValueFactory(cellvalue -> cellvalue.getValue().noAppProperty());
        nomApp.setCellValueFactory(cellvalue -> cellvalue.getValue().nomAppProperty());
        notypeApp.setCellValueFactory(cellvalue -> cellvalue.getValue().noTypeProperty());
        prixApp.setCellValueFactory(cellvalue -> cellvalue.getValue().prixAppProperty());
        
        FilteredList<Appareil> filteredDataApp = new FilteredList<>(appData, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        textsearchApp.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataApp.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                return choix1(person,lowerCaseFilter);
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Appareil> sortedData1 = new SortedList<>(filteredDataApp);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData1.comparatorProperty().bind(tableappareil.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableappareil.setItems(sortedData1);
        
        tableappareil.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showApp(newValue,tableappareil.getSelectionModel().getSelectedIndex()));
        
        
        //les factures
        // les Types
        nofacture.setCellValueFactory(cellvalue -> cellvalue.getValue().noFactProperty());
        montantfacture.setCellValueFactory(cellvalue -> cellvalue.getValue().montantProperty());
        
        FilteredList<Facture> filteredDataFact = new FilteredList<>(factData, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        textsearchFact.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDataFact.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                return choix2(person,lowerCaseFilter);
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Facture> sortedData2 = new SortedList<>(filteredDataFact);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData2.comparatorProperty().bind(tablefacture.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tablefacture.setItems(sortedData2);
        
        tablefacture.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showfact(newValue,tabletype.getSelectionModel().getSelectedIndex()));
        
    
        
        // les details factures
        nodetfacture.setCellValueFactory(cellvalue -> cellvalue.getValue().noFactProperty());
        noappfact.setCellValueFactory(cellvalue -> cellvalue.getValue().noAppProperty());
        
        FilteredList<DetailFacture> filteredDatadet = new FilteredList<>(detfactData, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        textsearchDetFact.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredDatadet.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                return choix3(person,lowerCaseFilter);
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<DetailFacture> sortedData3 = new SortedList<>(filteredDatadet);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData3.comparatorProperty().bind(tabledetfacture.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tabledetfacture.setItems(sortedData3);
        
        tabledetfacture.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showdetfact(newValue,tabletype.getSelectionModel().getSelectedIndex()));
    
    }    

    private boolean choix(Type person, String lowerCaseFilter) {
       boolean result =false; 
        if(person.getNotype() == null)
          {
                result =  ("").toLowerCase().contains(lowerCaseFilter);
                            
           }
        else
            {
              if (person.getNotype().toLowerCase().contains(lowerCaseFilter)) {
                    result = true; // Filter matches first name.
             }}
        
        return result;
    }

    private void showtype(Type newValue, int selectedIndex) {
    
        if(selectedIndex >= 0)
        {
            selecttyp = newValue;
            butmodtype.setDisable(false);
            butsuptype.setDisable(false);
            
            if(newValue != null)
            {
                textnumType.setText(newValue.getNotype());
                texttypType.setText(newValue.getNomtyme());
            }
            else
            {
                textnumType.setText(null);
                texttypType.setText(null);
            }
            
        }
        
    }
    
    
    /**
	 * ecrire dans notre fichier log
	 * @param e
	 */
        public static  void printStrace(Exception e)
  {
	   
	    String nom = "log.del";
	    
	    PrintWriter fich = null;

	    try {
			fich = new PrintWriter(new BufferedWriter(new FileWriter(nom, true)));
			//true c'est elle qui permet d'écrire à la suite des donnée enregistrer et non de les remplacé 
		} catch (IOException e1) {
		} 
	    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy  HH:mm:ss");
	    String dateStr = sdf.format(new Date());
	    fich.println(dateStr + " " + getStackTrace(e));
	    fich.println();
	    fich.println();
	    fich.close();
  }
    
   /**
	 * Cette méthode permet de recuperer une erreur
	 * et de l'afficher
	 * @param e
	 * @return
	 */
           public static String getStackTrace( Exception e ) { 
	    Writer result = new StringWriter(); 
	    PrintWriter printWriter = new PrintWriter( result ); 
	    e.printStackTrace( printWriter ); 
	    return result.toString(); 
	  }

    private boolean choix1(Appareil person, String lowerCaseFilter) {
         boolean result =false; 
        if(person.getNoApp() == null)
          {
                result =  ("").toLowerCase().contains(lowerCaseFilter);
                            
           }
        else
            {
              if (person.getNoApp().toLowerCase().contains(lowerCaseFilter)) {
                    result = true; // Filter matches first name.
             }}
        
        return result;
    }

    private void showApp(Appareil newValue, int selectedIndex) {
        if(selectedIndex >= 0)
        {
            selectapp = newValue;
            butmodApp.setDisable(false);
            butsupApp.setDisable(false);
        
             if(newValue != null)
            {
                textnotypApp.setText(newValue.getNoType());
                textnumApp.setText(newValue.getNoApp());
                textprrixApp.setText(newValue.getPrixApp());
                textnomsApp.setText(newValue.getNomApp());
            }
            else
            {
                textnotypApp.setText(null);
                textnumApp.setText(null);
                textprrixApp.setText(null);
                textnomsApp.setText(null);
            }
        }
    }

    private boolean choix2(Facture person, String lowerCaseFilter) {
     boolean result =false; 
        if(person.getNoFact() == null)
          {
                result =  ("").toLowerCase().contains(lowerCaseFilter);
                            
           }
        else
            {
              if (person.getNoFact().toLowerCase().contains(lowerCaseFilter)) {
                    result = true; // Filter matches first name.
             }}
        
        return result;
    }

    
    private void showfact(Facture newValue, int selectedIndex) {
    
        if(selectedIndex >= 0)
        {
            selectfac = newValue;
            butmodFact.setDisable(false);
            butsupFact.setDisable(false);
           
            if(newValue != null)
            {
                textnumFact.setText(newValue.getNoFact());
                textmontFact.setText(newValue.getMontant());
            }
            else
            {
                textnumFact.setText(null);
                textmontFact.setText(null);
            }
        }
    }

    private boolean choix3(DetailFacture person, String lowerCaseFilter) {
    boolean result =false; 
        if(person.getNoFact() == null)
          {
                result =  ("").toLowerCase().contains(lowerCaseFilter);
                            
           }
        else
            {
              if (person.getNoFact().toLowerCase().contains(lowerCaseFilter)) {
                    result = true; // Filter matches first name.
             }}
        
        return result;
    }

    
    private void showdetfact(DetailFacture newValue, int selectedIndex) {
        if(selectedIndex >= 0)
        {
            selectdetfac = newValue;
            butmodDetFact.setDisable(false);
            butsupDetFact.setDisable(false);
            
            if(newValue != null)
            {
                textnumDetFact.setText(newValue.getNoFact());
                textnumappDetFact.setText(newValue.getNoApp());
            }
            else
            {
               textnumDetFact.setText(null);
               textnumappDetFact.setText(null);
            }
            
        }
    }
    
    
    @FXML
    private void launchaddtype()
    {
        panetyp.setDisable(true);
        boxtyp.setVisible(true);
        edittyp = false;
        textnumType.setText(null);
        texttypType.setText(null);
    }
    
    @FXML
    private void launchmodtype()
    {
        panetyp.setDisable(true);
        boxtyp.setVisible(true);
        edittyp = true;
        textnumType.setText(selecttyp.getNotype());
        texttypType.setText(selecttyp.getNomtyme());
    }
    
    
    @FXML
    private void handleOKtype()
    {
        if(isInputValid2())
        {
        
            Type temp,tem;
            
            temp = new Type(null,null);        
            temp.setNotype(textnumType.getText());
            temp.setNomtyme(texttypType.getText());
            
            if(edittyp)
            {
               tem = selecttyp;
               listType.remove("type(" +tem.getNotype()+","+ "'"+tem.getNomtyme()+"'"+ ").");
               supprimer("type(" +tem.getNotype()+","+ "'"+tem.getNomtyme()+"'"+ ")");
               typeData.remove(tem);  
            }
        
            String interclause = "type(" +temp.getNotype()+","+ "'"+temp.getNomtyme()+"'"+ ")";
            
            enregistrer(interclause);
            
            listType.add("type(" +temp.getNotype()+","+ "'"+temp.getNomtyme()+"'"+ ").");
            
            typeData.add(temp);
        
            handlecanceltype();
        }
    }
    
    @FXML
    private void handlecanceltype()
    {
        boxtyp.setVisible(false);
        panetyp.setDisable(false);
        textnumType.setText(null);
        texttypType.setText(null);
    }
    
    @FXML
    private void launchaddapp()
    {
        panetapp.setDisable(true);
        boxapp.setVisible(true);
        editapp = false;
        textnotypApp.setText(null);
        textnumApp.setText(null);
        textprrixApp.setText(null);
        textnomsApp.setText(null);
    }
    
    @FXML
    private void launchmodapp()
    {
        panetapp.setDisable(true);
        boxapp.setVisible(true);
        editapp = true;
        textnotypApp.setText(selectapp.getNoType());
        textnumApp.setText(selectapp.getNoApp());
        textprrixApp.setText(selectapp.getPrixApp());
        textnomsApp.setText(selectapp.getNomApp());
    }
    
    /**
     * permet de verifier si les champs sont valides
     * @return 
     */
     private boolean isInputValid() {
          String errorMessage = "";

        if (textnomsApp.getText() == null || textnomsApp.getText().length() == 0) {
            errorMessage += "le nom de l'appareil n'est pas valide!\n"; 
        }
        if (textnumApp.getText() == null || textnumApp.getText().length() == 0) {
            errorMessage += "le numero de l'appareil n'est pas valide!\n"; 
            }
        else
        {
            try {
                Integer.parseInt(textnumApp.getText());
                if(checkapp(textnumApp.getText()))
                {
                    errorMessage += "Le numero choisi pour l'appareil existe déja!!\n"; 
                }
            } catch (Exception e) {
             errorMessage += "Entrer un nombre pour le numero!\n";
            }
        
        }
                    
        if (textnotypApp.getText() == null || textnotypApp.getText().length() == 0) {
            errorMessage += "le type de l'appareil n'est pas valide!\n"; 
            }
        else
        {
            try {
                Integer.parseInt(textnotypApp.getText());
                if(!checktyp(textnotypApp.getText()))
                {
                    errorMessage += "Le type entré n'existe pas encore!!\n";
                    errorMessage += "Enregistrer ce nouveau type et ressayer!!\n";
                }
            } catch (Exception e) {
             errorMessage += "Entrer un nombre pour le type d'appreil!\n";
            }
        
        }
                    
        if (textprrixApp.getText() == null || textprrixApp.getText().length() == 0) {
            errorMessage += "le prix de l'appareil n'est pas valide!\n"; 
            }
        else
        {
            try {
                Integer.parseInt(textnumApp.getText());
            } catch (Exception e) {
             errorMessage += "Entrer un nombre pour le prix de l'appreil!\n";
            }
        
        }
        

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(GestionProlog.getPrimaryStage());
            alert.setTitle("Disabled Data");
            alert.setHeaderText("Please correct invalid data");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
     }
    
     
     /**
     * permet de verifier si les champs sont valides
     * @return 
     */
     private boolean isInputValid1() {
          String errorMessage = "";

        if (textnumFact.getText() == null || textnumFact.getText().length() == 0) {
            errorMessage += "le numéro de la facture n'est pas valide!\n"; 
            }
        else
        {
            try {
                Integer.parseInt(textnumFact.getText());
                if(checkfac(textnumFact.getText()))
                {
                    errorMessage += "Ce numero de facture existe déja!!\n"; 
                }
            } catch (Exception e) {
             errorMessage += "Entrer un nombre pour le numero!\n";
            }
        
        }
        if (textmontFact.getText() == null || textmontFact.getText().length() == 0) {
            errorMessage += "le montant de la facture n'est pas valide!\n"; 
        }
        

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(GestionProlog.getPrimaryStage());
            alert.setTitle("Disabled Data");
            alert.setHeaderText("Please correct invalid data");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
     }
     
     
     /**
     * permet de verifier si les champs sont valides
     * @return 
     */
     private boolean isInputValid2() {
          String errorMessage = "";

        if (textnumType.getText() == null || textnumType.getText().length() == 0) {
            errorMessage += "le numéro du type n'est pas valide!\n"; 
        }
        else
        {
            try {
                Integer.parseInt(textnumType.getText());
                if(checktyp(textnumType.getText()))
                {
                    errorMessage += "Ce numero existe déja!!\n"; 
                }
            } catch (Exception e) {
             errorMessage += "Entrer un nombre pour le numero du type!\n";
            }
        }
        if (texttypType.getText() == null || texttypType.getText().length() == 0) {
            errorMessage += "Le type entré n'est pas valide!\n"; 
        }
        

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(GestionProlog.getPrimaryStage());
            alert.setTitle("Disabled Data");
            alert.setHeaderText("Please correct invalid data");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
     }
     
     
     /**
     * permet de verifier si les champs sont valides
     * @return 
     */
     private boolean isInputValid3() {
          String errorMessage = "";

        if (textnumDetFact.getText() == null || textnumDetFact.getText().length() == 0) {
            errorMessage += "le numéro de la facture n'est pas valide!\n"; 
        }
        else
        {
            try {
                Integer.parseInt(textnumApp.getText());
            } catch (Exception e) {
             errorMessage += "Entrer un nombre pour le numero!\n";
            }
        }
        if (textnumappDetFact.getText() == null || textnumappDetFact.getText().length() == 0) {
            errorMessage += "Le type d'appareil n'est pas valide!\n"; 
            }
        else
        {
            try {
                Integer.parseInt(textnumDetFact.getText());
            } catch (Exception e) {
             errorMessage += "Entrer un nombre pour le numero de l'appareil!\n";
            }
        
        }
        if(!checkapp(textnumApp.getText()) || !checkfac(textnumDetFact.getText()))
            {
               errorMessage += "La facture ou le type est inexistant dans notre Base!!\n"; 
            }
        if(checkdet(textnumApp.getText(),(textnumDetFact.getText())))
        {
            errorMessage += "Ce couple de données existent déja dans notre base!!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(GestionProlog.getPrimaryStage());
            alert.setTitle("Disabled Data");
            alert.setHeaderText("Please correct invalid data");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
     }
     
     
    @FXML
    private void handleOKapp()
    {
        if(isInputValid())
        {
            Appareil temp,tem;
            
            temp = new Appareil(null, null, null, null);
            temp.setNoApp(textnumApp.getText());
            temp.setNoType(textnotypApp.getText());
            temp.setNomApp(textnomsApp.getText());
            temp.setPrixApp(textprrixApp.getText());
            
            if(editapp)
            {
                tem = selectapp;
                
                listApp.remove("appareil(" + tem.getNoApp()+","
                    + tem.getNoType()+"," + "'"+tem.getNomApp()+"'"
                    +","+ tem.getPrixApp() + ").");
                
              
                supprimer("appareil(" + tem.getNoApp()+","
                    + tem.getNoType()+"," + "'"+tem.getNomApp()+"'"
                    +","+ tem.getPrixApp() + ")");
                
                appData.remove(tem);
            } 
            
            String interclause = "appareil(" + temp.getNoApp()+","
                  + temp.getNoType()+"," + "'"+temp.getNomApp()+"'"
                  +","+ temp.getPrixApp() + ")";
            
            enregistrer(interclause);
            listApp.add("appareil(" + temp.getNoApp()+","
                + temp.getNoType()+"," + "'"+temp.getNomApp()+"'"
                +","+ temp.getPrixApp() + ").");
            
            appData.add(temp);
            handlecancelapp();
        }
        
        
    }
    
    /**
     * cette méthode permet d'enregistrer notre clause dans le progrgamme prolog
     * chargé en mémoire
     * @param interclause 
     */
    private void enregistrer(String interclause)
    {
        String memoire = "assert("+ interclause +").";
        
        Query query = new Query(memoire);
        query.allSolutions();
    }
    
    /**
     * cette méthode permet de supprimer notre clause dans le progrgamme prolog
     * chargé en mémoire
     * @param interclause 
     */
    private void supprimer(String interclause)
    {
        String memoire = "retract("+ interclause +").";
        Query query = new Query(memoire);
        query.allSolutions();
    }
    
    @FXML
    private void handlecancelapp()
    {
        boxapp.setVisible(false);
        panetapp.setDisable(false);
        textnotypApp.setText(null);
        textnumApp.setText(null);
        textprrixApp.setText(null);
        textnomsApp.setText(null);
    }
    
    @FXML
    private void launchaddfac()
    {
        panefac.setDisable(true);
        boxfac.setVisible(true);
        editfac = false;
        textnumFact.setText(null);
        textmontFact.setText(null);
    }
    
    @FXML
    private void launchmodfac()
    {
        panefac.setDisable(true);
        boxfac.setVisible(true);
        editfac = true;
        textnumFact.setText(selectfac.getNoFact());
        textmontFact.setText(selectfac.getMontant());
    }
    
    @FXML
    private void handleOKfac()
    {
        if(isInputValid1())
        {
            Facture temp,tem;
            
            temp =  new Facture(null,null);
            temp.setNoFact(textnumFact.getText());
            temp.setMontant(textmontFact.getText());
        
            if(editfac)
            {
                tem = selectfac;
                listFact.remove("facture(" +tem.getNoFact()+","+ tem.getMontant()+ ").");
                supprimer("facture(" +tem.getNoFact()+","+ tem.getMontant()+ ")");
                factData.remove(tem);
            }
            
            String interclause = "facture(" +temp.getNoFact()+","+ temp.getMontant()+ ")";
            
            enregistrer(interclause);
            
            listFact.add("facture(" +temp.getNoFact()+","+ temp.getMontant()+ ").");
            
            factData.add(temp);
        
            handlecancelfac();
        }
    }
    
    @FXML
    private void handlecancelfac()
    {
        boxfac.setVisible(false);
        panefac.setDisable(false);
        textnumFact.setText(null);
        textmontFact.setText(null);
    }
    
    @FXML
    private void launchadddetfac()
    {
        panedetfac.setDisable(true);
        boxdetfac.setVisible(true);
        editdetfac = false;
        textnumDetFact.setText(null);
        textnumappDetFact.setText(null);
    }
    
    @FXML
    private void launchmoddetfac()
    {
        panedetfac.setDisable(true);
        boxdetfac.setVisible(true);
        editdetfac = true;
        textnumDetFact.setText(selectdetfac.getNoFact());
        textnumappDetFact.setText(selectdetfac.getNoApp());
    }
    
    @FXML
    private void handleOKdetfac()
    {
        if(isInputValid3())
        {
        
            DetailFacture temp,tem;
            
            temp=  new DetailFacture(null,null);
            temp.setNoApp(textnumappDetFact.getText());
            temp.setNoFact(textnumDetFact.getText());
            
            if(editdetfac)
            {
                tem = selectdetfac;
                listDetFact.remove("detailFacture(" +tem.getNoFact()+","+ tem.getNoApp() + ").");
                supprimer("detailFacture(" +tem.getNoFact()+","+ tem.getNoApp() + ")");
                detfactData.remove(tem);
            }
            
            String interclause = "detailFacture(" +temp.getNoFact()+","+ temp.getNoApp() + ")";
            
            enregistrer(interclause);
          
            listDetFact.add("detailFacture(" +temp.getNoFact()+","+ temp.getNoApp() + ").");
            
            detfactData.add(temp);
        
            handlecanceldetfac();
        }
    }
    
    @FXML
    private void handlecanceldetfac()
    {
        boxdetfac.setVisible(false);
        panedetfac.setDisable(false);
        textnumDetFact.setText(null);
        textnumappDetFact.setText(null);
    }
    
    @FXML
    private void deletetyp()
    {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(GestionProlog.getPrimaryStage());
            alert.setTitle("Confirm Dialog");
            alert.setHeaderText("We just want to cheer");
            alert.setContentText("Do you want to delete " + selecttyp.getNotype()+ "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                
                Type temp = selecttyp;
                
                listType.remove("type(" +temp.getNotype()+","+ "'"+temp.getNomtyme()+"'"+ ").");
                
                supprimer("type(" +temp.getNotype()+","+ "'"+temp.getNomtyme()+"'"+ ")");
                
                typeData.remove(temp);
                
                
                
                
            } else {
                 // ... user chose CANCEL or closed the dialog
               alert.close();
            }
    }
    
    @FXML
    private void deleteapp()
    {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(GestionProlog.getPrimaryStage());
            alert.setTitle("Confirm Dialog");
            alert.setHeaderText("We just want to cheer");
            alert.setContentText("Do you want to delete " + selectapp.getNoApp()+ "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                
                Appareil temp = selectapp;
                
                listApp.remove("appareil(" + temp.getNoApp()+","
                    + temp.getNoType()+"," + "'"+temp.getNomApp()+"'"
                    +","+ temp.getPrixApp() + ").");
                
              
                supprimer("appareil(" + temp.getNoApp()+","
                    + temp.getNoType()+"," + "'"+temp.getNomApp()+"'"
                    +","+ temp.getPrixApp() + ")");
                
                appData.remove(temp);
                
                
                
                
            } else {
                 // ... user chose CANCEL or closed the dialog
               alert.close();
            }
    }
    
    
    @FXML
    private void deletefac()
    {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(GestionProlog.getPrimaryStage());
            alert.setTitle("Confirm Dialog");
            alert.setHeaderText("We just want to cheer");
            alert.setContentText("Do you want to delete " + selectfac.getNoFact()+ "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                
                 Facture temp = selectfac;
                
                 listFact.remove("facture(" +temp.getNoFact()+","+ temp.getMontant()+ ").");
                 
                 supprimer("facture(" +temp.getNoFact()+","+ temp.getMontant()+ ")");
                 
                factData.remove(temp);
                
            } else {
                 // ... user chose CANCEL or closed the dialog
               alert.close();
            }
    }
    
    
    @FXML
    private void deletedetfac()
    {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(GestionProlog.getPrimaryStage());
            alert.setTitle("Confirm Dialog");
            alert.setHeaderText("We just want to cheer");
            alert.setContentText("Do you want to delete (" + selectdetfac.getNoFact()+ ","
                    +selectdetfac.getNoApp()+")"+"?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                
                 DetailFacture temp = selectdetfac;
                
                 listDetFact.remove("detailFacture(" +temp.getNoFact()+","+ temp.getNoApp() + ").");
                 
                 supprimer("detailFacture(" +temp.getNoFact()+","+ temp.getNoApp() + ")");
                 
                detfactData.remove(temp);
                
            } else {
                 // ... user chose CANCEL or closed the dialog
               alert.close();
            }
    }
    
    public static void main(String[] args) {
        GestionController con = new GestionController();
    }

    private boolean checktyp(String mot) {
        boolean result = false;
        
        for(Type auto : typeData)
        {
            if(auto.getNotype().equals(mot))
            {
                result = true;
            }
        }
        
        return result;
    }

    private boolean checkapp(String text) {
        boolean result = false;
        
        for(Appareil auto : appData)
        {
            if(auto.getNoApp().equals(text))
            {
                result = true;
            }
        }
        
        return result;
    }

    private boolean checkfac(String text) {
     boolean result = false;
        
        for(Facture auto : factData)
        {
            if(auto.getNoFact().equals(text))
            {
                result = true;
            }
        }
        
        return result;}

    private boolean checkdet(String text, String string) {
        boolean result = false;
        
        for(DetailFacture auto : detfactData)
        {
            if(auto.getNoApp().equals(text)&& auto.getNoFact().equals(string))
            {
                result = true;
            }
        }
        
        return result;
    }
}

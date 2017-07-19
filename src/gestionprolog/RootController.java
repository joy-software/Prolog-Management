/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionprolog;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author NDJAMA
 */
public class RootController implements Initializable {
    private GestionProlog myapp;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param app
     */
    public void setMainApp(GestionProlog app)
    {
        myapp = app;
    }

    public RootController() {
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
     /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Prolog files (*.pl)","*.pl");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File fileBD = fileChooser.showOpenDialog(GestionProlog.getPrimaryStage());
        Path monFichier = Paths.get(fileBD.getAbsolutePath());
        Path monFichierCopie = Paths.get(new File("./"+fileBD.getName()).getAbsolutePath());
        try {
            Path file = Files.copy(monFichier, monFichierCopie);
        } catch (IOException ex) {
            GestionController.printStrace(ex);
        }
        GestionController.bdpath = fileBD.getName();
        GestionController.appData.clear();
        GestionController.detfactData.clear();
        GestionController.factData.clear();
        GestionController.listApp.clear();
        GestionController.listDetFact.clear();
        GestionController.listFact.clear();
        GestionController.listType.clear();
        GestionController.typeData.clear();
        GestionController.fillData();
    }
    
    
     /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleSave() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Prolog files (*.pl)","*.pl");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File fileBD = fileChooser.showSaveDialog(GestionProlog.getPrimaryStage());
        File Bd = new File(GestionController.bdpath);
        Path monFichier = Paths.get(Bd.getAbsolutePath());
        Path monFichierCopie = Paths.get(fileBD.getAbsolutePath());
        try {
            Path file = Files.copy(monFichier, monFichierCopie);
        } catch (IOException ex) {
            GestionController.printStrace(ex);
        }
        
    }
    
    
    /**
     * permet de lancer apropos
     */
    @FXML
    private void about()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.initOwner(GestionProlog.getPrimaryStage());
        alert.setTitle("ABOUT US");
        alert.setHeaderText("\t\t\t\tAuthors\n");
        alert.setContentText("\t\t\t\tNDJAMA JOY JEDIDJA \n"
        +"\t\t\tSIMO NKAMNKUME ROSTAND \n"
        +"\t\t\t Elèves Ingénieurs à l'Ecole Nationale\n"+
        " \t\t\t\tSupérieure Polytechnique\n"+
        " \t\t\tCopyright (C) Septembre 2015\n"+
        " \t\t\t\t\t GENIE INFOS \n");
        alert.showAndWait();
    }
    
}

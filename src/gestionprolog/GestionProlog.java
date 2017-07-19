/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionprolog;

import static gestionprolog.GestionController.printStrace;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Map;
import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import jpl.Query;

/**
 *
 * @author NDJAMA
 */
public class GestionProlog extends Application {
    
    private static Stage primaryStage;
    private BorderPane rootlayout;
    private Object obj;
    
    public static  Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        GestionProlog.primaryStage = primaryStage;
    }

    public GestionProlog() {
        try {
            //System.setOut(new PrintStream(new File("text.txt")));
            //System.setErr(new PrintStream(new File("error.txt")));
       } catch (Exception ex) {
        GestionController.printStrace(ex);}
        try {
            ProcessBuilder pb = new ProcessBuilder(
            "cmd.exe", "/c", "set");
//            builder.redirectErrorStream(true);
//            Process p = builder.start();
//            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            String line;
//            while (true) {
//                line = r.readLine();
//                if (line == null) { break; }
//                    System.out.println(line);
//                }
   //         Process p = Runtime.getRuntime().exec("set path=%path%;%SWI_HOME_DIR%\\bin;%SWI_HOME_DIR%\\lib\\jpl.jar");
              Map<String, String> env = pb.environment();
              obj = env.get("G_PROL_DIR");
               System.out.println(obj);
//            env.remove("Path");
//            env.put("Path", obj.toString()+"%SWI_HOME_DIR%\\bin;%SWI_HOME_DIR%\\lib\\jpl.jar");
//            Process p = pb.start();
//            InputStreamReader isr = new InputStreamReader(p.getInputStream());
//            char[] buf = new char[1024];
//            while (!isr.ready()) {
//                ;
//            }
//            while (isr.read(buf) != -1) {
//                System.out.println(buf);
//            }
        } catch (Exception e) {
        }
        
        
    }
    
    
    
    @Override
    public void start(Stage primaryStage) {
        GestionProlog.primaryStage = primaryStage;
        GestionProlog.primaryStage.setTitle("Gestion Prolog");
        File f1 = new File("pro.png");
       
        GestionProlog.primaryStage.getIcons().add(new Image("file:"+f1.getAbsolutePath()));
        GestionProlog.primaryStage.setOnCloseRequest((WindowEvent event) -> {
           
//            File f = new File("bd.pl");
//            PrintWriter p = null;
//            try {
//                p = new PrintWriter(f);
//            } catch (FileNotFoundException ex) {
//                GestionController.printStrace(ex);
//            }
//            p.print("");
//            p.close();
            String requette = "tell('"+GestionController.bdpath+"').";
            Query query = new Query(requette);
            query.allSolutions();
            Query q = new Query("listing.");
            q.allSolutions();
            Query q1 = new Query("told.");
            q1.allSolutions();
            System.exit(0);
            
           // save();
        });
        prechargement();
        chargement();
    }

    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void prechargement() 
    {
        try {
           // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
           
              File f = new File("./src/gestionprolog/root.fxml");
              loader.setLocation(new URL("file:/"+f.getAbsolutePath()));
             
               rootlayout = (BorderPane)loader.load();
           
              RootController controller = loader.getController();
              //controller.setMainApp(this);
              
              
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootlayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
           GestionController.printStrace(e);
        }
    }
    
    
    private void chargement() 
    {
        try {
           // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
           // loader.setLocation(FXDomaineMinier.class.getResource("./RootLayout.fxml"));
              File f = new File("./src/gestionprolog/gestion.fxml");
              loader.setLocation(new URL("file:/"+f.getAbsolutePath()));
             
              AnchorPane root = loader.load();
           
               rootlayout.setCenter(root);
           // Node node = historiqueOverview.getChildren().get(0).get
       
    
            // Give the controller access to the main app.
            GestionController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
           GestionController.printStrace(e);
        }
    }
    
    
    ScheduledService<Void> SaveBD = new ScheduledService<Void>(){

    @Override
    protected Task<Void> createTask() {
        return new Task<Void>(){

        @Override
        protected Void call(){
            save(); 
            return null;
        }
        };
    }
    };
    
    public static void save()
    {
        // Sauvegarder le fichier ici.
            try {
                    
                    
                    PrintWriter fich = null;
                   
                    fich = new PrintWriter(new BufferedWriter(new FileWriter("bd.pl", true)));
			//true c'est elle qui permet d'écrire à la suite des donnée enregistrer et non de les remplacé 
                   
                    for(String auto : GestionController.listApp)
                    {
                       
                        fich.println(auto);
                    }
                    fich.println();
                    for(String auto : GestionController.listDetFact)
                    {
                       
                        fich.println(auto);
                    }
                    fich.println();
                    for(String auto : GestionController.listFact)
                    {
                       
                        fich.println(auto);
                    }
                    fich.println();
                    for(String auto : GestionController.listType)
                    {
  
                        fich.println(auto);
                    }
                    fich.println();
                    fich.close();
            } catch (Exception e1) {
                    printStrace(e1);
		}
    }
}

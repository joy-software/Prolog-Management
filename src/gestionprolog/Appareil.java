/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionprolog;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import jpl.*;

/**
 *
 * @author NDJAMA
 */
public class Appareil {
    
    
    private  StringProperty noApp = new SimpleStringProperty();
    private  StringProperty noType = new SimpleStringProperty();
    private  StringProperty nomApp = new SimpleStringProperty();
    private  StringProperty prixApp = new SimpleStringProperty();

    
    public Appareil(String noApp, String noType, String nomApp, String prixApp) 
    {
        this.noApp = new SimpleStringProperty(noApp);
        this.noType = new SimpleStringProperty(noType);
        this.nomApp = new SimpleStringProperty(nomApp);
        this.prixApp = new SimpleStringProperty(prixApp);
    }

    
    public String getPrixApp() {
        return prixApp.get();
    }

    public void setPrixApp(String value) {
        prixApp.set(value);
    }

    public StringProperty prixAppProperty() {
        return prixApp;
    }
    
    public String getNomApp() {
        return nomApp.get();
    }

    public void setNomApp(String value) {
        nomApp.set(value);
    }

    public StringProperty nomAppProperty() {
        return nomApp;
    }
    
    public String getNoType() {
        return noType.get();
    }

    public void setNoType(String value) {
        noType.set(value);
    }

    public StringProperty noTypeProperty() {
        return noType;
    }
    
    public String getNoApp() {
        return noApp.get();
    }

    public void setNoApp(String value) {
        noApp.set(value);
    }

    public StringProperty noAppProperty() {
        return noApp;
    }

    @Override
    public String toString() {
        return "Appareil{" + "noApp=" + noApp + ", noType=" + noType + ", nomApp=" + nomApp + ", prixApp=" + prixApp + '}';
    }
    
    
    public static void main(String[] args) {
        
        JPL.init();
    }
    
}

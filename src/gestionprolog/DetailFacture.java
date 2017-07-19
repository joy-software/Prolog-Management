/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionprolog;

import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author NDJAMA
 */
public class DetailFacture {
    
    
    private StringProperty noFact = new SimpleStringProperty();
    private StringProperty noApp = new SimpleStringProperty();

    
    public DetailFacture(String noFact, String noApp) 
    {
        this.noApp = new SimpleStringProperty(noApp);
        this.noFact = new SimpleStringProperty(noFact);
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
    
    public String getNoFact() {
        return noFact.get();
    }

    public void setNoFact(String value) {
        noFact.set(value);
    }

    public StringProperty noFactProperty() {
        return noFact;
    }

    @Override
    public String toString() {
        return "DetailFacture{" + "noFact=" + noFact + ", noApp=" + noApp + '}';
    }
    
    
    
    public static void main(String[] args) {
        
    }
    
}

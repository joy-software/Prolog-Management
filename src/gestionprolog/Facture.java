/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionprolog;

import javafx.beans.property.StringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author NDJAMA
 */
public class Facture {
    
    
    private StringProperty noFact = new SimpleStringProperty();
    private StringProperty montant = new SimpleStringProperty();

    
    public Facture(String noFact, String montant) 
    {
        this.noFact = new SimpleStringProperty(noFact);
        this.montant = new SimpleStringProperty(montant);
    }
    
    
    
    public String getMontant() {
        return montant.get();
    }

    public void setMontant(String value) {
        montant.set(value);
    }

    public StringProperty montantProperty() {
        return montant;
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
        return "Facture{" + "noFact=" + noFact + ", montant=" + montant + '}';
    }
    
    
    
    public static void main(String[] args) {
        
    }
    
}

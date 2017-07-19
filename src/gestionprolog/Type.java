/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionprolog;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author NDJAMA
 */
public class Type {
    
    
    
    private StringProperty nomtype = new SimpleStringProperty();
    private  StringProperty notype = new SimpleStringProperty();

    public String getNotype() {
        return notype.get();
    }

    public void setNotype(String value) {
        notype.set(value);
    }

    public StringProperty notypeProperty() {
        return notype;
    }
    
    public String getNomtyme() {
        return nomtype.get();
    }

    public void setNomtyme(String value) {
        nomtype.set(value);
    }

    public StringProperty nomtypeProperty() {
        return nomtype;
    }
    
    
    

    
    
    public Type(String notype, String type) 
    {
        this.notype =  new SimpleStringProperty(notype);
        this.nomtype = new SimpleStringProperty(type);
    }

    
    @Override
    public String toString() {
        return "Type{" + "notype=" + notype + ", nomtype=" + nomtype + '}';
    }
    
    
    
    public static void main(String[] args) {
        
    }
    
}

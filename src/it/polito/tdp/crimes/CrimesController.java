/**
 * Sample Skeleton for 'Crimes.fxml' Controller Class
 */

package it.polito.tdp.crimes;

import java.net.URL;
import java.time.Month;
import java.time.Year;
import java.util.ResourceBundle;

import it.polito.tdp.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CrimesController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ComboBox<Year> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxMese"
    private ComboBox<Month> boxMese; // Value injected by FXMLLoader

    @FXML // fx:id="boxGiorno"
    private ComboBox<Integer> boxGiorno; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaReteCittadina"
    private Button btnCreaReteCittadina; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtN"
    private TextField txtN; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCreaReteCittadina(ActionEvent event) {
    	txtResult.clear();
    	//txtResult.appendText("Creo il grafo... " , this.model.creaGrafo());
    	this.model.creaGrafo(boxAnno.getValue());
    	txtResult.appendText("Ecco i vertici adiacenti \n " + this.model.getAdiacenti());
    	ObservableList<Month> mese = FXCollections.observableList(model.getMesi(boxAnno.getValue()));
    	boxMese.setItems(mese);
    	boxMese.setValue(mese.get(0));
    	ObservableList<Integer> giorno = FXCollections.observableList(model.getDay(boxAnno.getValue(), boxMese.getValue()));
    	boxGiorno.setItems(giorno);
    	boxGiorno.setValue(giorno.get(0));
    }
    
    @FXML
    void updateDays(ActionEvent event) {
    	System.out.println("you clicked");
    	ObservableList<Integer> giorno = FXCollections.observableList(model.getDay(boxAnno.getValue(), boxMese.getValue()));
    	boxGiorno.setItems(giorno);
    	boxGiorno.setValue(giorno.get(0));
    }

    @FXML
    void doSimula(ActionEvent event) {
    	try {
    		int nAgenti = Integer.parseInt(txtN.getText());
    		if(nAgenti<1 || nAgenti>10) {
    			txtResult.appendText("Inserire un numero intero tra 1 e 10");
    		}
    	}
    	catch(NumberFormatException n) {
    		txtResult.appendText("Inserire un numero intero!");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert boxGiorno != null : "fx:id=\"boxGiorno\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnCreaReteCittadina != null : "fx:id=\"btnCreaReteCittadina\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert txtN != null : "fx:id=\"txtN\" was not injected: check your FXML file 'Crimes.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Crimes.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    	ObservableList<Year> anno = FXCollections.observableList(model.getAnni());
    	boxAnno.setItems(anno);
    	boxAnno.setValue(anno.get(0));
    }
}

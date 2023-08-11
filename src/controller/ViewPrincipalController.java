package controller;

import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Converter;
import model.Temperature;
import utils.ErrorMessage;
import utils.UtilConverter;
import utils.ValidateTextField;

public class ViewPrincipalController implements Initializable {
	
	static final int TEXT_FIELD_MAX_INPUT = 8;
	
	private boolean optionPrintLabelRight = true;
	
	ValidateTextField validateTextField = new ValidateTextField();
	
	ErrorMessage errorMessage = new ErrorMessage();
	
	@FXML
	private ComboBox<Converter> comboboxMain;
	 
	@FXML
	private ComboBox<Converter> comboboxSecond;

	@FXML
	private TextField txtInput;

	@FXML
	private TextField txtOutput;
	
	/* Label Up */
	@FXML
	private Label lblFirst, lblSecond;
	
	/* Label Bottom */
	@FXML
	private Label lblFirstBottom, lblSecondBottom;
	
	@FXML
	private Button btnMoney, btnTemperature, btnWeight;

	@FXML
	void comboboxEvent(ActionEvent event) {
		 
	  Object evt = event.getSource();
	  
	  if(evt.equals(comboboxMain)) {
		  if(optionPrintLabelRight) {
			  printLabel(1);
		  }
		  else {
			  printLabel(2);
		  }
		  
	  } else if(evt.equals(comboboxSecond)) {
		  if(optionPrintLabelRight) {
			  printLabel(3);
		  }
		  else {
			  printLabel(4);
		  }
	  }
	  
	  calculateOutput();
	
		 
	 }

	 @FXML
	 void keyTypedInput(KeyEvent event) {
		 
		 errorMessage = ValidateTextField.validateDouble(txtInput, event, TEXT_FIELD_MAX_INPUT);
		 
		 if(ErrorMessage.isError()) {
			 Toolkit.getDefaultToolkit().beep();
		 }
		 else {			 
			 calculateOutput();
		 }
		
	 }
	 
	 @FXML
	 void txtInputClicked(MouseEvent event) {
		 ValidateTextField.setOldCursorCaretPosition(txtInput.getCaretPosition());
	 }
	 
	 @FXML
	 void eventButtons(ActionEvent event) {

		 Object evt = event.getSource();
		 
		 if(evt.equals(btnMoney)) {
			 chargeConverter(CreateCollectionsMoneys());
			 optionPrintLabelRight = true;
			 printLabel(1);
			 printLabel(3);
			 
		 } else if(evt.equals(btnTemperature)) {
			 chargeConverter(CreateCollectionsTemperatures());
			 optionPrintLabelRight = false;
			 printLabel(2);
			 printLabel(4);
			 
		 } else if(evt.equals(btnWeight)) {
			 chargeConverter(CreateCollectionsWeights());
			 optionPrintLabelRight = false;
			 printLabel(2);
			 printLabel(4);			 
		 }
		 		 
	 }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		chargeConverter(CreateCollectionsMoneys());
				
	}
	
	private void chargeConverter(ArrayList<Converter> CreateCollectionsConverter) {
		// Combobox Primario
		if(!comboboxMain.getItems().isEmpty()) {	
//			comboboxMain.getSelectionModel().clearSelection();
//			comboboxMain.setValue(null);
			comboboxMain.getItems().removeAll(comboboxMain.getItems());
		}
		comboboxMain.getItems().addAll(CreateCollectionsConverter);
		comboboxMain.setConverter(new UtilConverter());
		
		comboboxMain.getSelectionModel().select(0);
		
		// Combobox Secundario
		if(!comboboxSecond.getItems().isEmpty()) {
//			comboboxSecond.getSelectionModel().clearSelection();
//			comboboxSecond.setValue(null);
			comboboxSecond.getItems().removeAll(comboboxSecond.getItems());
		}
		comboboxSecond.getItems().addAll(CreateCollectionsConverter);
		comboboxSecond.setConverter(new UtilConverter());
		
		comboboxSecond.getSelectionModel().select(1);
		
		// Inicio de Input
		txtInput.setText("1");
		
		// Iniciando symbolos
		printLabel(1);
		printLabel(3);

		calculateOutput();
	}
	
	private ArrayList<Converter> CreateCollectionsMoneys() {
		
		ArrayList<Converter> arrayMoneys = new ArrayList<>();
		
		arrayMoneys.add(new Converter("S/","Sol Peruano", 1));
		arrayMoneys.add(new Converter("$","Dolar Americano", 0.2706));
		arrayMoneys.add(new Converter("€","Euro", 0.2461));
		arrayMoneys.add(new Converter("£","Libra Esterlina", 0.2117));
		arrayMoneys.add(new Converter("¥","Yen Japones", 38.5390));
		arrayMoneys.add(new Converter("₩","Won Surcoreano", 353.5028));
		
		return arrayMoneys;
	}
	
	private ArrayList<Converter> CreateCollectionsTemperatures() {
		
		ArrayList<Converter> arrayTemperatures = new ArrayList<>();
		
		arrayTemperatures.add(new Temperature("°C","Celsius", 1));
		arrayTemperatures.add(new Temperature("F","Fahrenheit", 2));
		arrayTemperatures.add(new Temperature("K","Kelvin ", 3));
		arrayTemperatures.add(new Temperature("Re","Reaumur", 4));
		arrayTemperatures.add(new Temperature("Ra","Rankine", 5));
		
		return arrayTemperatures;
	}
	
	private ArrayList<Converter> CreateCollectionsWeights() {
		
		ArrayList<Converter> arrayWeights = new ArrayList<>();
		
		arrayWeights.add(new Converter("g","Gramo", 1));
		arrayWeights.add(new Converter("Kg","Kilogramo", 0.001));
		arrayWeights.add(new Converter("lb","Libras", 0.0022));
		arrayWeights.add(new Converter("oz","Onza", 0.03527));
		arrayWeights.add(new Converter("mg","Miligramo", 1000));
		
		return arrayWeights;
	}
	
	private void calculateOutput() {
		
		if( selectedCombobox() ) {
			
			Double cantidad = Double.parseDouble(txtInput.getText());
			
			Converter selected = comboboxMain.getSelectionModel().getSelectedItem();
			 
			Converter change = comboboxSecond.getSelectionModel().getSelectedItem();
			
			Double valueCalculate = change.operationConverter(cantidad, selected.getFactorConverter());
			
			txtOutput.setText(valueCalculate.toString());	
		}
		
	}
	
	private void printLabel (int option) {
		
		if( selectedCombobox() ) {
			
			Converter selected = comboboxMain.getSelectionModel().getSelectedItem();			 
			Converter change = comboboxSecond.getSelectionModel().getSelectedItem();
			switch (option) {
			case 1:
				lblFirst.setText(selected.getSymbol());
				lblSecond.setText("");
				break;
			case 2:
				lblFirst.setText("");
				lblSecond.setText(selected.getSymbol());
				break;
			case 3:
				lblFirstBottom.setText(change.getSymbol());
				lblSecondBottom.setText("");
				break;
			case 4:
				lblFirstBottom.setText("");
				lblSecondBottom.setText(change.getSymbol());
				break;
				
			default:
				break;
			}
		}
		
	}
	
	private boolean selectedCombobox () {
		return !(comboboxMain.getSelectionModel().getSelectedItem() == null) && !(comboboxSecond.getSelectionModel().getSelectedItem() == null);
	}
}

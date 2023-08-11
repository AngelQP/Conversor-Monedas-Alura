package utils;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ValidateTextField {
	
	private static int currentCursorCaretPosition = 0;
	private static int oldCursorCaretPosition = 0;
	private static String oldTextFieldContents = "0";
	
	static final int BACK_SPACE = 8;
	static final int CTRL_V = 22;
	static final int DEL = 127;
	static final int ENTER = 13;
	static final char DECIMAL_POINT = '.';
	
	
	public static ErrorMessage validateDouble(TextField textField, KeyEvent event, int textFieldMaxInput) {
		
		boolean error = false;
		String errorMsg = "";
		
		char currentKeyTyped = event.getCharacter().charAt(0);
		
		final boolean PASTE_KEY = currentKeyTyped == CTRL_V;

		
		final boolean INVALID_KEY
			= (!Character.isDigit(currentKeyTyped))
			&& (currentKeyTyped != BACK_SPACE)
			&& (currentKeyTyped != DEL)
			&& (currentKeyTyped != ENTER)
			&& (currentKeyTyped != DECIMAL_POINT)
			&& (!Character.isISOControl(currentKeyTyped));
		
		currentCursorCaretPosition = textField.getCaretPosition();
		
		
		try {
			
			if((currentKeyTyped == DECIMAL_POINT) && (countStringMatches(textField.getText(),".")>1)) {
				String textInput = textField.getText();
				
				error = true;
				errorMsg = "ERROR: Invalid Decimal number";
				
				textField.setText(deleteCharInString(textInput, currentCursorCaretPosition - 1));
				
				textField.positionCaret(currentCursorCaretPosition - 1);
			}
			else if(PASTE_KEY) {
				error = true;
				errorMsg = "ERROR: PASTE NOT allowed";
				
				textField.setText(oldTextFieldContents);
				textField.positionCaret(getOldCursorCaretPosition());
				
			} else if (INVALID_KEY) {
				String replaceText = textField.getText().replace(Character.toString(currentKeyTyped), "");
				
				error = true;
				errorMsg = "ERROR: Input must be a whole number";
				
				textField.setText(replaceText);
				textField.positionCaret(currentCursorCaretPosition - 1);
			}
			
			if (textField.getText().length() > textFieldMaxInput) {
				String textInput = textField.getText();
				
				error = true;
				errorMsg = "ERROR: Max number Input " + textFieldMaxInput;
				
				textField.setText(deleteCharInString(textInput, currentCursorCaretPosition - 1));
				
				textField.positionCaret(currentCursorCaretPosition - 1);
			}
			
			if(textField.getText().equals("")) {
				textField.setText("0");
			}
			
			oldTextFieldContents = textField.getText();
			setOldCursorCaretPosition(textField.getCaretPosition());
			
			return new ErrorMessage(error, errorMsg);
		}
		catch (Exception e) {
			// TODO: handle exception
			return new ErrorMessage(true, "ERROR: Exception Caught");
		}
		
	}
	
	public static int countStringMatches(String text, String searchStr) {
		int index = 0;
		int found = 0;
		
		while((index = text.indexOf(searchStr, index)) != -1) {
			found++;
			index += searchStr.length();
		}
		
		return found;
	}
	
	static private String deleteCharInString(String textInput, int CharPosition) {
		StringBuilder str = new StringBuilder(textInput);
		StringBuilder limitTextInput = str.deleteCharAt(CharPosition);
		
		return limitTextInput.toString();
	}
	
	public static int getOldCursorCaretPosition() {
		return oldCursorCaretPosition;
	}
	
	public static void setOldCursorCaretPosition(int oldCursorCaretPositionZ) {
		oldCursorCaretPosition = oldCursorCaretPositionZ;
	}

}

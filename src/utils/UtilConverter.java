package utils;

import javafx.util.StringConverter;
import model.Converter;

public class UtilConverter extends StringConverter<Converter> {

	@Override
	public Converter fromString(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(Converter converter) {
		// TODO Auto-generated method stub
		return converter == null ? null : converter.getName();
	}

	
}

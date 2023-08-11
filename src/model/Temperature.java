package model;

public class Temperature extends Converter {

	public Temperature() {
	}
	
	public Temperature(String symbol, String name, double factorConverter) {
		super(symbol, name, factorConverter);
	}
	
	public double operationConverter(double cantidad, double valorConverter) {
		
		int key = (int)valorConverter;
		
		double valor =  converterFar(cantidad, key);
		
		valor = converterTemp(valor, (int)getFactorConverter());
		
		return redondearDecimal(valor, 2);
	}
	
	private double converterFar(double cantidad, int option) {
		
		double valor = 0.0;
		
		switch (option) {
		case 1:
			valor = 1.8*cantidad + 32;
			break;
			
		case 2:
			valor = cantidad;
			break;
			
		case 3:
			valor = ((9*cantidad)/5) - 459.67;
			break;
		
		case 4:
			valor =  cantidad*2.25 + 32;
			break;
			
		case 5:
			valor = cantidad - 459.67;
			break;

		default:
			break;
		}
		
		return valor;
	}
	
	private double converterTemp(double cantidad, int option) {
		
		double valor = 0.0;
		
		switch (option) {
		case 1:
			valor = (cantidad-32)/1.8;
			break;
			
		case 2:
			valor = cantidad;
			break;
			
		case 3:
			valor = (cantidad+459.67)*5/9  ;
			break;
		
		case 4:
			valor =  (cantidad-32)/2.25;
			break;
			
		case 5:
			valor = cantidad + 459.67;
			break;

		default:
			break;
		}
		
		return valor;
	}
	
}

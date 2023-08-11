package model;

public class Money extends Converter {
	
	public Money() {
	}
	
	public Money(String symbol, String name, double factorConverter) {
		super(symbol,name,factorConverter);
	}
	
	public double operationConverter(double cantidad, double valorConverter) {
		double valor = (super.getFactorConverter() * cantidad)/valorConverter;
		return redondearDecimal(valor, 4);
	}
	
}

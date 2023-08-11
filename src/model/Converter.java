package model;

public class Converter {
	
	private String symbol;
	private String name;
	private double factorConverter;
	
	public Converter() {
	}
	
	public Converter(String symbol, String name, double factorConverter) {
		this.symbol = symbol;
		this.name = name;
		this.factorConverter = factorConverter;
	}
	
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getFactorConverter() {
		return factorConverter;
	}

	public void setFactorConverter(double factorConverter) {
		this.factorConverter = factorConverter;
	}
	
	public double redondearDecimal(double valorInicial, int numeroDecimales) {
		double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
	}
	
	/* OPERACIONES PARA QUE SE DEFINA EN CADA CLASE*/
	public double operationConverter(double cantidad, double valorConverter) {
		double valor = (getFactorConverter() * cantidad)/valorConverter;
		return redondearDecimal(valor, 4);
	}
	
}

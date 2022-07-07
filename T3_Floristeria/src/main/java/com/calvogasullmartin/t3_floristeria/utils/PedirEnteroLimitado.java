package com.calvogasullmartin.t3_floristeria.utils;

public class PedirEnteroLimitado {

	private String title;
	
	private RangoEnterosCerrado rango;
	
	private RangoEnterosCerradoVista rangoVista;
	
	public PedirEnteroLimitado(String title, int min, int max){
		assert title != null;
		this.rango = new RangoEnterosCerrado(min, max);
		rangoVista = new RangoEnterosCerradoVista("El valor debe estar entre ", rango);
		this.title = title + " " + rangoVista + ": ";
	}
	
	public PedirEnteroLimitado(String title, int max){
		this(title, 1, max);
	}
	
	public int read(){
		InOut io = new InOut();
		int value;
		boolean ok;
		do {
			value = io.readInt(title);
			ok = rango.includes(value);
			if (!ok) {
				rangoVista.writeln();
			}
		} while (!ok);
		return value;
	}
}

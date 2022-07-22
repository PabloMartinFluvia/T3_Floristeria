package com.calvogasullmartin.t3_floristeria.utils;

public class PedirEnteroLimitado2 {

	private String title;
	
	private RangoEnterosCerrado2 rango;
	
	private RangoEnterosCerradoVista2 rangoVista;
	
	public PedirEnteroLimitado2(String title, int min, int max){
		assert title != null;
		this.rango = new RangoEnterosCerrado2(min, max);
		rangoVista = new RangoEnterosCerradoVista2("El valor debe estar entre ", rango);
		this.title = title + " " + rangoVista + ": ";
	}
	
	public PedirEnteroLimitado2(String title, int max){
		this(title, 1, max);
	}
	
	public int read(){
		InOut2 io = new InOut2();
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

package com.calvogasullmartin.t3_floristeria.utils;

public class PedirEnteroLimitado {

	private String title;
	
	private RangoEnterosCerrado limits;
	
	private RangoEnterosCerradoVista limitsView;
	
	public PedirEnteroLimitado(String title, int min, int max){
		assert title != null;
		this.limits = new RangoEnterosCerrado(min, max);
		limitsView = new RangoEnterosCerradoVista("El valor debe estar entre ", limits);
		this.title = title + " " + limitsView + ": ";
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
			ok = limits.includes(value);
			if (!ok) {
				limitsView.writeln();
			}
		} while (!ok);
		return value;
	}
}

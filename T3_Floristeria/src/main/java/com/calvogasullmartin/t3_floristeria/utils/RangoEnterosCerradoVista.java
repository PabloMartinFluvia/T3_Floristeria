package com.calvogasullmartin.t3_floristeria.utils;

class RangoEnterosCerradoVista {

	private String title;
	
	private RangoEnterosCerrado rango;
	
	private InOut io;
	
	public RangoEnterosCerradoVista(String title, RangoEnterosCerrado rango){
		assert title != null;
		assert rango != null;
		this.title = title;
		this.rango = rango;
		io = new InOut();
	}
	
	public void writeln() {
		io.writeln(title + " " + this.toString());
	}
	
	@Override
	public String toString() {
		return "[" + rango.getMin() + ", " + rango.getMax() + "]";
	}
}

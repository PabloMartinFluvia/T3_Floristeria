package com.calvogasullmartin.t3_floristeria.utils;

class RangoEnterosCerradoVista2 {

	private String title;
	
	private RangoEnterosCerrado2 rango;
	
	private InOut2 io;
	
	public RangoEnterosCerradoVista2(String title, RangoEnterosCerrado2 rango){
		assert title != null;
		assert rango != null;
		this.title = title;
		this.rango = rango;
		io = new InOut2();
	}
	
	public void writeln() {
		io.writeln(title + " " + this.toString());
	}
	
	@Override
	public String toString() {
		return "[" + rango.getMin() + ", " + rango.getMax() + "]";
	}
}

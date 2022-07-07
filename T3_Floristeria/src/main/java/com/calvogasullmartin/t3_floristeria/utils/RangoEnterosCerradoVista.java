package com.calvogasullmartin.t3_floristeria.utils;

class RangoEnterosCerradoVista {

	private String title;
	
	private RangoEnterosCerrado closedInterval;
	
	private InOut io;
	
	public RangoEnterosCerradoVista(String title, RangoEnterosCerrado closedInterval){
		assert title != null;
		assert closedInterval != null;
		this.title = title;
		this.closedInterval = closedInterval;
		io = new InOut();
	}
	
	public void writeln() {
		io.writeln(title + " " + this.toString());
	}
	
	@Override
	public String toString() {
		return "[" + closedInterval.getMin() + ", " + closedInterval.getMax() + "]";
	}
}

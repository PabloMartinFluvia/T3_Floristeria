package com.calvogasullmartin.t3_floristeria.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InOut {

	private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
			System.in));

	public String readString(String title) {
		String input = null;
		boolean ok = false;
		do {
			this.write(title);
			try {
				input = bufferedReader.readLine();
				ok = true;
			} catch (Exception ex) {
				this.writeError("de cadena de caracteres");
			}
		} while (!ok);
		return input;
	}

	public void writeln() {
		System.out.println();
	}
	
	public void write(String string) {
		System.out.print(string);
	}

	public void writeln(String string) {
		System.out.println(string);
	}

	private void writeError(String formato) {
		System.err.println("ERROR DE FORMATO! "
				+ "Introduzca un valor con formato " + formato + ".");
	}
}
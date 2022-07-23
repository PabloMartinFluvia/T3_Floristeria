package com.calvogasullmartin.t3_floristeria.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class InOut {

        //Correcte per a ingés. Si es vol posar accents o apostrofs dona problemes
	private BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
    
        //Per a ES/CAT
        //private Scanner lector = new Scanner(System.in);

	public String readString(String title) {
		String input = null;
		boolean ok = false;
		do {                   
			this.write(title);                         
			try {
				input = lector.readLine(); // Buffered Reader
                                //input = lector.nextLine(); // Scanner
				ok = true;
			} catch (Exception ex) {
				this.writeError("de cadena de caracteres");
			}
		} while (!ok);
		return input;
	}
        
        public int readInt(String title) {
		int input = 0;
		boolean ok = false;
		do {
			try {
				input = Integer.parseInt(this.readString(title));
				ok = true;
			} catch (Exception ex) {
				this.writeError("entero");
			}
		} while (!ok);
		return input;
	}
        
        public float readFloat(String title) {
		float input = 0;
		boolean ok = false;
		do {
			try {
				input = Float.parseFloat(this.readString(title));
				ok = true;
			} catch (Exception ex) {
				this.writeError("decimal xx,yy");
			}
		} while (!ok);
		return input;
	}
        
        public char readChar(String title) {
		char charValue = ' ';
		boolean ok = false;
		do {
			String input = this.readString(title);
			if (input.length() != 1) {
				this.writeError("caracter");
			} else {
				charValue = input.charAt(0);
				ok = true;
			}
		} while (!ok);
		return charValue;
	}

	public void writeln() {
		System.out.println();
	}
	
	public void write(String string) {
		System.out.println(string); // println to fix the bug
	}

	public void writeln(String string) {
		System.out.println(string);
	}

	public void writeError(String formato) {
		System.err.println("ERROR DE FORMATO! "
				+ "Introduzca un valor con formato " + formato + ".");
	}
}

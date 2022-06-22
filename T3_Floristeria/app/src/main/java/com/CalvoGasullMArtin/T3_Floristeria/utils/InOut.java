package com.CalvoGasullMArtin.T3_Floristeria.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InOut {

    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public String readString(String title) {
        String input = null;
        boolean ok = false;
        do {
            this.write(title);
            try {
                input = bufferedReader.readLine();
                ok = true;
            } catch (IOException ex) {
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
            } catch (NumberFormatException ex) {
                this.writeError("enter");
            }
        } while (!ok);
        return input;
    }
    
    public float readFloat(String title) {
        float input = 0f;
        boolean ok = false;
        do {
            try {
                input = Float.parseFloat(this.readString(title));
                ok = true;
            } catch (NumberFormatException ex) {
                this.writeError("decimal");
            }
        } while (!ok);
        return input;
    }

    public void write(String string) {
        System.out.print(string);
    }

    public void writeln(String string) {
        System.out.println(string);
    }

    private void writeError(String format) {
        System.out.println("ERROR EN EL FORMAT INTRODUIT! "
                + "Introdueix un valor amb format " + format + ".");
    }
}

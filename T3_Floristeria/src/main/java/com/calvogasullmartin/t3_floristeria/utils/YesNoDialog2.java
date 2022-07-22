package com.calvogasullmartin.t3_floristeria.utils;

public class YesNoDialog2 {

    private String title;

    public YesNoDialog2(String title) {
        assert title != null;
        this.title = title;
    }

    public boolean read() {
        char answer;
        InOut2 io = new InOut2();
        boolean ok;
        do {
            answer = io.readChar(title + "? (s/n): ");
            ok = answer == 's' || answer == 'S' || answer == 'n'
                    || answer == 'N';
            if (!ok) {
                io.writeln("El valor debe ser 's' ó 'n'");
            }
        } while (!ok);
        return answer == 's' || answer == 'S';
    }
}

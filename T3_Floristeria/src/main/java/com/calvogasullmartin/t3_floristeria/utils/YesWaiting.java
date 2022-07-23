package com.calvogasullmartin.t3_floristeria.utils;

public class YesWaiting {
    
    private YesNoDialog pregunta;
    
    private String mensage;

    public YesWaiting(String mensage) {
        this.mensage = mensage;
        pregunta = new YesNoDialog(mensage);
    }
    
    public void bucleYes(){
        boolean ok =false;
        do{
            ok = pregunta.read();
        }while(!ok);
    }        
}

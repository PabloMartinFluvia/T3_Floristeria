package com.calvogasullmartin.t3_floristeria.utils;

public class YesWaiting2 {
    
    private YesNoDialog2 pregunta;
    
    private String mensage;

    public YesWaiting2(String mensage) {
        this.mensage = mensage;
        pregunta = new YesNoDialog2(mensage);
    }
    
    public void bucleYes(){
        boolean ok =false;
        do{
            ok = pregunta.read();
        }while(!ok);
    }        
}

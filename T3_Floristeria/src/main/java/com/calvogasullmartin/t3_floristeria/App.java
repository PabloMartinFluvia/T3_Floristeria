package com.calvogasullmartin.t3_floristeria;

public class App {
    
    public static void main(String[] args) {
        App app = new App();
        app.configurarInicializacion();
        app.execute();
    }
    
    /*
    Inicializa la configuración inicial.
    Que base de datos se usa, que familia de vistas, que famila de controladores, etc...
    Como mínimo debería usar una vista, para saber al usuario que BD usar.
    */
    private void configurarInicializacion(){
        //some code para inicializar las abstract facotry
        
        //es necesario inyectar a la DaoFactory una implementación concreta mediante
        //su metodo estatico setFactory.
    }
    
    /*
    Arranca la aplicación
    */
    private void execute(){
        //some code que permita ir canviando de controlador, según lo que le diga el usuario a las vistas
    }
}

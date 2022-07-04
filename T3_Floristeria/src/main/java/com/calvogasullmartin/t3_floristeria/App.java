package com.calvogasullmartin.t3_floristeria;

import com.calvogasullmartin.t3_floristeria.config.Configurador;
import com.calvogasullmartin.t3_floristeria.persistencia.DaoFactory;

public class App {
    
    private Configurador configurador;
    
    private DaoFactory daoFactory;
    
    
    public static void main(String[] args) {
        App app = new App();
        app.configurar();
    }
    
    /*
    Constructor
    */
    public App(){
        configurador = new Configurador();                
    }
    
    /*
    Inicializa la configuración inicial.
    Que base de datos se usa, que familia de vistas, que famila de controladores, etc...    
    */
    private void configurar(){
        configurador.configurarPersistenciaFactory();
        if(configurador.validarBD()){
            //BD está ok y és viable
            ejecutar(); // arranca la aplicación
        }else{
            //el programa "peta", ja que no se puede crear la BD
            //mejor que esto gestione dendtro del metodo configurador.validarBD()
        }        
    }
    
    /*
    Arranca la aplicación
    */
    private void ejecutar(){
        //some code que permita ir canviando de controlador / gestionar controlador principal
        // (pendiente de revisión) , según lo que le diga el usuario a las vistas
        
        /*
        De momento solo hace falta un "InicializarControlador"
            sequencia que debe organizar el controlador:
                1) Una vista que lanze un mensaje de bienvenida
                2) hacer getFactory del daoFactory y invocar al método .isNecesarioInicializar()
                    2.1) Si devuelve false (no será la primera vez que se ejecute la app)
                        (floristeria ya guardada (al menos el nombre))
                        -> NO hacer nada -> ir a [3}                        
        
                    2.2) Si devuleve true (será la primera vez que se ejecute la app)
                        Habrá que hacer (des de inicializarControlador):
                        a) invocar al metodo de la factory .inicializarBD()
                        b) instanciar un objecto de la classe "FloristeriaVista"
                        c) de momento es suficiente con que la floristeria pida el nombre al usuario
                            y lo devuelva
                        d) crear un objeto de la classe FloristeriaDto (inicialmente todo null)
                        e) mediante el .setNombre enchufarle el nombre proporcionado por la vista
                        f) hacer .getFactory().getFloristeriaDao() del daoFactory
                        g) lo que devuelve es un objecto del tipo FloristeriaDao
                            (es la clase que se encara de transferir data de la floristeria
                            des de la BD a la aplicacion)
                        h) decirle que haga .create( pasandole el objeto floristeriaDto)
                        i) -> se tendrá la BD inicializada + la info mínima de la floristeria guardada
                        
                        3) La misma vista del paso [1] que tenga también otro método que lanze un mensaje
                        informando que la BD se ha inicializado correctamente
        
                        -> Ya se puede ir al paso [3]
                3)
                pasar el control a un "GeneralControlador" (que gestionará el menú y
                ira gestionando que controlador especifico tiene que "trabajar" en fucnión 
                de lo que elija el usuario"
                De momento solo hace falta que este controlador le diga a un "MenuVista"
                que le diga que opción ha solicitado el usuario (la vista tendrá que, previamente,
                mostrar el menú).
        
                   
        
        */
        

    }
}

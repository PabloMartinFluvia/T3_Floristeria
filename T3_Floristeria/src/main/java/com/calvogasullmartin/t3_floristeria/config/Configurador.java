package com.calvogasullmartin.t3_floristeria.config;


/*
Responsabilidad de esta clase:
"poner a punto la app" // seria lo equivalente a un archivo de propiedades o configuración ->
    inyectar a las factorias abstractas las factorias concretas, para saber que "familia" se usa
*/
public interface Configurador {
    
    public void configurarPersistenciaFactory();
    
    public boolean validarBD();
}

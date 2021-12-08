package edu.fiuba.algo3.modelo;

public class ObjetoMuyValioso implements ObjetoRobado {

    private final int cantidadPaises = 7;
    private final String nombre;
    private Ciudad ciudad;
    private final String rareza = "muy valioso";

    public ObjetoMuyValioso(String nombre, Ciudad ciudad){

        this.nombre = nombre;
        this.ciudad = ciudad;

    }


    public ObjetoMuyValioso(String nombre){

        this.nombre = nombre;

    }

    public int cantidadPaises(){return this.cantidadPaises;}

    public String mostrarNombre(){

        return this.nombre;

    }

    @Override
    public Ciudad ciudad(){
        return this.ciudad;
    }

    @Override
    public String rareza(){
        return this.rareza;
    }
}

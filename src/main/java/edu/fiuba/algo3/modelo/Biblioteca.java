package edu.fiuba.algo3.modelo;

import java.util.HashMap;

public class Biblioteca implements LugarDeInteres{

    private HashMap<String,Pista> pistas;

    public Biblioteca() {

        this.pistas = new HashMap<String, Pista>();
    }
    @Override
    public void visitar(GradoDePolicia grado){

         this.obtenerPista(grado).mostrarPista();
    }

    @Override
    public Pista obtenerPista(GradoDePolicia grado){

        return grado.buscarPista(this.pistas);
    }
    @Override
    public void agregarPista(String dificultad, String descripcion){
        this.pistas.put(dificultad, new PistaFacil(descripcion));
    }

}

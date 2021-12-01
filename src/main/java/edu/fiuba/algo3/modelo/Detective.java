package edu.fiuba.algo3.modelo;

import java.util.HashMap;

public class Detective implements GradoDePolicia {
    private final int tiempoDeViaje = 1100;
    private final String nivelDePistaDisponible = "medio";
    public Detective(){
    }

    @Override
    public int calcularTiempoDeViaje(int kilometros) {
        return kilometros/(this.tiempoDeViaje);
    }


    @Override
    public Pista buscarPista(HashMap<String, Pista> pistas){
        return pistas.get(this.nivelDePistaDisponible);
    }
}
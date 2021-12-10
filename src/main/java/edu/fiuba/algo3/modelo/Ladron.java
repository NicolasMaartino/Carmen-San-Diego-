package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.objetos.ObjetoRobado;

import java.util.List;
import java.util.Objects;

public class Ladron{

    private String nombre;
    private List<Cualidad> cualidades;
    private ObjetoRobado objetoRobado;
    private Ciudad ciudadSiguiente;

    public Ladron (String nombre, List<Cualidad> cualidades){

        this.cualidades = cualidades;
        this.nombre = nombre;

    }

    public void asignarObjetoRobado(ObjetoRobado objeto){

        this.objetoRobado = objeto;

    }


    public int esIgual(Sospechoso sospechoso) {
        return sospechoso.esIgual(this.cualidades);
    }

    public void chequearPistas(List<String> pistasDelLadron, String descripcion, String nombreDelLadron) {
        if (this.nombre.equals(nombreDelLadron)){
            pistasDelLadron.add(descripcion);
        }
    }

    public void esAtrapado(Policia policia, int cantidadDePaisesVisitados) {
        this.objetoRobado.atrapar(policia, cantidadDePaisesVisitados);
    }
}


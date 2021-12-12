package edu.fiuba.algo3.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tiempo {

    private int hora;
    private final int tiempoLimite = 178;
    private final LocalDateTime tiempoInicio = LocalDateTime.parse("2021-12-12T07:00:00");
    private final String formato = "cccc', 'kk' Hs'.";
    public Tiempo(){

        this.hora = 0;

    }
    public void agregarHoras(float tiempoDeLaAccion) throws GameOverException {

        this.hora += tiempoDeLaAccion;

        if (this.finalizado()){
            throw new GameOverException();
        }
    }

    private boolean finalizado(){
        return (this.hora >= this.tiempoLimite);
    }

    public int tiempoTranscurrido() {

        return this.hora;

    }

    public String tiempoFormateado(){
        String hora = new String();
        LocalDateTime tiempoActual = tiempoInicio.plusHours(this.hora);
        DateTimeFormatter formatoDiaHs = DateTimeFormatter.ofPattern(formato);

        return tiempoActual.format(formatoDiaHs);
    }
}

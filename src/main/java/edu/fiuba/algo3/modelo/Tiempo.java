package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.GameOverException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Tiempo {

    private int hora;
    private final int tiempoLimite = 154;
    private final LocalDateTime tiempoInicio = LocalDateTime.parse("2021-12-13T07:00:00");
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

    public void debeDormir(int tiempoDeDescanso) throws GameOverException {
        int horaActual = tiempoInicio.plusHours(this.hora).getHour();
        if (horaActual > 20){
            this.agregarHoras(8);
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
        DateTimeFormatter formatoDiaHs = DateTimeFormatter.ofPattern(formato).withLocale(
                new Locale("en", "IN"));
        return tiempoActual.format(formatoDiaHs);
    }
}

package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.excepciones.GameOverException;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PartidaTests
{

    @Test
    public void creoUnaPartidaYPidoDosPistaLuegoViajaAMexicoYDuerme() throws GameOverException {
        InicializadorDeArchivos inicializadorDeArchivos = new InicializadorDeArchivos();
        Random mockDado = mock(Random.class);
        when(mockDado.nextInt(3)).thenReturn(1);
        when(mockDado.nextInt(7)).thenReturn(1);
        Partida partida = new Partida(inicializadorDeArchivos,mockDado);
        partida.nuevoCaso(3);
        Pista pista = partida.entrarEdificio("Banco");
        assertEquals("Cambio sus monedas a pesos argentinos",pista.descripcion());
        pista = partida.entrarEdificio("Banco");
        assertEquals("Cambio sus monedas a pesos argentinos",pista.descripcion());
        partida.viajar("Ciudad de Mexico");

        assertEquals("Monday, 17 Hs.",partida.hora());

    }

}


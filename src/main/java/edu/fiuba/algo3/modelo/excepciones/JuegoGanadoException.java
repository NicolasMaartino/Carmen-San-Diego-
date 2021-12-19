package edu.fiuba.algo3.modelo.excepciones;


import javafx.scene.image.Image;

public class JuegoGanadoException extends RuntimeException {
    private static final String descripcion = "Has ganado el juego!";
    private static final Image imagen = new Image("file:src/main/resources/fotos/avisos/trofeo.png");

    public String descripcion() {
        return this.descripcion;
    }

    public Image imagen(){
        return this.imagen;
    }

}

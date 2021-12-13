package edu.fiuba.algo3.modelo.ordenDeArresto;

import edu.fiuba.algo3.modelo.Ladron;
import edu.fiuba.algo3.modelo.excepciones.NoHayLadronesException;

import java.util.List;

public class OrdenSinEmitir implements OrdenDeArresto {
    public OrdenSinEmitir(){
    }
    @Override
    public OrdenDeArresto emitir(List<Ladron> ladronesPosibles) {
        if (ladronesPosibles.size() == 0){
            throw new NoHayLadronesException();
        }else if(ladronesPosibles.size() == 1){
            return (new OrdenEmitida(ladronesPosibles.get(0)));
        }
        return (this);
    }
    @Override
    public boolean emitida(){
        return false;
    }
}
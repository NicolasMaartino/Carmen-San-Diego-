package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.comisaria.Cualidad;
import edu.fiuba.algo3.modelo.comisaria.Ladron;
import edu.fiuba.algo3.modelo.objetos.*;
import edu.fiuba.algo3.modelo.ubicacion.Ciudad;
import edu.fiuba.algo3.modelo.ubicacion.Mapa;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class InicializadorDeArchivos {
    private final String RUTA_PISTAS_LUGARES = "src/main/java/edu/fiuba/algo3/modelo/archivos/pistasDeCiudades.csv";
    private final String RUTA_PISTAS_LADRONES = "src/main/java/edu/fiuba/algo3/modelo/archivos/pistasLadrones.csv";
    private final String RUTA_CIUDADES = "src/main/java/edu/fiuba/algo3/modelo/archivos/ciudades.csv";
    private final String RUTA_DESTINOS = "src/main/java/edu/fiuba/algo3/modelo/archivos/destinos.csv";
    private final String RUTA_LADRONES = "src/main/java/edu/fiuba/algo3/modelo/archivos/ladrones.csv";
    private final String RUTA_OBJETOS = "src/main/java/edu/fiuba/algo3/modelo/archivos/objetos.csv";

    public InicializadorDeArchivos() {
    }

    public List<Ladron> cargarLadrones() throws IOException {
        List<Ladron> ladrones = new ArrayList<>();

        Reader in = new FileReader(RUTA_LADRONES);
        Iterable<CSVRecord> texto = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord linea : texto) {
            String nombre = linea.get("Nombre");
            String sexo = linea.get("Sexo");
            String hobby = linea.get("Hobby");
            String pelo = linea.get("Pelo");
            String senia = linea.get("Senia");
            String vehiculo = linea.get("Vehiculo");
            List<Cualidad> cualidades = new ArrayList<>();
            cualidades.add(new Cualidad(sexo));
            cualidades.add(new Cualidad(hobby));
            cualidades.add(new Cualidad(pelo));
            cualidades.add(new Cualidad(senia));
            cualidades.add(new Cualidad(vehiculo));
            Ladron actual = new Ladron(nombre, cualidades);
            ladrones.add(actual);
        }
        return ladrones;
    }

    public Map<String , Ciudad> cargarCiudades() throws IOException {
        Map<String, Ciudad> ciudades = new HashMap<>();

        Reader in = new FileReader(RUTA_CIUDADES);
        Iterable<CSVRecord> texto = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for ( CSVRecord linea : texto ) {
            String nombreCiudad = linea.get("Ciudad");
            //float latitud = Float.parseFloat(linea.get("Latitud"));
            //float longitud = Float.parseFloat(linea.get("Longitud"));
            Ciudad actual = new Ciudad(nombreCiudad);
            ciudades.put(nombreCiudad, actual);
        }

        return ciudades;

    }

    public void cargarDestinos(Map<String,Ciudad>ciudades) throws IOException {


        Reader in = new FileReader(RUTA_DESTINOS);
        Iterable<CSVRecord> texto = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for ( CSVRecord linea : texto ) {
            Ciudad ciudad = ciudades.get(linea.get("Ciudad"));
            String destino1 = linea.get("Destino1");
            String destino2 = linea.get("Destino2");
            String destino3 = linea.get("Destino3");
            ciudad.agregarDestino(destino1);
            ciudad.agregarDestino(destino2);
            ciudad.agregarDestino(destino3);
        }

    }

    public Mapa cargarMapa (Map<String,Ciudad> ciudades) throws IOException {
        Mapa mapa = new Mapa();

        Reader in = new FileReader(RUTA_CIUDADES);
        Iterable<CSVRecord> texto = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord linea : texto) {
            String nombreCiudad = linea.get("Ciudad");
            float latitud = Float.parseFloat(linea.get("Latitud"));
            float longitud = Float.parseFloat(linea.get("Longitud"));
            Ciudad actual = ciudades.get(nombreCiudad);
            mapa.agregarCiudad(actual, latitud, longitud);
        }
        return mapa;
    }

    public Map<String,Ciudad> cargarPistasLugares(Map<String, Ciudad> ciudades, List<String> pistasDelLadron,Random dado) throws IOException {

        Reader in = new FileReader(RUTA_PISTAS_LUGARES);
        Iterable<CSVRecord> texto = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for ( CSVRecord linea : texto ) {
            String descripcion = linea.get("Pista");
            String ciudad = linea.get("Ciudad");
            String dificultad = linea.get("Dificultad");
            String lugar = linea.get("Lugar");

            Pista pista = new Pista(new Dificultad(dificultad), descripcion, lugar);
            pista.asignarPistaDeLadron(pistasDelLadron.get(dado.nextInt(pistasDelLadron.size()-1)));
            Ciudad ciudadActual = ciudades.get(ciudad);
            ciudadActual.agregarPista(pista);
            ciudadActual.agregarLugar(lugar);

            }

        return ciudades;
    }

    public List<ObjetoRobado> cargarObjetosRobados(Map<String,Ciudad> ciudades) throws IOException {
        List<ObjetoRobado> objetosRobados = new ArrayList<>();

        Reader in = new FileReader(RUTA_OBJETOS);
        Iterable<CSVRecord> texto = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord linea : texto) {
            String tesoro = linea.get("Tesoro");
            String ciudad = linea.get("Ciudad");
            String valor = linea.get("Valor");
            ObjetoRobado actual;
            Ciudad ciudadInicial = ciudades.get(ciudad);
            if(valor.equals("Comun")){
                actual = new ObjetoComun(tesoro, ciudadInicial);
            }
            else if(valor.equals("Valioso")){
                actual = new ObjetoValioso(tesoro, ciudadInicial);
            }
            else{
                actual = new ObjetoMuyValioso(tesoro, ciudadInicial);
            }
            objetosRobados.add(actual);
        }
    return objetosRobados;
    }

    public List<String> cargarPistasDescripcionLadron(Ladron ladron) throws IOException {

        Reader in = new FileReader(RUTA_PISTAS_LADRONES);
        Iterable<CSVRecord> texto = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        List<String> pistasDelLadron = new ArrayList<>();
        for (CSVRecord linea:texto) {
            String descripcion = linea.get("Pista");
            String ladronArchivo = linea.get("Ladron");
            ladron.chequearPistas(pistasDelLadron, descripcion, ladronArchivo);
        }
        return pistasDelLadron;
    }

}
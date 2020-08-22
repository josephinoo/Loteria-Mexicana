package Models;

import componetes.Jugador;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Encargada de la leida, y actualizacion del Reporte, a su vez posee el Observable List de la tabla a mostrar
 * @author Grupo 10
 *
 */
public class Report {

    public static ObservableList<Jugador> jugadores= FXCollections.observableArrayList();

    public Report() {
        leerArchivo();

    } 

    public static void leerArchivo() {
        BufferedReader csvReader = null;
        try {
            String ruta = "src/files/report.csv"; //ruta del archivo que se va a leer
            csvReader = new BufferedReader(new FileReader(ruta));
            String fila = csvReader.readLine();
            while ((fila = csvReader.readLine()) != null) { //iterar en el contenido del archivo
                String[] data = fila.split(",");
                Jugador jugador=new Jugador(data[0], data[1], data[2], data[3], data[4]);
                jugadores.add(jugador);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);

                try {
                    csvReader.close();
                } catch (IOException e) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } 
        public static  void actualizarArchivo (ArrayList<Jugador> jugadores) {
            FileWriter writer = null;
            try {
                String ruta = "src/files/report.csv"; //ruta del archivo que se va a leer
                writer = new FileWriter(ruta);
                
                //PROBAR ARCHIVO ESCRIBIENDO SOLO UN JUGADOR
                writer.write("Fecha,Duracion,Nombre,Oponente,Alineacion" + System.lineSeparator());
                
                for (Jugador est : jugadores) {
                                    writer.write(est.getFecha() + "," + est.getDuracion() + "," + est.getName() + "," + est.getOponentes() + "," + est.getAlinicacion() + System.lineSeparator());
                }
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        public static void setJugadores (Jugador jugador) {
        	jugadores.add(jugador);
        }
    }


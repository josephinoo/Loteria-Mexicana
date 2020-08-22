package Models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import music.Sound;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
//*
/**
 * Clase Encargada de la carga del mazo de cartas, las toma del archivo CSV proporcionado por la Instructora
 * @author Grupo 10
 *
 */
public class MazoOfCards {
    public ArrayList<Card> cards ;
    public MazoOfCards(){
       cards= new ArrayList<Card>();
    }


    public ArrayList<Card> mazodecartas(){
        BufferedReader csvReader = null;
        try {
            String ruta = "src/files/cards.csv"; //ruta del archivo que se va a leer
            csvReader = new BufferedReader(new FileReader(ruta));
            String fila = csvReader.readLine();
            while ((fila = csvReader.readLine()) != null) { //iterar en el contenido del archivo
                String[] data = fila.split(",");
                cards.add(new Card(data[0],data[1],"resources/cards/"+data[0]+".PNG"));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MazoOfCards.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MazoOfCards.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                csvReader.close();
            } catch (IOException ex) {
                Logger.getLogger(MazoOfCards.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cards;
    }

    public String[][] cardsRandom(){
        MazoOfCards mazo=new MazoOfCards();

        ArrayList<Card> cartas= mazo.mazodecartas();

        Collections.shuffle(cartas);
        String[][] cards = {{cartas.get(0).getImage(),cartas.get(1).getImage(),cartas.get(2).getImage(),cartas.get(3).getImage()},
                {cartas.get(4).getImage(),cartas.get(5).getImage(),cartas.get(6).getImage(),cartas.get(7).getImage()},
                {cartas.get(8).getImage(),cartas.get(9).getImage(),cartas.get(10).getImage(),cartas.get(11).getImage()},
                {cartas.get(12).getImage(),cartas.get(13).getImage(),cartas.get(14).getImage(),cartas.get(15).getImage()}};
        return cards;

    }

    
}



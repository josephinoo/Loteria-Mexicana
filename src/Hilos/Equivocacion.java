package Hilos;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import Models.Card;
//import com.sun.deploy.net.MessageHeader;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Clase Encargada de la superposicion de la imagen X, que ocurre cuando alguien selecciona una carta Incorrecta
 * @author Grupo10
 *
 */
public class Equivocacion extends Thread{
    ImageView[][] btn;
    int finalI;
    int finalJ;
    GridPane tablet;



    public Equivocacion(ImageView[][] btn,int finalI,int finalJ,GridPane tablet){
        this.btn=btn;
        this.finalI=finalI;
        this.finalJ=finalJ;
        this.tablet=tablet;



    }

        public void run () {
            Platform.runLater(()->{
                try {
                    InputStream is = null;
                    is = Files.newInputStream(Paths.get("src/resources/x.png"));
                    ImageView image2 = new ImageView(new Image(is));
                    image2.setFitWidth(90);
                    image2.setFitHeight(120);
                    image2.setTranslateX(0);


                    btn[finalI][finalJ] = image2;
                    tablet.add(btn[finalI][finalJ], finalI, finalJ);
                    siesta(5);


                    Platform.runLater(()->{
                        siesta(300);
                        Platform.runLater(()->{

                            siesta(400);
                            tablet.getChildren().remove(btn[finalI][finalJ]);

                        });

                        System.out.println("ffffff");


                    });



 



                } catch (IOException e) {
                    e.printStackTrace();
                }







            });









        }







    public void siesta(int sec){
        try{
            Thread.currentThread().sleep(sec);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

}

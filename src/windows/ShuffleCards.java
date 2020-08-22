package windows;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import Models.Card;
import comprobadores.VerificarMatriz;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import loteriamexicana.LoteriaMexicana;
/**
 * Clase Hilo Representativo para el cambio de cartas de manera controlada y con tiempo del Jugador y del primer oponente, junto con su generador de contenido o ventana
 * @author Grupo10
 *
 */
public class ShuffleCards extends Thread {
	private ImageView imageVwc;
    private Image imagec;
    private ArrayList<Card> cartase;
    public static  ArrayList<String> cartaAl=new ArrayList<>();
    private String[][] cartasaletorios2;
    ImageView [][] btn2;
    private GridPane tablet2;
    private volatile  boolean detener = true;
    HashMap<String,int[] > mapaComputadora;
    int[][] matrizCompudora;
    public ShuffleCards(ImageView imageVwc, Image imagec,ArrayList<Card> cartase,String[][] cartasaletorios2,ImageView [][] btn2, GridPane tablet2,HashMap<String,int[] > mapaComputadora,int[][] matrizCompudora){
        this.imageVwc = imageVwc;
        this.imagec = imagec;
        this.cartase = cartase;
        this.cartasaletorios2=cartasaletorios2;
        this.btn2=btn2;
        this.tablet2=tablet2;
        this.mapaComputadora=mapaComputadora;
        this.matrizCompudora=matrizCompudora;
    }
    
    /**
     * Cambia la imagen en el contenedor especificado cada 2 segundos y regresa a la inicial al finalizar
     */
    @Override
    

    public void run() {

        while (!Thread.interrupted()) {
            for (Card carta : cartase) {
                String path = carta.getImage();
                File romper1 = new File(path);

                String ruta1 = null;

                try {
                    ruta1 = romper1.toURI().toURL().toString();

                } catch (MalformedURLException e) {
                    System.out.println(e.getMessage());
                }
                
                Image rota1;
                try {
                    rota1 = new Image(new FileInputStream("src/" + carta.getImage()));
                    imageVwc.setImage(rota1);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                cartaAl.add(carta.getImage());
                siesta();

                //System.out.println(ShuffleCards.cartaAl.get(0));
                ArrayList<String> valoresA = new ArrayList<>();

                for (int i = 0; i < 4; i++)
                    for (int j = 0; j < 4; j++)
                        valoresA.add(cartasaletorios2[i][j]);


                if (valoresA.contains(ShuffleCards.cartaAl.get(0))) {
                    
                    InputStream is = null;
                    int[] valores = mapaComputadora.get(cartaAl.get(0));
                    try {
                        is = Files.newInputStream(Paths.get("src/resources/frijo2.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    ImageView image2 = new ImageView(new Image(is));
                    image2.setFitWidth(25);
                    image2.setFitHeight(25);
                    image2.setTranslateX(0);
                    btn2[valores[0]][valores[1]] = image2;
                    Platform.runLater(() -> {

                        tablet2.add(btn2[valores[0]][valores[1]], valores[0], valores[1]);
                        matrizCompudora[valores[0]][valores[1]] = 1;
                    });


                    //ubicar primera imagen
                    imageVwc.setImage(imagec);

                }
                cartaAl.clear();

                if (ComprobarAlinaciones(matrizCompudora, Aleniacion.alineacionParaJugar)) {
                    
                    interrupt();
                	if (ShuffleCardsC2.currentThread().isAlive()) {
                    	ShuffleCardsC2.currentThread().interrupt();}
                	
                    Platform.runLater(()->{
                        Lose ventana = new Lose();
                        Scene scene = new Scene(ventana.getRoot(), 860, 600);
                        LoteriaMexicana.stage.setScene(scene);
                        
                        
                        Platform.runLater(()->{
                        	
                            detenerlo();

                        });




                    });
                }

            }
        }
    }
    
    /**
     *Pausa el hilo durante 2 segundos 
     */
    public void siesta(){
        try{
            sleep(3000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
    public void siestalose(){
        try{
            sleep(500);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
    public boolean ComprobarAlinaciones(int M[][],String alineacion){
        boolean retonar=false;
        switch (alineacion){

            case "Verticial-Horizontal":
                retonar= VerificarMatriz.comprobar(M);
                break;
            case "Esquinas":
                retonar=VerificarMatriz.esquinasCom(M);
                break;
            case "Medio":
                retonar=VerificarMatriz.medioCards(M);
                break;
            case "trazaDerecha":
                retonar=VerificarMatriz.diagonalCom(M);
                break;
            case "trazaIzquierda":
                retonar=VerificarMatriz.sumarTrazaIzquierda(M);
                break;
        }

    return retonar;
    }

    public void detenerlo()
    {
        detener=false;
    }
}


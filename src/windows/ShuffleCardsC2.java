package windows;

import Models.Card;
import comprobadores.VerificarMatriz;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import loteriamexicana.LoteriaMexicana;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Clase Hilo encargado de la baraja de cartas del segundo oponente, junto con su generador de contenido o ventana
 * @author Grupo10
 *
 */
public class ShuffleCardsC2 extends Thread{
    private ImageView imageVwc;
    private Image imagec;
    private ArrayList<Card> cartase;
    public static  ArrayList<String> cartaAl=new ArrayList<>();
    private String[][] cartasaletorios3;
    ImageView [][] btn3;
    private GridPane tablet3;
    HashMap<String,int[] > mapaComputadora1;
    int[][] matrizCompudora2;
    public ShuffleCardsC2(ImageView imageVwc, Image imagec,ArrayList<Card> cartase,String[][] cartasaletorios3,ImageView [][] btn3, GridPane tablet3,HashMap<String,int[] > mapaComputadora1,int[][] matrizCompudora2){
        this.imageVwc = imageVwc;
        this.imagec = imagec;
        this.cartase = cartase;
        this.cartasaletorios3=cartasaletorios3;
        this.btn3=btn3;
        this.tablet3=tablet3;
        this.mapaComputadora1=mapaComputadora1;
        this.matrizCompudora2=matrizCompudora2;
    }



    /**
     * Cambia la imagen en el contenedor especificado cada 2 segundos y regresa a la inicial al finalizar
     */
    @Override


    public void run() {
        //ArrayList<ImageView> cPasadas=
    	while (!Thread.interrupted()) {
        for (Card carta : cartase) {
            /*
        	String path = carta.getImage();
            File romper1 = new File(path);

            String ruta1 = null;

            try {
                ruta1 = romper1.toURI().toURL().toString();

            } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
            }
            //System.out.println(ruta1);

//                Image rota1 = new Image(ruta1, 200, 200, true, true);
            Image rota1;
            try {
                rota1 = new Image(new FileInputStream("src/" + carta.getImage()));
                imageVwc.setImage(rota1);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            */
            cartaAl.add(carta.getImage());
            siesta();
            //System.out.println(ShuffleCards.cartaAl.get(0));
            ArrayList<String> valoresA=new ArrayList<>();

            for(int i=0;i<4;i++)
                for(int j=0;j<4;j++)
                    valoresA.add(cartasaletorios3[i][j]);
            
            if(valoresA.contains(ShuffleCardsC2.cartaAl.get(0))) {
                ;
                InputStream is = null;
                int[] valores =mapaComputadora1.get(cartaAl.get(0));

                try {
                    is = Files.newInputStream(Paths.get("src/resources/frijo2.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ImageView image2 = new ImageView(new Image(is));
                image2.setFitWidth(25);
                image2.setFitHeight(25);
                image2.setTranslateX(0);
                btn3[valores[0]][valores[1]] = image2;

                Platform.runLater(()->{
                    tablet3.add(btn3[valores[0]][valores[1]], valores[0], valores[1]);
                    matrizCompudora2[valores[0]][valores[1]] = 1;
                });


                //ubicar primera imagen
                //imageVwc.setImage(imagec);

            }
            cartaAl.clear();
            
            if (ComprobarAlinaciones(matrizCompudora2, Aleniacion.alineacionParaJugar)) {
            	interrupt();
                ShuffleCards.currentThread().interrupt();
                Platform.runLater(()->{
                    Lose ventana = new Lose();
                    Scene scene = new Scene(ventana.getRoot(), 860, 600);
                    LoteriaMexicana.stage.setScene(scene);
                    
                    Platform.runLater(()->{
                        detenerlo();
                        
                    });




                });
            }
        }}}

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
        boolean detener = false;
    }
    
}
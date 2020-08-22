package windows;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import Hilos.Equivocacion;
import Models.Card;
import Models.MazoOfCards;
import Models.Report;
import componetes.Jugador;

import comprobadores.VerificarMatriz;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import loteriamexicana.LoteriaMexicana;
import music.Sound;
/**
 * Clase Representativa de los atributos des tablas, junto con su generador de contenido o ventana
 * @author Grupo10
 *
 */
public class Tabletofthecards   {
    BorderPane  root ;
    GridPane  tablet;
    GridPane tablet2;
    GridPane tablet3;
    HBox change;
    VBox vbox ;
    Date fecha = new Date();
    int minutes = fecha.getMinutes();
    int seconds = fecha.getSeconds();
    GridPane changes;
    ArrayList<String> cartaeAle=new ArrayList<>();
    //computador
    ImageView [][] btn2;
    ImageView [][]btn3;
    String[][] cartasaletorios2;
    HashMap<String,int[] >mapaComputadora;

    ImageView image2;

   //Matrices para jugar

    int[][] matrizJugador= {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
    int[][] matrizCompudora1= {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
    int[][] matrizCompudora2= {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};


    //Matrices para jugar
    menu ventanaMenu;
    Image imag1;
    ImageView imag;
    ArrayList<Card> cartase;
    public Pane getRoot(){
        return root;

    }

    public Tabletofthecards() {
        createContent();
    }
    private void createContent()  {

        root= new BorderPane();


        try (InputStream is = Files.newInputStream(Paths.get("src/resources/background-login.png"))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(860);
            img.setFitHeight(600);
            root.getChildren().add(img);

        }
        catch (IOException e) {
            System.out.println("Couldn't load image");
        }
       
        tablet=new GridPane();

        ImageView [][] btn = new ImageView[4][4];

        MazoOfCards mazo = new MazoOfCards();
        String[][] cartasaletorios = mazo.cardsRandom();
        System.out.println(cartasaletorios);
        System.out.println(cartasaletorios);
        //Pane root2 = new Pane();
        MazoOfCards mazoe = new MazoOfCards();
        cartase = mazoe.mazodecartas();
        Collections.shuffle(cartase);
        for(Card cartagg:cartase){
            cartaeAle.add(cartagg.getImage());
 
        }
        ImageView loteriaGanar= new ImageView("resources/loteria.png");
        loteriaGanar.setFitHeight(40);
        loteriaGanar.setFitWidth(160);
        loteriaGanar.setTranslateX(-75);
        System.out.println("pilas:     "+Aleniacion.alineacionParaJugar);
        loteriaGanar.setOnMouseClicked(event ->{
            if(ComprobarAlinaciones(matrizJugador,Aleniacion.alineacionParaJugar)){
            	
            	String nombreJugador=login.textField1.getText();
                //System.out.println (fecha);
                String duracion = tiempo(minutes, seconds);
                Jugador jugador = new Jugador(String.valueOf(fecha),duracion,nombreJugador,settings.oponentes,Aleniacion.alineacionParaJugar);
                Report.setJugadores (jugador);
                ObservableList<Jugador> personas= Report.jugadores;
                //agregar los datos del arraylist al tableview
                //table.getItems().add(jugador);
                ArrayList<Jugador> jugado = new ArrayList<Jugador>() ;
                for(Jugador p:personas){
                    jugado.add(p);
                    //automaticamente los campos del objeto se agregaran a la columna respectivo
                }
                Collections.sort(jugado);
                Report.actualizarArchivo(jugado);
                Ganador ventana = new Ganador();
                Scene scene = new Scene(ventana.getRoot(),860,600);
                LoteriaMexicana.stage.setScene(scene);
                System.out.println("Ganaste");
                
                ShuffleCards.currentThread().interrupt();
                if (ShuffleCardsC2.currentThread().isAlive()) {
                	ShuffleCardsC2.currentThread().interrupt();
                }
                

            }else {
                Sound.error();
            }
                }
        );
//Parte 2
        vbox = new VBox();




        tablet.setAlignment(Pos.CENTER);
        root.setCenter(tablet);
        //root.setLeft(vbox);
        //Fin de parte 2
        imag1 = new Image(cartase.get(0).getImage());
        imag = new ImageView();
        imag.setImage(imag1);
        imag.setTranslateX(-75);
        imag.setTranslateY(200);
        imag.setFitHeight(200);
        imag.setFitWidth(150);
        VBox parteIzquierda = new VBox();
        parteIzquierda.setPadding(new Insets(10, 0, 50, -10));
        parteIzquierda.setSpacing(-50);
        parteIzquierda.getChildren().addAll(imag,loteriaGanar);
        root.setRight(parteIzquierda);



        for(int i=0; i<btn.length; i++){
            for(int j=0; j<btn.length;j++){
                int finalJ = j;
                int finalI = i;
                ImageView image=new ImageView(cartasaletorios[i][j]);
                image.setFitWidth(90);
                image.setFitHeight(120);
                btn[finalI][finalJ]=image;



                tablet.add(btn[i][j], i, j);
                image.setOnMouseClicked(event -> {
                    InputStream is = null;
                    //Arrays.asList(permitidos).contains(numero);

                    if(ShuffleCards.cartaAl.contains(cartasaletorios[finalI][finalJ])) {
                        matrizJugador[finalI][finalJ]=1;
                        Sound.correct();
                        try {
                            is = Files.newInputStream(Paths.get("src/resources/frijo2.png"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        image2 = new ImageView(new Image(is));
                        image2.setFitWidth(50);
                        image2.setFitHeight(50);
                        image2.setTranslateX(25);
                        btn[finalI][finalJ] = image2;

                        tablet.add(btn[finalI][finalJ], finalI, finalJ);
                    }else{
                        Equivocacion equivocacion=new Equivocacion(btn,finalI,finalJ,tablet);
                        Sound.error();
                        equivocacion.run();

                    }
                });


            }
        }



        HBox vbox1 = new HBox();


        ;

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox1.setAlignment(Pos.TOP_CENTER);

        //root.setLeft(vbox1);
        //root.setRight(vbox);


        if (settings.getOponentes().equals("1")) {
            try {
                computador();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            vbox.getChildren().add(tablet2);
            vbox.setTranslateX(60);
            vbox.setTranslateY(50);
            vbox.setPadding(new Insets(50,0,0,0));
            root.setLeft(vbox);
        }
        if (settings.getOponentes().equals("2")){
            ImageView[][] btn3 = new ImageView[4][4];
            try {
                computador();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            tablet3= new GridPane();

            System.out.println("ENTRA AL IF");
            MazoOfCards mazo3 = new MazoOfCards();

            String[][] cartasaletorios3 = mazo3.cardsRandom();

            HashMap<String,int[] >mapaComputadora1=new HashMap<>();
            for(int i=0; i<btn3.length; i++){
                for(int j=0; j<btn3.length;j++){
                    ImageView image3=new ImageView(cartasaletorios3[i][j]);
                    image3.setFitWidth(30);
                    image3.setFitHeight(45);
                    btn3[i][j]=image3;
                    mapaComputadora1.put(cartasaletorios3[i][j], new int[]{i, j});
                    vbox.setTranslateX(60);
                    vbox.setTranslateY(50);
                    tablet3.add(btn3[i][j], i, j);



                }
            }





            vbox.getChildren().add(tablet3);
            vbox.getChildren().add(tablet2);
            root.setLeft(vbox);
            ShuffleCardsC2 suf1 = new ShuffleCardsC2(imag, imag1,cartase,cartasaletorios3,btn3,tablet3,mapaComputadora1,matrizCompudora2);
            suf1.start();

        }

        tablet.setAlignment(Pos.CENTER);
        root.setCenter(tablet);



 
    }
    public String tiempo(int minutes,int seconds){
        LocalDateTime locaDate2 = LocalDateTime.now();
        int minutes2 = locaDate2.getMinute();
        int seconds2 = locaDate2.getSecond();
        int valor1=minutes2-minutes;
        int valor2=seconds2-seconds;
        if(valor2<0)
            valor2=valor2*-1;

        String duracion= String.valueOf(valor1)+":"+String.valueOf(valor2);
        	return duracion;
    }
    public void computador() throws FileNotFoundException {
        tablet2 = new GridPane();
        btn2 = new ImageView[4][4];
        MazoOfCards mazo2 = new MazoOfCards();
        String[][] cartasaletorios2 = mazo2.cardsRandom();
        mapaComputadora=new HashMap<>();
        for(int i=0; i<btn2.length; i++){
            for(int j=0; j<btn2.length;j++){
                ImageView image2=new ImageView(cartasaletorios2[i][j]);
                if (settings.getVisibilidad().equals("Oculto")){
                    image2= new ImageView(new Image(new FileInputStream("src/resources/blankspace.png")));
                }
                image2.setFitWidth(30);
                image2.setFitHeight(45);
                btn2[i][j]=image2;
                mapaComputadora.put(cartasaletorios2[i][j], new int[]{i, j});
                tablet2.add(btn2[i][j], i, j);



            }
        }

        ShuffleCards suf = new ShuffleCards(imag, imag1,cartase,cartasaletorios2,btn2,tablet2,mapaComputadora,matrizCompudora1);
        suf.start();
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

}
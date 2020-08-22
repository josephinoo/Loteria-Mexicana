package windows;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;

import Models.Report;
import componetes.Jugador;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import loteriamexicana.LoteriaMexicana;

/**
 * Clase Representativa de los atributos del login, junto con su generador de contenido o ventana
 * @author Grupo10
 *
 */
public class login   {
    public static String nombreJugador=null;
    Pane  root ;

    menu ventanaMenu;
    Jugador jugador;
    Date fecha = new Date();
    int minutes = fecha.getMinutes();
    int seconds = fecha.getSeconds();
    public static TextField textField1;
    public Pane getRoot(){
        return root;

    }
    public login(menu ventanaMenu){
       this.ventanaMenu=ventanaMenu;
       createContent();

    }


    private void createContent() {
        root= new Pane();

        try (InputStream is = Files.newInputStream(Paths.get("src/resources/background-login.png"))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(860);
            img.setFitHeight(600);
            root.getChildren().add(img);

        }
        catch (IOException e) {
            System.out.println("Couldn't load image");
        }

        Title title = new Title("LOGIN");
        title.setTranslateX(300);
        title.setTranslateY(130);





        root.getChildren().addAll(title );
        textField1 = new TextField();



        textField1.setTranslateX(350);
        textField1.setTranslateY(250);

        //Creating a Grid Pane

        //Setting size for the pane

        root.getChildren().addAll(textField1);




        //Creating Text Filed for email
   ;
        // Adding images

 

        //Creating Buttons
        menu.MenuItem itemNewGame=new menu.MenuItem("Enter");

        menu.MenuItem itemExit=new menu.MenuItem("Back");
        menu.MenuItem pruba=new menu.MenuItem("probar");
        pruba.setOnMouseClicked(event -> {
            System.out.println(textField1.getText());
            nombreJugador=textField1.getText();
            //System.out.println (fecha);
             
            String duracion = tiempo(minutes, seconds);
            jugador = new Jugador(String.valueOf(fecha),duracion,nombreJugador,settings.oponentes,"alinicacion");
            Report.setJugadores (jugador);
            Ganador ventana = new Ganador();
            Scene scene = new Scene(ventana.getRoot(),860,600);
            LoteriaMexicana.stage.setScene(scene);
        });
        itemExit.setOnMouseClicked(event ->{
            menu ventana = new menu();
            Scene scene = new Scene(ventana.getRoot(),860,600);
            LoteriaMexicana.stage.setScene(scene);
                                });

        menu.MenuBox menu = new menu.MenuBox(
                itemNewGame,itemExit,pruba);
        menu.setTranslateX(330);
        menu.setTranslateY(300);

        root.getChildren().addAll(menu);


        itemNewGame.setOnMouseClicked(event -> {
            System.out.println(textField1.getText());
            Aleniacion ventana = new Aleniacion();
            Scene scene = new Scene(ventana.getRoot(),860,600);
            LoteriaMexicana.stage.setScene(scene);;
        });
    }
    private static class Title extends StackPane {
        public Title(String name) {
            Rectangle bg = new Rectangle(250, 60);
            bg.setStroke(Color.WHITE);
            bg.setStrokeWidth(2);
            bg.setFill(null);

            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Hey Fun", FontWeight.SEMI_BOLD, 50));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);
        }
    }
    private static class MenuBox extends VBox {
        public MenuBox(menu.MenuItem... items) {
            getChildren().add(createSeparator());

            for (menu.MenuItem item : items) {
                getChildren().addAll(item, createSeparator());
            }
        }

        private Line createSeparator() {
            Line sep = new Line();
            sep.setEndX(200);
            sep.setStroke(Color.DARKGREY);
            return sep;
        }
    }

    static class MenuItem extends StackPane {
        public MenuItem(String name) {
            LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                    new Stop(0, Color.WHITE),
                    new Stop(0.1, Color.BLACK),
                    new Stop(0.9, Color.BLACK),
                    new Stop(1, Color.WHITE)
            });

            Rectangle bg = new Rectangle(200, 30);
            bg.setOpacity(0.4);

            Text text = new Text(name);
            text.setFill(Color.DARKGREY);
            text.setFont(Font.font("Hey Fun", FontWeight.SEMI_BOLD, 22));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                bg.setFill(gradient);
                text.setFill(Color.WHITE);
            });


            setOnMouseExited(event -> {
                bg.setFill(Color.BLACK);
                text.setFill(Color.DARKGREY);
            });

            setOnMousePressed(event -> {
                bg.setFill(Color.DARKVIOLET);
            });

            setOnMouseReleased(event -> {
                bg.setFill(gradient);
            });
        }
    }
 // Este codiog para medir el tiempo de duracion del juego
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

}

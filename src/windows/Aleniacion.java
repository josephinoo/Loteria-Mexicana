package windows;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import loteriamexicana.LoteriaMexicana;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
/**
 * Clase que posee las caracteristicas de Alineacion del juego, ademas crea el contenido para antes del juego.
 * @author Grupo 10
 *
 */
public class Aleniacion   {
    BorderPane root ;
    public static String  alineacion;
    public static String alineacionParaJugar;
    public ArrayList<String> alineaciones=new ArrayList<>();


 
    menu ventanaMenu;
    public Pane getRoot(){
        return root;

    }
    public Aleniacion(menu ventanaMenu){
        this.ventanaMenu=ventanaMenu;
        createContent();

    }
    public Aleniacion(){
        createContent();

    }
    
    

    private void createContent() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Verticial-Horizontal","src/resources/aleniaciones/Vetical-Horizontal.png");
        map.put("Esquinas","src/resources/aleniaciones/Esquinas.png");
        map.put("Medio","src/resources/aleniaciones/Medio.png");
        map.put("trazaDerecha","src/resources/aleniaciones/trazaDerecha.png");
        map.put("trazaIzquierda","src/resources/aleniaciones/trazaIzquierda.png");
        alineaciones.add("Verticial-Horizontal");
        alineaciones.add("Esquinas");
        alineaciones.add("Medio");
        alineaciones.add("trazaDerecha");
        alineaciones.add("trazaIzquierda");
        Collections.shuffle(alineaciones);


        root= new BorderPane();

        try (InputStream is = Files.newInputStream(Paths.get("src/resources/fondoAlineacion.png"))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(860);
            img.setFitHeight(600);
            root.getChildren().add(img);

        }
        catch (IOException e) {
            System.out.println("Couldn't load image");
        }

        //Creating Buttons
        menu.MenuItem itemNewGame=new menu.MenuItem("ENTER AND GAME");

        menu.MenuItem itemExit=new menu.MenuItem("Back");
        itemExit.setOnMouseClicked(event ->{
            login ventana = new login(ventanaMenu);
            Scene scene = new Scene(ventana.getRoot(),860,600);
            LoteriaMexicana.stage.setScene(scene);;
            });

        menu.MenuBox menu = new menu.MenuBox(
                itemNewGame,itemExit);
        menu.setTranslateX(330);
        menu.setTranslateY(500);

        itemNewGame.setOnMouseClicked(event -> {
        	
        	Tabletofthecards ventana = new Tabletofthecards();
            Scene scene = new Scene(ventana.getRoot(),860,600);
            LoteriaMexicana.stage.setScene(scene);
            
        });
        
        
        
        try{
        	alineacion=map.get(alineaciones.get(0));
            alineacionParaJugar=alineaciones.get(0);
        	InputStream is2 = Files.newInputStream(Paths.get(alineacion));
            ImageView image = new ImageView(new Image(is2));
            image.setTranslateX(330);
            image.setTranslateY(230);
            image.setFitWidth(200);
            image.setFitHeight(250);
            root.getChildren().add(image);

        }catch (Exception e){
            System.out.println("No se enceutra la imagen");
        }



        //Creating a Grid Pane

        //Setting size for the pane

        root.getChildren().add(menu);


        







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

}
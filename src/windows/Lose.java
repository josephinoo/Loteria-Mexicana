package windows;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.geometry.Pos;
import javafx.scene.*;
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
 * Clase que representa la situacion de perder el juego junto con su generador de contenido o ventana
 * @author Grupo10
 *
 */
public class Lose   {
    Pane  root ;

    menu ventanaMenu;

    public Pane getRoot(){
        return root;

    }
    public Lose (menu ventanaMenu){
        this.ventanaMenu=ventanaMenu;
        createContent();

    }
    public Lose (){

        createContent();

    }


    private void createContent() {
        root= new Pane();

        try (InputStream is = Files.newInputStream(Paths.get("src/resources/youlose.png"))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(860);
            img.setFitHeight(600);
            root.getChildren().add(img);

        }
        catch (IOException e) {
            System.out.println("Couldn't load image");
        }

        //Creating Buttons
        menu.MenuItem itemNewGame=new menu.MenuItem("Salir");

        menu.MenuItem itemExit=new menu.MenuItem("Menu principal");
        itemExit.setOnMouseClicked(event ->{
            menu ventana = new menu();
            Scene scene = new Scene(ventana.getRoot(),860,600);
            LoteriaMexicana.stage.setScene(scene);
        });

        menu.MenuBox menu = new menu.MenuBox(
                itemNewGame,itemExit);
        menu.setTranslateX(330);
        menu.setTranslateY(470);

        root.getChildren().addAll(menu);


        itemNewGame.setOnMouseClicked(event -> {
            System.exit(0);
        });
    }
    private static class Title extends StackPane {
        public Title(String name) {
            Rectangle bg = new Rectangle(250, 50);
            bg.setStroke(Color.BLACK);
            bg.setStrokeWidth(2);
            bg.setFill(null);

            Text text = new Text(name);
            text.setFill(Color.BLACK);
            text.setFont(Font.font("Hey Fun", FontWeight.SEMI_BOLD, 40));

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
            sep.setStroke(Color.WHITE);
            return sep;
        }
    }

    static class MenuItem extends StackPane {
        public MenuItem(String name) {
            LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] {
                    new Stop(0, Color.WHITE),
                    new Stop(0.1, Color.WHITE),
                    new Stop(0.9, Color.WHITE),
                    new Stop(1, Color.WHITE)
            });

            Rectangle bg = new Rectangle(200, 30);
            bg.setOpacity(0.1);

            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Hey Fun", FontWeight.SEMI_BOLD, 22));

            setAlignment(Pos.CENTER);
            getChildren().addAll(bg, text);

            setOnMouseEntered(event -> {
                bg.setFill(gradient);
                text.setFill(Color.WHITE);
            });


            setOnMouseExited(event -> {
                bg.setFill(Color.WHITE);
                text.setFill(Color.WHITE);
            });

            setOnMousePressed(event -> {
                bg.setFill(Color.WHITE);
            });

            setOnMouseReleased(event -> {
                bg.setFill(gradient);
            });
        }
    }

}

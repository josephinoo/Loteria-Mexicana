/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package windows;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import loteriamexicana.LoteriaMexicana;
/**
 * Clase Representativa de los atributos del menu del juego, junto con su generador de contenido o ventana
 * @author Grupo10
 *
 */
public class menu extends Parent {
    Pane root ;
    public Pane getRoot(){
        return root;
    }
    public menu(){
        createContent();
    }
    private void  createContent() {
        root= new Pane();
        root.setPrefSize(860, 600);

        try (InputStream is = Files.newInputStream(Paths.get("src/resources/2873401.png"))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(860);
            img.setFitHeight(600);
            root.getChildren().add(img);
        }
        catch (IOException e) {
            System.out.println("Couldn't load image");
        }
        Title title = new Title("MENU");
        title.setTranslateX(100);
        title.setTranslateY(200);

        MenuItem itemExit = new MenuItem("Exit");
        itemExit.setOnMouseClicked(event -> System.exit(0));
        MenuItem itemNewGame=new MenuItem("New Game");
        itemNewGame.setOnMouseClicked(event ->  {

            login ventana = new login(this);
            Scene scene = new Scene(ventana.getRoot(),860,600);
            LoteriaMexicana.stage.setScene(scene);;
        });
        MenuItem itemSettings=new MenuItem("Settings");
        itemSettings.setOnMouseClicked(event ->  {
            settings ventana = new settings(this);
            Scene scene = new Scene(ventana.getRoot(),860,600);
            LoteriaMexicana.stage.setScene(scene);;
        });
        MenuItem itemReport =new MenuItem("Report");
        itemReport.setOnMouseClicked(event -> {

            report ventana = new report(this);
            Scene scene = new Scene(ventana.getRoot(),860,600);
            LoteriaMexicana.stage.setScene(scene);;




        });

        MenuBox menu = new MenuBox(
                itemNewGame,
                itemSettings,itemReport
                ,

                itemExit);
        menu.setTranslateX(100);
        menu.setTranslateY(300);

        root.getChildren().addAll(title, menu);

    }




    private static class Title extends StackPane {
        public Title(String name) {
            Rectangle bg = new Rectangle(200, 60);
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

    static class MenuBox extends VBox {
        public MenuBox(MenuItem... items) {
            getChildren().add(createSeparator());

            for (MenuItem item : items) {
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
package windows;



import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import Models.Report;
import componetes.Jugador;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
 * Clase Representativa de los atributos del Reporte previo a convertirse en tabla, junto con su generador de contenido o ventana
 * @author Grupo10
 *
 */
public class report   {
    Pane  root ;
    VBox hBox ;
    menu ventanaMenu;
    public ArrayList<Jugador> jugadores;
    
    public Pane getRoot(){
        return root;

    }
    public report(menu ventanaMenu){
        this.ventanaMenu=ventanaMenu;
        createContent();

    }
  

    private void createContent() {
        root= new Pane();
        hBox= new VBox();
        try (InputStream is = Files.newInputStream(Paths.get("src/resources/background-login.png"))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(860);
            img.setFitHeight(600);
            root.getChildren().add(img);

        }
        catch (IOException e) {
            System.out.println("Couldn't load image");
        }

        Title title = new Title("REPORT");
        title.setTranslateX(300);
        title.setTranslateY(50);





        root.getChildren().addAll(title );
        
        String[] datos = {"Fecha","Duracion","Name","Oponentes","alinicacion"};
        TableView table = new TableView();
        for(String campo:datos){
            //establecer el nombre en la cabecera de la columna
            TableColumn<String, Jugador> column = new TableColumn<>(campo);
            //relacionar a la columna con un valor del objeto
            column.setCellValueFactory(new PropertyValueFactory<>(campo.toLowerCase()));
            column.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
            table.getColumns().add(column);
        }
        //Jugador jugador = new Jugador(login.nombreJugador,"Hola","Esto	eS","Prueba","detablas");
        //Report.jugadores.add(jugador);
        
        ObservableList<Jugador> personas= Report.jugadores;       
        //agregar los datos del arraylist al tableview
        //table.getItems().add(jugador);
        
        for(Jugador p:personas){
            table.getItems().add(p);       
          //automaticamente los campos del objeto se agregaran a la columna respectivo
        }
        
        
        
        /*
        for(Jugador p:personas) {
            table.setItems(personas);
        }
        */
                
        
        hBox.setPrefWidth(600);
        hBox.setPrefHeight(480);

        //Creating Buttons
        hBox.getChildren().add(table);
        root.getChildren().addAll(hBox);
        table.setTranslateX(130);
        table.setTranslateY(120);


        menu.MenuItem itemExit=new menu.MenuItem("<-Regresar");
        itemExit.setOnMouseClicked(event ->{
            menu ventana = new menu();
            Scene scene = new Scene(ventana.getRoot(),860,600);
            LoteriaMexicana.stage.setScene(scene);
        });

        menu.MenuBox menu = new menu.MenuBox(itemExit);
        menu.setTranslateX(50);
        menu.setTranslateY(75);

        root.getChildren().addAll(menu);
















    }
    private static class Title extends StackPane {
        public Title(String name) {
            Rectangle bg = new Rectangle(250, 60);
            bg.setStroke(Color.WHITE);
            bg.setStrokeWidth(2);
            bg.setFill(null);

            Text text = new Text(name);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Hey Fun", FontWeight.SEMI_BOLD, 30));

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
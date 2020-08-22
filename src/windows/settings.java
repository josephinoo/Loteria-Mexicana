package windows;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
 * Clase Representativa de los atributos de las configuraciones, junto con su generador de contenido o ventana
 * @author Grupo10
 *
 */
public class settings {
    Pane  root ;
    public static String oponentes="1";
    public static String visibilidad="Oculto";
    RadioButton button1,button2;

    menu ventanaMenu;
    public Pane getRoot(){
        return root;

    }
    public settings(menu ventanaMenu){
        this.ventanaMenu=ventanaMenu;
        createContent();

    }


    private void createContent() {
        root= new Pane();

        try (InputStream is = Files.newInputStream(Paths.get("src/resources/back-settings.png"))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(860);
            img.setFitHeight(600);
            root.getChildren().add(img);

        }
        catch (IOException e) {
            System.out.println("Couldn't load image");
        }

        Title title = new Title("Settings");
        title.setTranslateX(300);
        title.setTranslateY(130);
        CheckBox checkBox3 = new CheckBox("");
        checkBox3.setTranslateX(400);
        checkBox3.setTranslateY(330);





        root.getChildren().addAll(title );



        ToggleGroup group = new ToggleGroup();
        RadioButton button1 = new RadioButton();
        button1.setToggleGroup(group);
        button1.setSelected(true);
        button1.setTranslateX(400);
        button1.setTranslateY(300);

        RadioButton button2 = new RadioButton();
        button2.setToggleGroup(group);
        button2.setTranslateX(450);
        button2.setTranslateY(300);
        // Configuracion para un Jugador
        Text text1=new Text("1");
        text1.setTranslateX(430);
        text1.setTranslateY(315);
        text1.setFill(Color.WHITE);
        text1.setFont(Font.font("Hey Fun", FontWeight.SEMI_BOLD, 20));
        menu.MenuItem itemCantOponent=new menu.MenuItem("Number Enemies ");
        menu.MenuItem itemVisionCards=new menu.MenuItem("Cards Visibility");
        menu.MenuItem itemChanges=new menu.MenuItem("Save Changes");
        menu.MenuItem itemExit=new menu.MenuItem("Back");
        menu.MenuBox menu = new menu.MenuBox(itemCantOponent,itemVisionCards,itemChanges,itemExit);
        itemChanges.setOnMouseClicked(event ->{
            if(button1.isSelected()){

                oponentes="1";

            }
            if(button2.isSelected()){
                oponentes="2";

            }
            visibilidad=checkBox3.getText();

        });




        itemExit.setOnMouseClicked(event ->{

            menu ventana = new menu();
            Scene scene = new Scene(ventana.getRoot(),860,600);
            LoteriaMexicana.stage.setScene(scene);;


        });

        menu.setTranslateX(150);
        menu.setTranslateY(300);
        // Group

//        checkBox1.setTranslateX(400);
//        checkBox1.setTranslateY(300);
        Text text2=new Text("2");
        text2.setTranslateX(480);
        text2.setTranslateY(315);
        text2.setFill(Color.WHITE);
        text2.setFont(Font.font("Hey Fun", FontWeight.SEMI_BOLD, 20));
//        checkBox2.setTranslateX(450);
////        checkBox2.setTranslateY(300);
//        checkBox1.setSelected(true);
        Text text3=new Text("Ocult");
        text3.setTranslateX(430);
        text3.setTranslateY(350);
        text3.setFill(Color.WHITE);
        text3.setFont(Font.font("Hey Fun", FontWeight.SEMI_BOLD, 20));
        checkBox3.setTranslateX(400);
        checkBox3.setTranslateY(330);
//
//
        File f = new File("src/resources/style.css");
//       checkBox1.getStylesheets().add("file:///"+f.getAbsolutePath().replace("\\","/"));
//       checkBox2.getStylesheets().add("file:///"+f.getAbsolutePath().replace("\\","/"));
        button1.getStylesheets().add("file:///"+f.getAbsolutePath().replace("\\","/"));
        button2.getStylesheets().add("file:///"+f.getAbsolutePath().replace("\\","/"));
        checkBox3.getStylesheets().add("file:///"+f.getAbsolutePath().replace("\\","/"));









        //Creating a Grid Pane

        //Setting size for the pane

        //root.getChildren().addAll(menu,checkBox1,checkBox2,checkBox3,text1,text2,text3);
        root.getChildren().addAll(menu,button1,button2,checkBox3,text1,text2,text3);




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
            text.setFont(Font.font("Hey Fun", FontWeight.SEMI_BOLD, 30));

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

    public static String getOponentes() {
        return oponentes;
    }

    public static String getVisibilidad() {
        return visibilidad;
    }
}
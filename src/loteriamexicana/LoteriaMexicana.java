package loteriamexicana;

import Models.Report;
import javafx.application.Application;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import music.Sound;
import windows.menu;

/**
 *Clase Principal que ejecuta el juego
 * @author Grupo10
 */


public class LoteriaMexicana extends Application {
    public static final Stage stage = new Stage();

    public void start(Stage primaryStage) {

        menu ventanaLista = new menu();
        Report.leerArchivo();
        Scene scene = new Scene(ventanaLista.getRoot());
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        stage.setResizable(false);
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sound.playmusic("src/resources/coco.wav");
        launch(args);
        


    }


}




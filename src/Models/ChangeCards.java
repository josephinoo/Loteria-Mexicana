package Models;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase Encargada de cambiar las cartas, ademas de su respectivo sonido
 * @author USUARIO
 *
 */
public class ChangeCards {


    private void createContenido() {
        Pane root = new Pane();
        MazoOfCards mazo = new MazoOfCards();
        ArrayList<Card> cartas = mazo.mazodecartas();
        Collections.shuffle(cartas);
        for (Card card : cartas) {
            Image image1 = new Image("resources/x.png");
            ImageView imageView = new ImageView();
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(3), new KeyValue(imageView.imageProperty(), image1))
            );
            timeline.play();
            root.getChildren().add(imageView);

        }

 
    }


}


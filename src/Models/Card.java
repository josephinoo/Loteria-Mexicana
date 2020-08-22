package Models;



import java.awt.*;
import java.util.Objects;

import javafx.scene.image.ImageView;
/**
 * Clase Representativa de las cartas del juego con sus atributos
 * @author Grupo10
 *
 */
public class Card {
    private String name;
    private String number;
    private String image;


    public Card(String name, String number, String image) {
        this.name = name;
        this.number = number;
        this.image = image;


    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", image=" + image +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return name.equals(card.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

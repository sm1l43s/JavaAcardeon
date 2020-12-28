package parts;

import java.awt.*;

public class Card {

    private Image image;
    private String name;
    private String type;

    private Coord coord;

    public Card() {}

    public Card(Image image, String name, String type) {
        this.image = image;
        this.name = name;
        this.type = type;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    @Override
    public String toString() {
        return "logic.Card{" +
                "image=" + image +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", coord=" + coord +
                '}';
    }
}

package parts;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreatorCards {

    private TypeCard[] typeCards;
    private NameCard[] nameCards;

    public CreatorCards(TypeCard[] typeCards, NameCard[] nameCards) {
        this.typeCards = typeCards;
        this.nameCards = nameCards;
    }

    public List<Card> getCardsList() {
        List<Card> list = new ArrayList<Card>();

        for (int i = 0; i < nameCards.length; i++) {
            for (int j = 0; j < typeCards.length; j++) {
                Card card = new Card(getImage("/cards/" + typeCards[j] + "s/" + nameCards[i] + "_" + typeCards[j] + ".png"),
                        nameCards[i].name().toLowerCase(), typeCards[j].name().toLowerCase());
                list.add(card);
            }
        }
        return list;
    }

    public List<Card> shuffleList(List<Card> list) {
        Collections.shuffle(list);
        return list;
    }

    private Image getImage(String url) {
        Image image = null;
        try {
            image = ImageIO.read(getClass().getResource(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}

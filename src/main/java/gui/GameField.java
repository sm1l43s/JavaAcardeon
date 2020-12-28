package gui;

import parts.Card;
import parts.Coord;
import parts.SizeKeeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GameField extends JPanel {

    private int posX = 10;
    private int posY = 60;

    private int dx = SizeKeeper.IMAGE_WIDTH + 18;
    private int dy = SizeKeeper.IMAGE_HEIGHT + 25;

    private int count = 0;

    private List<Card> list;
    private List<Card> cards = new ArrayList<>(2);

    public GameField() {
        setBackground(new Color(54, 123, 12));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();

                checkStatusGame();

                for (Card card: list) {
                    if (card.getCoord().isCardArea(card.getCoord(), x, y)) {
                        if (cards.size() < 1) {
                            cards.add(card);
                        } else if (cards.size() == 1 && !cards.get(0).equals(card)) {
                            cards.add(card);
                        } else {
                            cards.remove(0);
                        }

                        if (cards.size() == 0) {
                            drawCards(getGraphics(), list, null);
                        } else {
                            drawCards(getGraphics(), list, cards.get(0));
                        }
                    }
                }
                checkCard();
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (cards.size() == 0) {
            drawCards(g, list, null);
        } else {
            drawCards(g, list, cards.get(0));
        }
    }

    private void drawCards(Graphics g, List<Card> cards, Card checkedCard) {
        int k = 0;
        g.setFont(new Font("TimesRoman", Font.PLAIN, 22));
        g.setColor(Color.white);
        g.drawString("Кол-во ходов: " + count, 10, 35);
        for (int i = 0; i < SizeKeeper.ROW; i++) {
            for (int j = 0; j < SizeKeeper.COLS; j++) {
                if (k >= cards.size()) continue;

                if (checkedCard != null && list.get(k).equals(checkedCard)) {
                    g.setColor(new Color(255, 188, 26));
                    g.fillRect(posX - 3, posY - 3, SizeKeeper.IMAGE_WIDTH + 6, SizeKeeper.IMAGE_HEIGHT + 6);
                    g.drawRect(posX - 3, posY - 3, SizeKeeper.IMAGE_WIDTH + 6, SizeKeeper.IMAGE_HEIGHT + 6);
                }
                g.drawImage(list.get(k).getImage(), posX, posY, this);
                list.get(k).setCoord(new Coord(posX, posY));
                posX += dx;
                k++;
            }
            posX = 10;
            posY += dy;
        }
        posX = 10;
        posY = 60;
    }

    public void setList(List<Card> list) {
        this.list = list;
    }

    private void changerList(int posCardOne, int posCardTwo) {
        count++;
        list.remove(posCardOne);
        list.remove(posCardTwo);
        list.add(posCardTwo, cards.get(0));
    }

    private int dividePos(int posStart, int posEnd) {
        return posStart - posEnd;
    }

    private void checkStatusGame() {
        if (list.size() < 2) {
            JOptionPane.showMessageDialog(null, "Вы выйграли!");
        }
    }

    private void checkCard() {
        if (cards.size() == 2 ) {
            int posCardOne = list.indexOf(cards.get(0));
            int posCardTwo = list.indexOf(cards.get(1));

            if ((cards.get(0).getName().equals(cards.get(1).getName()) ||
                    cards.get(0).getType().equals(cards.get(1).getType())) &&
                    (dividePos(posCardOne, posCardTwo) == 1 || dividePos(posCardOne, posCardTwo) == 2)) {

                changerList(posCardOne, posCardTwo);
                drawCards(getGraphics(), list, null);

                cards.remove(1);
                cards.remove(0);
            } else {
                cards.remove(1);
            }
        }
    }

}

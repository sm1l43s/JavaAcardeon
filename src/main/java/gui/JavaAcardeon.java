package gui;

import parts.CreatorCards;
import parts.NameCard;
import parts.SizeKeeper;
import parts.TypeCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class JavaAcardeon extends JFrame {

    public JavaAcardeon() {
        initFrame();
    }

    private void initFrame() {
        setTitle("Игра 'Баян'");
        setSize(new Dimension(SizeKeeper.IMAGE_WIDTH * SizeKeeper.COLS + SizeKeeper.IMAGE_WIDTH + 145,
                              SizeKeeper.IMAGE_HEIGHT * SizeKeeper.ROW + SizeKeeper.IMAGE_HEIGHT + 85));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        add(initGameField());
        setVisible(true);
    }

    private GameField initGameField() {
        GameField gameField = new GameField();
        add(InitMenuBar(), BorderLayout.NORTH);
        CreatorCards creatorCards = new CreatorCards(TypeCard.values(), NameCard.values());
        gameField.setList(creatorCards.shuffleList(creatorCards.getCardsList()));
        return gameField;
    }

    private JMenuBar InitMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Файл");
        fileMenu.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);

        JMenuItem newGame = new JMenuItem("Новая игра", KeyEvent.VK_N);
        newGame.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getContentPane().removeAll();
                getContentPane().add(initGameField());
                revalidate();
                repaint();
            }
        });
        fileMenu.add(newGame);

        JMenuItem rules = new JMenuItem("Правила игры", KeyEvent.VK_O);
        rules.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
        rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Правила пасьянса «Баян»\n" +
                        "\n" +
                        "Пасьянс раскладывается колодой из 52 карт.\n" +
                        "Карты раскладываются в 4 горизонтальных ряда по 13 карт в каждом. Каждая карта – это стопка из одной карты. \n" +
                        "Далее стопки начинают объединяться по следующему правилу: на любую стопку можно положить стопку правее данной \n" +
                        "или стопку, располагающуюся справа от данной через одну стопку, при этом верхние карты перекладываемых стопок \n" +
                        "должны быть одинаковой масти или одинакового достоинства. Для крайней правой стопки в ряду правой стопкой \n" +
                        "считается крайняя левая стопка в следующем ряду. После перекладывания стопки на освободившееся место сдвигаются все стопки правее. \n" +
                        "\nЦель пасьянса - сложить все карты в одну стопку.");
            }
        });
        fileMenu.add(rules);

        JMenuItem about = new JMenuItem("О программе", KeyEvent.VK_O);
        about.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Автор: Браусов Д.Д. \nПредмет: ООТПиСП");
            }
        });
        fileMenu.add(about);

        JMenuItem close = new JMenuItem("Закрыть", KeyEvent.VK_O);
        close.setFont(new Font("Arial", Font.ROMAN_BASELINE, 14));
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        fileMenu.add(close);

        return menuBar;
    }
}

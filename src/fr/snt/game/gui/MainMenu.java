package fr.snt.game.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class MainMenu extends BaseGui {
    private JFrame rootPanel;
    private JLabel bg;
    private JLabel play, settings, exit;


    public MainMenu() {
        rootPanel = new JFrame("Game");
        rootPanel.setDefaultCloseOperation(EXIT_ON_CLOSE);
        rootPanel.setLayout(null);
        rootPanel.setSize(screenSize.width * 4 / 5, screenSize.height * 3 / 4);
        rootPanel.setLocationRelativeTo(null);
        rootPanel.setResizable(false);
        rootPanel.setForeground(Color.BLACK);


        ImageIcon icon = new ImageIcon(imPath + "/src/assets/images/anno.jpg");
        icon = new ImageIcon(icon.getImage().getScaledInstance(screenSize.width * 4 / 5, screenSize.height * 3 / 4, Image.SCALE_SMOOTH));
        bg = new JLabel(icon);
        bg.setOpaque(true);
        bg.setSize(rootPanel.getSize());
        bg.setBackground(Color.green);
        //rootPanel.add(bg);

        ImageIcon iconB = new ImageIcon(imPath + "/src/assets/images/9k.png");
        exit = new JLabel(iconB);
        //exit.setBackground(null);
        exit.setFont(new Font(exit.getFont().getFontName(), Font.PLAIN, 50));
        exit.setForeground(Color.RED);
        exit.setBackground(Color.GREEN);
        exit.setBounds(50, 50, 100, 50);
        exit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(1);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        exit.setVisible(true);


        rootPanel.add(exit);
        rootPanel.setVisible(true);

    }

}

package fr.snt.game.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Struct;

import static  javax.swing.WindowConstants.*;

public class Gui {
    private JFrame rootPanel;
    private JPanel bg;
    private JFrame combatPanel;
    private JButton button1;
    private JLabel label1;
    private Popup popup;
    private boolean popupShown;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private final int offsetX = (screenSize.width/15);
    private final int offsetY = screenSize.height/12;

    public Gui(){
        rootPanel = new JFrame("Game");
        rootPanel.setDefaultCloseOperation(EXIT_ON_CLOSE);
        rootPanel.setLayout(null);
        rootPanel.setSize(screenSize.width*4/5, screenSize.height*3/4);
        rootPanel.setLocationRelativeTo(null);
        rootPanel.setResizable(false);

        bg = new JPanel();
        bg.setOpaque(true);
        bg.setSize(rootPanel.getSize());
        bg.setBackground(Color.green);


        Path currentRelativePath = Paths.get("");
        String imPath = currentRelativePath.toAbsolutePath().toString();
        ImageIcon icon = new ImageIcon(imPath + "/src/assets/images/9k.png");
        label1 = new JLabel("Hello");
        label1.setBounds(20,20,icon.getIconWidth(),icon.getIconHeight());



        button1 = new JButton(icon);
        //button1.setToolTipText("Knife \nattack: 3 \ncost: 5");

        // TODO: create a function that automatically creates those for any given button and later that automatically displays weapons/armors info

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                JLabel text = new JLabel("<html>You've clicked at:<br/> " + e.getPoint() + "<br/> hello <br/> tester");
                if (!popupShown) {
                    text.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
                    text.setOpaque(true);
                    text.setBackground(new Color(192, 192, 192, 140)); // light gray with alpha   FIXME: Alpha not working for some reason
                    popup = PopupFactory.getSharedInstance().getPopup(button1, text, (int) (button1.getLocationOnScreen().x + button1.getWidth()*1.25), button1.getLocationOnScreen().y - 20);
                    popup.show();
                    popupShown = true;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                popup.hide();
                popupShown = false;

            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked");
                JLabel text = new JLabel("<html>You've clicked at:<br/> " +"dzdzdzzq"+ "<br/> hello <br/> tester");
                System.out.println(text.getBounds().height);
            }
        });
        button1.setBounds(200, 100, 70, 40);



        JButton button2 = new JButton(icon);
        //button1.setToolTipText("Knife \nattack: 3 \ncost: 5");

        // TODO: create a function that automatically creates those for any given button and later that automatically displays weapons/armors info

        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("entered");
                JLabel text = new JLabel("<html>You've clicked at:<br/> " + e.getPoint() + "<br/> hello <br/> tester");
                if (!popupShown) {
                    text.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
                    text.setOpaque(true);
                    text.setBackground(new Color(192, 192, 192, 140)); // light gray with alpha   FIXME: Alpha not working for some reason
                    popup = PopupFactory.getSharedInstance().getPopup(button2, text, (int) (button2.getLocationOnScreen().x + button2.getWidth()*1.25), button2.getLocationOnScreen().y - 20);
                    popup.show();
                    popupShown = true;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                popup.hide();
                popupShown = false;

            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clicked");
                JLabel text = new JLabel("<html>You've clicked at:<br/> " +"dzdzdzzq"+ "<br/> hello <br/> tester");
                System.out.println(text.getBounds().height);
            }
        });
        button2.setBounds(200 + offsetX, 100, 70, 40);





        //rootPanel.add(label1);
        rootPanel.add(button1);
        rootPanel.add(button2);
        rootPanel.add(bg);
        //rootPanel.setBackground(Color.red);
        rootPanel.setVisible(true);


    }

    //public Gui getGui(){

    //}

}

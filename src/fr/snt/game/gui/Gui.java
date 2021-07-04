package fr.snt.game.gui;

import fr.snt.game.Player;
import fr.snt.game.equipables.Armors;
import fr.snt.game.equipables.Equipables;
import fr.snt.game.equipables.Weapons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static assets.utils.SavesHandler.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Gui {
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int offsetX = (screenSize.width / 17);
    // --Commented out by Inspection (04/07/2021 22:01):private final int offsetY = screenSize.height / 12;
    private final JFrame rootPanel;
    private JPanel bg;
// --Commented out by Inspection START (04/07/2021 22:01):
//    // --Commented out by Inspection (04/07/2021 22:01):private JFrame combatPanel;
//    // --Commented out by Inspection (04/07/2021 22:01):private JButton button1;
// --Commented out by Inspection STOP (04/07/2021 22:01)
    private JLabel label1;
    private Popup popup;
    private boolean popupShown;
    private final ArrayList<Weapons> WL;
    private final ArrayList<Armors> AL;
    private final Player player;

    public Gui() throws Exception {
        rootPanel = new JFrame("Game");
        rootPanel.setDefaultCloseOperation(EXIT_ON_CLOSE);
        rootPanel.setLayout(null);
        rootPanel.setSize(screenSize.width * 4 / 5, screenSize.height * 3 / 4);
        rootPanel.setLocationRelativeTo(null);
        rootPanel.setResizable(false);
        rootPanel.setVisible(true);

        assert testLoad();
        WL = loadW();
        AL = loadA();

        player = new Player("Jari", 24, 5, 1);
        player.addToInventory(WL.get(2));
        player.addToInventory(AL.get(2));
        player.addGold(999);

        this.switchShop();

    }

    public void switchShop() {
        rootPanel.getContentPane().removeAll();
        rootPanel.repaint();


        int buttonWidth = screenSize.width * 4 / (5 * 20);
        @SuppressWarnings("SuspiciousNameCombination") int buttonHeight = buttonWidth;

        bg = new JPanel();
        bg.setOpaque(true);
        bg.setSize(rootPanel.getSize());
        bg.setBackground(Color.green);

        JLabel wLabel = new JLabel();
        wLabel.setText("Weapons");
        wLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        wLabel.setBounds(20, 20, 200, 28);
        rootPanel.add(wLabel);

        Path currentRelativePath = Paths.get("");
        String imPath = currentRelativePath.toAbsolutePath().toString();
        //ImageIcon icon = new ImageIcon(imPath + "/src/assets/images/weapons/botrk.png");

        int offset_times = 0;
        for (Weapons w : WL){
            ImageIcon icon = new ImageIcon(new ImageIcon(imPath + w.getImgsrc())
                    .getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_DEFAULT));
//            label1 = new JLabel("Hello");
//            label1.setBounds(20, 20, icon.getIconWidth(), icon.getIconHeight());
            JButton placeholder_button = new JButton(icon);
            boolean hasItem = player.getInventory().contains(w);
            placeholder_button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    // System.out.println("entered");
                    JLabel text = new JLabel(String.format("<html><div WIDTH=%d>%s</div></html>", screenSize.width / 5,
                            "<html>Weapon name: " + w.getName() + (hasItem ? " [OWNED]" : "")
                                    + "<br/>Cost: " + w.getCost() + "<br/>Attack: " + w.getAttack() +
                                    (w.hasSpEffect() ? ("<br/>SPEffect: " + w.getSpEffectType()) : "") +
                                    "<br/> Description: " + w.getDescription()));
                    if (!popupShown) {
                        text.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
                        text.setOpaque(true);
                        text.setBackground(new Color(192, 192, 192, 140)); // light gray with alpha   FIXME: Alpha not working for some reason
                        popup = PopupFactory.getSharedInstance().getPopup(placeholder_button, text,
                                (int) (placeholder_button.getLocationOnScreen().x + placeholder_button.getWidth() * 1.25),
                                placeholder_button.getLocationOnScreen().y - 20);
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
            if (!hasItem) {
                placeholder_button.addActionListener(e -> {
                    System.out.println("Bought " + w.getName() + " for " + w.getCost() + "GP");
                    if (this.buy(w)) {
                        popup.hide();
                        popupShown = false;
                        this.switchShop();
                    }
                    System.out.println("Clicked");
                });
            }
            placeholder_button.setBounds(20 + offsetX*offset_times, 60, buttonWidth, buttonHeight);
            rootPanel.add(placeholder_button);
            offset_times++;
        }
        ImageIcon icon = new ImageIcon(new ImageIcon(imPath + "/src/assets/images/weapons/botrk.png")
                .getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_DEFAULT));
        offset_times = 0;
        for (Armors a : AL){
            JButton placeholder_button = new JButton(icon);
            boolean hasItem = player.getInventory().contains(a);
            placeholder_button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    // System.out.println("entered");
                    JLabel text = new JLabel(String.format("<html><div WIDTH=%d>%s</div></html>", screenSize.width / 5,
                            "<html>Armor name: " + a.getName() + (hasItem ? " [OWNED]" : "")
                                    + "<br/>Cost: " + a.getCost() + "<br/>Armor: " + a.getArmorValue() +
                                    (a.hasSpEffect() ? ("<br/>SPEffect: " + a.getSpEffectType()) : "") +
                                    "<br/> Description: " + a.getDescription()));
                    if (!popupShown) {
                        text.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
                        text.setOpaque(true);
                        text.setBackground(new Color(192, 192, 192, 140)); // light gray with alpha   FIXME: Alpha not working for some reason
                        popup = PopupFactory.getSharedInstance().getPopup(placeholder_button, text,
                                (int) (placeholder_button.getLocationOnScreen().x + placeholder_button.getWidth() * 1.25),
                                placeholder_button.getLocationOnScreen().y - 20);
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
            if (!hasItem) {
                placeholder_button.addActionListener(e -> {
                    System.out.println("Bought " + a.getName() + " for " + a.getCost() + "GP");
                    if (this.buy(a)) {
                        popup.hide();
                        popupShown = false;
                        this.switchShop();
                    }
                    System.out.println("Clicked");
                });
            }
            placeholder_button.setBounds(20 + offsetX*offset_times, 300, buttonWidth, buttonHeight);
            rootPanel.add(placeholder_button);
            offset_times++;
        }

        JButton attackButton = new JButton();
        attackButton.setBounds(20, 500, 100, 20);
        attackButton.setText("Attack");
        attackButton.addActionListener(e -> {
            try {
                this.switchCombat();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        rootPanel.add(attackButton);
        rootPanel.add(bg);
        rootPanel.repaint();
        //rootPanel.setBackground(Color.red);
    }

    public void switchInventory() {
        rootPanel.getContentPane().removeAll();
        rootPanel.repaint();

        int buttonWidth = screenSize.width * 4 / (5 * 20);
        @SuppressWarnings("SuspiciousNameCombination") int buttonHeight = buttonWidth;

        bg = new JPanel();
        bg.setOpaque(true);
        bg.setSize(rootPanel.getSize());
        bg.setBackground(Color.red);

        JLabel wLabel = new JLabel();
        wLabel.setText("Weapons");
        wLabel.setFont(new Font("Verdana", Font.PLAIN, 25));
        wLabel.setBounds(20, 20, 200, 28);
        rootPanel.add(wLabel);

        Path currentRelativePath = Paths.get("");
        String imPath = currentRelativePath.toAbsolutePath().toString();
        //ImageIcon icon = new ImageIcon(imPath + "/src/assets/images/weapons/botrk.png");

        int offset_times = 0;
        for (Weapons w : player.getWeapons()){
            ImageIcon icon = new ImageIcon(new ImageIcon(imPath + w.getImgsrc())
                    .getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_DEFAULT));
//            label1 = new JLabel("Hello");
//            label1.setBounds(20, 20, icon.getIconWidth(), icon.getIconHeight());
            JButton placeholder_button = new JButton(icon);
            boolean isEquipped = player.getWeapon() == w;
            placeholder_button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    // System.out.println("entered");
                    JLabel text = new JLabel(String.format("<html><div WIDTH=%d>%s</div></html>", screenSize.width / 5,
                            "<html>Weapon name: " + w.getName() + (isEquipped ? " [EQUIPPED]" : "")
                                    + "<br/>Attack: " + w.getAttack() +
                                    (w.hasSpEffect() ? ("<br/>SPEffect: " + w.getSpEffectType()) : "") +
                                    "<br/> Description: " + w.getDescription()));
                    if (!popupShown) {
                        text.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
                        text.setOpaque(true);
                        text.setBackground(new Color(192, 192, 192, 140)); // light gray with alpha   FIXME: Alpha not working for some reason
                        popup = PopupFactory.getSharedInstance().getPopup(placeholder_button, text,
                                (int) (placeholder_button.getLocationOnScreen().x + placeholder_button.getWidth() * 1.25),
                                placeholder_button.getLocationOnScreen().y - 20);
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
            placeholder_button.addActionListener(e -> {
                player.setWeapon(w);
                popup.hide();
                popupShown = false;
                this.switchInventory();
                System.out.println("Clicked on " + w.getName());
            });

            placeholder_button.setBounds(20 + offsetX*offset_times, 60, buttonWidth, buttonHeight);
            rootPanel.add(placeholder_button);
            offset_times++;
        }
        ImageIcon icon = new ImageIcon(new ImageIcon(imPath + "/src/assets/images/weapons/botrk.png")
                .getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_DEFAULT));
        offset_times = 0;
        for (Armors a : player.getArmors()){
            JButton placeholder_button = new JButton(icon);
            boolean isEquipped = player.getArmor() == a;
            placeholder_button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    // System.out.println("entered");
                    JLabel text = new JLabel(String.format("<html><div WIDTH=%d>%s</div></html>", screenSize.width / 5,
                            "<html>Armor name: " + a.getName() + (isEquipped ? " [EQUIPPED]" : "")
                                    + "<br/>Armor: " + a.getArmorValue() +
                                    (a.hasSpEffect() ? ("<br/>SPEffect: " + a.getSpEffectType()) : "") +
                                    "<br/> Description: " + a.getDescription()));
                    if (!popupShown) {
                        text.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
                        text.setOpaque(true);
                        text.setBackground(new Color(192, 192, 192, 140)); // light gray with alpha   FIXME: Alpha not working for some reason
                        popup = PopupFactory.getSharedInstance().getPopup(placeholder_button, text,
                                (int) (placeholder_button.getLocationOnScreen().x + placeholder_button.getWidth() * 1.25),
                                placeholder_button.getLocationOnScreen().y - 20);
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
            placeholder_button.addActionListener(e -> {
                player.setArmor(a);
                popup.hide();
                popupShown = false;
                this.switchInventory();
                System.out.println("Clicked on " + a.getName());
            });
            placeholder_button.setBounds(20 + offsetX*offset_times, 300, buttonWidth, buttonHeight);
            rootPanel.add(placeholder_button);
            offset_times++;
        }

        JButton attackButton = new JButton();
        attackButton.setBounds(20, 500, 100, 20);
        attackButton.setText("Attack");
        attackButton.addActionListener(e -> this.switchCombat());
        rootPanel.add(attackButton);
        rootPanel.add(bg);
        rootPanel.repaint();
        //rootPanel.setBackground(Color.red);
    }

    public void switchCombat() {
        rootPanel.getContentPane().removeAll();
        rootPanel.repaint();
        JButton inventory = new JButton();
        inventory.setBounds(50, 200, 100, 50);
        inventory.setText("Inventory");
        inventory.addActionListener(e -> this.switchInventory());
        rootPanel.add(inventory);
        JButton shop = new JButton();
        shop.setBounds(150, 200, 100, 50);
        shop.setText("Shop");
        shop.addActionListener(e -> this.switchShop());
        rootPanel.add(shop);
    }

    private boolean buy(Equipables item) {
        if (player.getGold() < item.getCost()) {
            return false;
        }
        System.out.println(player.getGold());
        player.addToInventory(item);
        player.addGold(-item.getCost());
        System.out.println(player.getGold());
        return true;
    }

}

//import fr.snt.game.equipables.Weapons;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.ArrayList;
//
//import static assets.utils.SavesHandler.loadW;
//
//
//class FirstSwingExample {
//    private static class ButtonListener implements ActionListener{
//        Weapons weapon;
//        ButtonListener(Weapons weapon){
//            this.weapon = weapon;
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            System.out.println("Price of " + weapon.getName() + " is " + weapon.getCost() + " gold" );
//        }
//
//    }
//
//    public static void main(String[] args) throws Exception {
//        ArrayList<Weapons> WL = loadW();
//        JFrame f=new JFrame();//creating instance of JFrame
//
//        for (int i = 0; i < WL.size(); i++) {
//            JButton b = new JButton(WL.get(i).getName());
//            b.setBounds(130, 100 + i*50, 100, 40);
//            b.addActionListener(new ButtonListener(WL.get(i)));
//
//            f.add(b);
//        }
//
//
//        //JButton b=new JButton("click");//creating instance of JButton
//        //b.setBounds(130,100,100, 40);//x axis, y axis, width, height
//
//        //.add(b);//adding button in JFrame
//
//        f.setSize(400,500);//400 width and 500 height
//        f.setLayout(null);//using no layout managers
//        f.setVisible(true);//making the frame visible
//    }
//}
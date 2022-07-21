package my.example;


import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is for the purpose to run the program.
 * @author Wei Jun Shong
 * @author Beh He-Hong
 */
public class PosLajuMalaysia {
    public static void main(String[] args) {
        /**
         * Runs the program
         */
        Choice choice = new Choice();
    }
}

/**
 * This class is for user to key in their names and proceed.
 * @author Wei Jun Shong
 * @author Beh He-Hong
 */
class Choice extends MenuItem{
    JButton b1, b2;
    static String summery;
    public static String One;
    private int flag=1;

    /**
     * This constructor displays the buttons for user to save and proceed.
     */
    public Choice(){

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        JPanel pTop = new JPanel();
        cp.add(pTop, BorderLayout.NORTH);

        JPanel pCenter = new JPanel();
        cp.add(pCenter, BorderLayout.CENTER);
        pCenter.setBackground(Color.WHITE);

        JLabel jl2 = new JLabel();
        pCenter.add(jl2);

        cp.setLayout(new BorderLayout());

        JPanel panelTop = new JPanel();
        cp.add(panelTop, BorderLayout.NORTH);

        JLabel label = new JLabel("Name");
        JTextField tf = new JTextField(20);

        panelTop.add(label);
        panelTop.add(tf);

        JPanel centreMid = new JPanel();
        centreMid.setLayout(new GridLayout(2,2));

        cp.add(centreMid,BorderLayout.CENTER);
        JPanel panelBottom = new JPanel();
        cp.add(panelBottom, BorderLayout.SOUTH);
        JButton button1 = new JButton("Save");
        JButton button2 = new JButton("Clear");
        JButton button3 = new JButton("Reset");
        JButton button4 = new JButton("Exit");
        centreMid.add(button1);
        centreMid.add(button2);
        centreMid.add(button3);
        centreMid.add(button4);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (dialogButton == JOptionPane.YES_OPTION) {
                    if (e.getSource() == button1) {

                        One = tf.getText().trim();

                        if (!"".equals(tf)) {
                            summery = ("\n" + "Name" + "\n" + One);
                            String Data = Choice.summery;
                            try {
                                BufferedWriter reader = new BufferedWriter(new FileWriter(new File("Name.txt"), true));
                                reader.write(Data);
                                reader.newLine();
                                reader.close();

                            } catch (IOException E) {
                                System.out.println("Error is " + E);
                            }
                            flag = 2;
                        }
                    }

                }if(tf.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter your name");
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag==2) {
                    JOptionPane.showMessageDialog(null, "Please proceed to Next");
                }

                else {
                    try {
                        Document doc = tf.getDocument();
                        if (doc.getLength() > 0) {
                            doc.remove(doc.getLength() - 1, 1);
                        }
                    } catch (BadLocationException badLocationException) {
                        badLocationException.printStackTrace();
                    }
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tf.getText().equals("")){

                }
                else if (flag==2) {
                    JOptionPane.showMessageDialog(null, "Please proceed to Next");
                }
                else{
                    tf.setText("");
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel pBottom = new JPanel();

        cp.add(pBottom, BorderLayout.SOUTH);
        b1 = new JButton("Next");
        b2 = new JButton("Exit");
        pBottom.add(b1);
        pBottom.add(b2);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b1) {
                    if(flag!=2){
                        JOptionPane.showMessageDialog(null, "Please save your name");
                    }

                    else if(tf.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Please enter your name");
                    }

                    else if (!"".equals(tf)|| flag==2) {
                        new MainFrame().setVisible(true);
                        setVisible(false);

                        try {
                            BufferedWriter reader1 = new BufferedWriter(new FileWriter(new File("NextDayDelivery.txt"), true));
                            NextDayDelivery.charge1 = 0;
                            NextDayDelivery.total = 0;
                            reader1.write("-----------");
                            reader1.newLine();
                            reader1.close();

                            BufferedWriter reader2 = new BufferedWriter(new FileWriter(new File("SameDayDelivery.txt"), true));
                            SameDayDelivery.payment = 0;
                            SameDayDelivery.total = 0;
                            reader2.write("-----------");
                            reader2.newLine();
                            reader2.close();

                            BufferedWriter reader3 = new BufferedWriter(new FileWriter(new File("Prepaid Box and Envelope.txt"), true));
                            PrepaidBox.charge2 = 0;
                            PrepaidBox.total = 0;
                            Envelope.charge2 = 0;
                            Envelope.total = 0;
                            reader3.write("-----------");
                            reader3.newLine();
                            reader3.close();

                            BufferedWriter reader4 = new BufferedWriter(new FileWriter(new File("Pos Ekspres.txt"), true));
                            PosEkspres.totalprice = 0;
                            PosEkspres.total = 0;
                            reader4.write("-----------");
                            reader4.newLine();
                            reader4.close();

                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setTitle("Pos Laju Malaysia");
        setVisible(true);
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }

}

/**
 * This class is for displaying the menubar.
 * @author Wei Jun Shong
 * @author Beh He-Hong
 */
class MenuItem extends JFrame{

    /**
     * This constructor shows all the item in the menubar.
     */
    public MenuItem() {

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        JMenu file = new JMenu("File");
        menubar.add(file);
        JMenuItem open = new JMenuItem("Open");
        JMenuItem settings = new JMenuItem("Settings");
        JMenuItem print = new JMenuItem("Print");
        JMenuItem exit = new JMenuItem("Exit");
        file.add(open);
        file.add(settings);
        file.add(print);
        file.add(exit);

        JMenu edit = new JMenu("Edit");
        menubar.add(edit);
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        JMenuItem delete = new JMenuItem("Delete");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(delete);

        JMenu help = new JMenu("Help");
        menubar.add(help);
        JMenuItem about = new JMenuItem("About");
        help.add(about);

        setTitle("Menu");
        setVisible(true);
        setSize(400, 200);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}


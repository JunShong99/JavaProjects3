package my.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This class is for displaying the package of Prepaid Box and Envelope.
 * @author Wei Jun Shong
 * @author Beh He-Hong
 */
public class PrepaidBoxEnvelope extends JFrame implements ActionListener{
    private JButton button,button2;
    private JLabel label;
    private JRadioButton envelope, prepaid;

    /**
     * This constructor displays Envelope and Prepaid Box.
     */
    public PrepaidBoxEnvelope() {
        label = new JLabel("Please select your type of package");
        label.setBounds(50, 50, 300, 20);
        add(label);

        envelope = new JRadioButton("Envelope");
        envelope.setBounds(100, 100, 150, 20);
        envelope.addActionListener(this);
        prepaid = new JRadioButton("Prepaid Box");
        prepaid.setBounds(100, 150, 150, 20);
        prepaid.addActionListener(this);
        add(envelope);
        add(prepaid);

        button = new JButton("Confirm");
        button.setBounds(100, 200, 80, 30);
        button.addActionListener(new ActionListener() {
            @Override
            /**
             * This method is to allow user to choose only one at a time whether Envelope or Prepaid Box is selected.
             */
            public void actionPerformed(ActionEvent e) {
                if(envelope.isSelected()){
                    new Envelope();
                    dispose();
                }
                else if(prepaid.isSelected()){
                    new PrepaidBox();
                    dispose();
                }

            }
        });

        button2 = new JButton("Back");
        button2.setBounds(200, 200, 80, 30);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainFrame();
                dispose();
            }
        });

        add(button);
        add(button2);

        setSize(400, 400);
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Prepaid Box and Envelope");
        setLocationRelativeTo(null);
    }

    /**
     *
     * @param e This method is to allow user to choose only one at a time whether Envelope or Prepaid Box is selected.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(envelope.isSelected()){
            prepaid.setEnabled(false);
        }
        else{
            prepaid.setEnabled(true);
        }

        if(prepaid.isSelected()){
            envelope.setEnabled(false);
        }
        else{
            envelope.setEnabled(true);
        }
    }
}

/**
 * This class is used to display the details of Prepaid Box services.
 * @author Wei Jun Shong
 * @author Beh He-Hong
 */
class PrepaidBox implements ActionListener {
    private double price = 0;
    private int One, Two;
    public static double charge2;
    static String summery;
    private int index;
    private String [] indexNum = new String[3];
    private String size;
    private JCheckBox[] prepaid = new JCheckBox[3];
    private int flag1;
    private int flag2;
    private int flag3;
    public static double total = 0;
    private static DecimalFormat df = new DecimalFormat("0.00");

    /**
     * This constructor displays the menubar, data table for Prepaid Box, sizes, package quantity and weight.
     */
    public PrepaidBox(){
        JFrame frame = new JFrame("Prepaid Box");

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

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

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JLabel label1 = new JLabel("Prepaid Box: ");
        label1.setBounds(10,230,250,25);
        frame.add(label1);

        prepaid[0] = new JCheckBox("S");
        prepaid[0].setBounds(200, 230, 50, 25);
        prepaid[1] = new JCheckBox("M");
        prepaid[1].setBounds(250, 230, 50, 25);
        prepaid[2] = new JCheckBox("L");
        prepaid[2].setBounds(300, 230, 50, 25);

        for (int i = 0; i < 3; i++) {
            frame.add(prepaid[i]);
            prepaid[i].addActionListener(this);
        }

        JLabel label2 = new JLabel("Package Quantity: ");
        label2.setBounds(10,280,250,25);
        frame.add(label2);
        JTextField textField1 = new JTextField(20);
        textField1.setBounds(200,280,200,25);
        frame.add(textField1);

        JLabel label3 = new JLabel("Package Weight(g): ");
        label3.setBounds(10,330,250,25);
        frame.add(label3);
        JTextField textField2 = new JTextField(20);
        textField2.setBounds(200,330,200,25);
        frame.add(textField2);

        JButton button = new JButton("Confirm");
        button.setBounds(100,390,80,25);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JCheckBox box: prepaid){
                    if(box.isSelected()) {
                        int dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                        if (dialogButton == JOptionPane.YES_OPTION) {
                            if (e.getSource() == button) {
                                One = Integer.parseInt(textField1.getText());
                                Two = (int) Double.parseDouble(textField2.getText());

                                if (index == 0) {
                                    if (Two > 1000 && Two <= 2000) {
                                        price = 13.78;
                                        flag1 = 1;
                                    }
                                    if (Two <= 1000) {
                                        JOptionPane.showMessageDialog(null, "Please enter above 1kg");
                                        textField2.setText("");
                                        flag1 = 0;
                                    }
                                    if (Two > 2000) {
                                        JOptionPane.showMessageDialog(null, "Please enter 2kg or below");
                                        textField2.setText("");
                                        flag1 = 0;
                                    }
                                } else if (index == 1) {
                                    if (Two > 2000 && Two <= 5000) {
                                        price = 21.20;
                                        flag2 = 2;
                                    }
                                    if (Two <= 2000) {
                                        JOptionPane.showMessageDialog(null, "Please enter above 2kg");
                                        textField2.setText("");
                                        flag2 = 0;
                                    }
                                    if (Two > 5000) {
                                        JOptionPane.showMessageDialog(null, "Please enter 5kg or below");
                                        textField2.setText("");
                                        flag2 = 0;
                                    }

                                } else if (index == 2) {
                                    if (Two > 5000 && Two <= 10000) {
                                        price = 31.80;
                                        flag3 = 3;
                                    }
                                    if (Two <= 5000) {
                                        JOptionPane.showMessageDialog(null, "Please enter above 5kg");
                                        textField2.setText("");
                                        flag3 = 0;
                                    }
                                    if (Two > 10000) {
                                        JOptionPane.showMessageDialog(null, "Please enter 10kg or below");
                                        textField2.setText("");
                                        flag3 = 0;
                                    }
                                }

                                if (flag1 == 1 || flag2 == 2 || flag3 == 3) {
                                    if (Two <= 10000) {
                                        charge2 = price * One;

                                        summery = ("\nCustomer Name : " + Choice.One) + ("\nPrepaid Box") + ("\nPackage Size : " + size) + ("\nPackage Quantity : " + One) + ("\nPackage Weight(g) : " + Two + "g") + ("\nCharge : RM" + df.format(charge2) + "\n") + ("Total Charge : RM" + df.format(total += charge2)) + ("\nTotal Charge of Prepaid Box and Envelope : RM" + df.format(total + Envelope.total)) + ("\nRM" + df.format(total + Envelope.total));

                                        String Data1 = PrepaidBox.summery;

                                        try {
                                            BufferedWriter reader = new BufferedWriter(new FileWriter(new File("Prepaid Box and Envelope.txt"), true));
                                            reader.write(Data1);
                                            reader.newLine();
                                            reader.close();

                                        } catch (IOException E) {
                                            System.out.println("Error is " + E);
                                        }

                                        textField1.setText("");
                                        textField2.setText("");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        frame.add(button);

        JButton button2 = new JButton("Back");
        button2.setBounds(200,390,80,25);//200,130,80,25)

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PrepaidBoxEnvelope();
                frame.dispose();
            }
        });
        frame.add(button2);

        String data[][]={ {"Saiz","         280mm x 200mm","         380mm x 320mm","   340mm x 250mm x 80mm","   340mm x 250mm x 150mm","   380mm x 320mm x 200mm"},
                {"Berat Max ","                    500g","                    1kg","                    2kg","                    5kg","                    10kg"},
                {"Harga ","                 RM7.31","                 RM10.49","                 RM13.78","                 RM21.20","                 RM31.80"}
        };

        String column[]={"Jenis","Envelope S","Envelope L","Prepaid Box S","Prepaid Box M","Prepaid Box L"};
        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);

        /**
         * This image is used as the background of the program.
         */
        BufferedImage img = null;
        try {
            img = ImageIO.read(this.getClass().getResource("/photo_2020-06-25_04-40-46.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        ImageIcon icon3=new ImageIcon(img);
        JLabel label6 = new JLabel();
        label6.setIcon(icon3);
        label6.setBounds(195,90,1000,500);
        frame.add(label6);



        frame.add(sp);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    /**
     *
     * @param e This method is for customer's Prepaid Box size option.
     */
    public void actionPerformed(ActionEvent e) {
        int max = 0;

        for (int i = 0; i < 3; i++) {
            if (prepaid[i].isSelected()) {
                max++;
                index= Arrays.asList(prepaid).indexOf(prepaid[i]);

                if(index == 0){
                    indexNum[0]="S";
                }
                if(index == 1){
                    indexNum[1]="M";
                }
                if(index == 2){
                    indexNum[2]="L";
                }
                size=indexNum[i];

            }
        }

        for (JCheckBox box : prepaid) {
            if (max == 1) {
                if (!box.isSelected()) {
                    box.setEnabled(false);
                }
            } else {
                if (max < 1) {
                    box.setEnabled(true);
                }
            }
        }
    }
}


/**
 * This class is used to display the details of Envelope services.
 * @author Wei Jun Shong
 * @author Beh He-Hong
 */
class Envelope implements ActionListener {
    private double price = 0;
    private int One, Two;
    public static double charge2;
    static String summery;
    private JCheckBox[] envelope = new JCheckBox[2];
    private int index;
    private String [] indexNum = new String[2];
    private String size;
    private int flag1;
    private int flag2;
    public static double total = 0;
    private static DecimalFormat df = new DecimalFormat("0.00");

    /**
     * This constructor displays the menubar, data table for Envelope, sizes, package quantity and weight.
     */
    public Envelope(){
        JFrame frame = new JFrame("Envelope");

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

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

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel panel = new JPanel();
        frame.add(panel);

        JLabel label1 = new JLabel("Envelope: ");
        label1.setBounds(10,230,250,25);
        frame.add(label1);

        //Check Boxes
        envelope[0] = new JCheckBox("S");
        envelope[0].setBounds(200, 230, 50, 25);
        envelope[1] = new JCheckBox("L");
        envelope[1].setBounds(250, 230, 50, 25);

        for (int i = 0; i < 2; i++) {
            frame.add(envelope[i]);
            envelope[i].addActionListener(this);
        }

        JLabel label2 = new JLabel("Package Quantity: ");
        label2.setBounds(10,280,250,25);
        frame.add(label2);
        JTextField textField1 = new JTextField(20);
        textField1.setBounds(200,280,200,25);
        frame.add(textField1);

        JLabel label3 = new JLabel("Package Weight(g): ");
        label3.setBounds(10,330,250,25);
        frame.add(label3);
        JTextField textField2 = new JTextField(20);
        textField2.setBounds(200,330,200,25);
        frame.add(textField2);

        JButton button = new JButton("Confirm");
        button.setBounds(100,390,80,25);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JCheckBox box: envelope){
                    if(box.isSelected()) {
                        int dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                        if (dialogButton == JOptionPane.YES_OPTION) {
                            if (e.getSource() == button) {

                                One = Integer.parseInt(textField1.getText());
                                Two = (int) Double.parseDouble(textField2.getText());


                                if(index==0) {
                                    if (Two <= 500) {
                                        price = 7.31;
                                        flag1 = 1;
                                    }
                                    if (Two > 500) {
                                        JOptionPane.showMessageDialog(null, "Please enter 500gram or below");
                                        textField2.setText("");
                                        flag1 = 0;
                                    }
                                    if(Two<=0) {
                                        JOptionPane.showMessageDialog(null, "Please enter a proper value");
                                        textField2.setText("");
                                        flag1 = 0;
                                    }
                                }
                                if(index==1) {
                                    if (Two > 500 && Two <= 1000) {
                                        price = 10.49;
                                        flag2 = 2;
                                    }
                                    if(Two<=500){
                                        JOptionPane.showMessageDialog(null, "Please enter above 500gram");
                                        textField2.setText("");
                                        flag2 = 0;
                                    }
                                    if(Two>1000){
                                        JOptionPane.showMessageDialog(null, "Please enter 1kg or below");
                                        textField2.setText("");
                                        flag2 = 0;
                                    }
                                }

                                if (flag1 == 1 || flag2 == 2) {
                                    if (Two <= 1000) {
                                        charge2 = price * One;

                                        summery = ("\nCustomer Name : " + Choice.One) + ("\nEnvelope") + ("\nPackage Size : " + size) + ("\nPackage Quantity: " + One) + ("\nPackage Weight : " + Two + "g") + ("\nCharge : RM" + df.format(charge2) + "\n") + ("Total Charge : RM" + df.format(total += charge2)) + ("\nTotal Charge of Prepaid Box and Envelope : RM" + df.format(total + PrepaidBox.total)) + ("\nRM" + df.format(total + PrepaidBox.total));

                                        String Data = Envelope.summery;
                                        try {
                                            BufferedWriter reader = new BufferedWriter(new FileWriter(new File("Prepaid Box and Envelope.txt"), true));
                                            reader.write(Data);
                                            reader.newLine();
                                            reader.close();

                                        } catch (IOException E) {
                                            System.out.println("Error is " + E);
                                        }

                                        textField1.setText("");
                                        textField2.setText("");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        frame.add(button);

        JButton button2 = new JButton("Back");
        button2.setBounds(200,390,80,25);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PrepaidBoxEnvelope();
                frame.dispose();
            }
        });
        frame.add(button2);

        String data[][]={ {"Saiz","         280mm x 200mm","         380mm x 320mm","   340mm x 250mm x 80mm","   340mm x 250mm x 150mm","   380mm x 320mm x 200mm"},
                {"Berat Max ","                    500g","                    1kg","                    2kg","                    5kg","                    10kg"},
                {"Harga ","                 RM7.31","                 RM10.49","                 RM13.78","                 RM21.20","                 RM31.80"}
        };

        String column[]={"Jenis","Envelope S","Envelope L","Prepaid Box S","Prepaid Box M","Prepaid Box L"};
        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);

        /**
         * This image is used as the background of the program.
         */
        BufferedImage img = null;
        try {
            img = ImageIO.read(this.getClass().getResource("/photo_2020-06-25_01-39-52.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageIcon icon=new ImageIcon(img);
        JLabel label4 = new JLabel();
        label4.setIcon(icon);
        label4.setBounds(682,308,1000,130);
        frame.add(label4);


        /**
         * This image is used as the background of the program.
         */
        try {
            img = ImageIO.read(this.getClass().getResource("/photo_2020-06-25_03-42-57.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageIcon icon2=new ImageIcon(img);
        JLabel label5 = new JLabel();
        label5.setIcon(icon2);
        label5.setBounds(50,90,1000,130);
        frame.add(label5);


        /**
         * This image is used as the background of the program.
         */
        try {
            img = ImageIO.read(this.getClass().getResource("/photo_2020-06-25_03-54-00.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageIcon icon3=new ImageIcon(img);
        JLabel label6 = new JLabel();
        label6.setIcon(icon3);
        label6.setBounds(150,90,1000,130);
        frame.add(label6);


        /**
         * This image is used as the background of the program.
         */
        try {
            img = ImageIO.read(this.getClass().getResource("/photo_2020-06-25_04-20-05.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageIcon icon4=new ImageIcon(img);
        JLabel label7 = new JLabel();
        label7.setIcon(icon4);
        label7.setBounds(700,90,1000,130);
        frame.add(label7);

        frame.add(sp);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    /**
     *
     * @param e This method is for customer's Envelope size option.
     */
    public void actionPerformed(ActionEvent e) {
        int max = 0;

        for (int i = 0; i < 2; i++) {
            if (envelope[i].isSelected()) {
                max++;
                index= Arrays.asList(envelope).indexOf(envelope[i]);

                if(index == 0){
                    indexNum[0]="S";
                }

                if(index == 1){
                    indexNum[1]="L";
                }

                size=indexNum[i];
            }
        }

        for (JCheckBox box : envelope) {
            if (max == 1) {
                if (!box.isSelected()) {
                    box.setEnabled(false);
                }
            } else {
                if (max < 1) {
                    box.setEnabled(true);
                }
            }
        }
    }
}

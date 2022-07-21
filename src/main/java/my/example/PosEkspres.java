package my.example;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This class is used to display the details of Pos Ekspres services.
 * @author Wei Jun Shong
 * @author Beh He-Hong
 */
public class PosEkspres extends JFrame implements ActionListener {

    private JLabel label;
    private JRadioButton[]type = new JRadioButton[4];
    private String [] indexNum = new String[4];
    private double price = 0;
    private int quantity;
    private int weight;
    static String summery;
    private int index;
    private String size;
    private int flag1;
    private int flag2;
    private int flag3;
    private int flag4;
    /**
     * The amount needed to be paid by customer based on weight and quantity.
     */
    public static double total = 0;
    /**
     * The total amount needed to be paid by customer based on weight and quantity.
     */
    public static double totalprice;
    private static DecimalFormat df = new DecimalFormat("0.00");


    /**
     * This constructor displays the Pos Ekspres standard types, package quantity and package weight.
     */
    public PosEkspres(){

        JFrame frame = new JFrame("Pos Ekspres");

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

        label = new JLabel("Please choose type of package");
        label.setBounds(50,0,300,20);
        add(label);

        type[0] = new JRadioButton("LG");
        type[0].setBounds(10, 230, 90, 20);
        type[1] = new JRadioButton("LE(C5)");
        type[1].setBounds(110, 230, 90, 20);
        type[2] = new JRadioButton("LD(B4)");
        type[2].setBounds(210, 230, 90, 20);
        type[3] = new JRadioButton("LK");
        type[3].setBounds(310, 230, 90, 20);

        for(int i=0; i<4; i++){
            frame.add(type[i]);
            type[i].addActionListener(this);
        }

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

        String data[][]={ {"Saiz","                  220mm x 110mm","                  229mm x 162mm","                  353mm x 250mm","                  340mm x 250mm"},
                {"Berat Max (gm)","                             100","                             250","                             500","                             1000"},
                {"Ketebalan Max","                            3mm","                            5mm","                           10mm","                            25mm"},
                {"Harga (RM)","                             3.18","                             3.71","                             4.77","                             7.42"}
        };
        String column[]={"Jenis","LG","LE(C5)","LD(B4)","LK"};
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
            img = ImageIO.read(this.getClass().getResource("/photo_2020-06-25_03-54-00.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageIcon icon3=new ImageIcon(img);
        JLabel label6 = new JLabel();
        label6.setIcon(icon3);
        label6.setBounds(200,90,1000,130);
        frame.add(label6);

        JLabel label1 = new JLabel("Package Quantity: ");
        label1.setBounds(10,280,150,25);
        frame.add(label1);
        JTextField textField1 = new JTextField(20);
        textField1.setBounds(130,280,250,25);
        frame.add(textField1);

        JLabel label2 = new JLabel("Package Weight(g): ");
        label2.setBounds(10,330,150,25);
        frame.add(label2);
        JTextField textField2 = new JTextField(20);
        textField2.setBounds(130,330,250,25);
        frame.add(textField2);

        JButton button = new JButton("Confirm");
        button.setBounds(100,390,80,25);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JRadioButton jRadioButton: type) {
                    if (jRadioButton.isSelected()) {
                        int dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                        if (dialogButton == JOptionPane.YES_OPTION) {
                            if (e.getSource() == button) {
                                quantity = Integer.parseInt(textField1.getText());

                                weight = (int) Double.parseDouble(textField2.getText());

                                if (index==0) {
                                    if (weight <= 100) {
                                        price = 3.18;
                                        flag1=1;
                                    }
                                    if (weight > 100) {
                                        JOptionPane.showMessageDialog(null, "Please enter 100gm or below");
                                        textField2.setText("");
                                        flag1=0;
                                    }
                                    if(weight<=0){
                                        JOptionPane.showMessageDialog(null, "Please enter a proper value");
                                        textField2.setText("");
                                        flag1=0;
                                    }
                                } else if (index==1) {
                                    if (weight > 100 && weight <= 250) {
                                        price = 3.71;
                                        flag2=2;
                                    } if (weight > 250) {
                                        JOptionPane.showMessageDialog(null, "Please enter 250gm or below");
                                        textField2.setText("");
                                        flag2=0;
                                    }
                                    if(weight <= 100){
                                        JOptionPane.showMessageDialog(null, "Please enter above 100gm");
                                        textField2.setText("");
                                        flag2=0;
                                    }
                                } else if (index==2) {
                                    if (weight > 250 && weight <= 500) {
                                        price = 4.77;
                                        flag3=3;
                                    }if (weight > 500) {
                                        JOptionPane.showMessageDialog(null, "Please enter 500gm or below");
                                        textField2.setText("");
                                        flag3=0;
                                    }
                                    if(weight<=250){
                                        JOptionPane.showMessageDialog(null, "Please enter above 250gm");
                                        textField2.setText("");
                                        flag3=0;
                                    }
                                } else if (index==3) {
                                    if (weight > 500 && weight <= 1000) {
                                        price = 7.42;
                                        flag4=4;
                                    } if (weight > 1000) {
                                        JOptionPane.showMessageDialog(null, "Please enter 1kg or below");
                                        textField2.setText("");
                                        flag4=0;
                                    }
                                    if (weight<=500){
                                        JOptionPane.showMessageDialog(null, "Please enter above 500gm");
                                        textField2.setText("");
                                        flag4=0;
                                    }
                                }

                                if (flag1==1 || flag2==2 || flag3==3|| flag4==4) {
                                    if (weight <= 1000) {
                                        totalprice = price * quantity;

                                        summery = ("\nCustomer Name : " + Choice.One) + ("\nPos Ekspres") + ("\nPackage Type : " + size) + ("\nPackage Quantity : " + quantity) + ("\nPackage Weight : " + weight + "g") + ("\nCharge : RM" + df.format(totalprice) + "\n") + ("Total Charge : RM" + df.format(total += totalprice)) + ("\nRM" + df.format(total));

                                        String Data = PosEkspres.summery;
                                        try {
                                            BufferedWriter reader = new BufferedWriter(new FileWriter(new File("Pos Ekspres.txt"), true));
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
                }}
        });
        frame.add(button);

        JButton button2 = new JButton("Back");
        button2.setBounds(200,390,80,25);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainFrame();
                frame.dispose();
            }
        });
        frame.add(button2);

        frame.add(sp);
        frame.setSize(1000,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    /**
     *
     * @param e This method is for customer's Pos Ekspres standard types option.
     */
    public void actionPerformed(ActionEvent e){
        int max = 0;

        for(int i=0; i<4; i++){
            if(type[i].isSelected()){
                max++;
                index= Arrays.asList(type).indexOf(type[i]);

                if(index == 0){
                    indexNum[0]="LG";
                }
                if(index == 1){
                    indexNum[1]="LE(C5)";
                }
                if(index == 2){
                    indexNum[2]="LD(B4)";
                }
                if(index == 3){
                    indexNum[3]="LK";
                }
                size=indexNum[i];
            }
        }

        for(JRadioButton button: type){
            if (max == 1){
                if(!button.isSelected()){
                    button.setEnabled(false);
                }
            }
            else{
                if(max < 1){
                    button.setEnabled(true);
                }
            }
        }
    }
}

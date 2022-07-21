package my.example;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


/**
 * This class is used to display the details of Next Day Delivery services.
 * @author Wei Jun Shong
 * @author Beh He-Hong
 */
public class NextDayDelivery implements ActionListener {
    private String One;
    private int Two;
    /**
     * The amount needed to be paid by customer based on document or parcel.
     */
    public static double charge1;
    /**
     * The total amount needed to be paid by customer based on document or parcel.
     */
    public static double total = 0;
    static String summery;
    private JCheckBox[] zone = new JCheckBox[5];

    private int index;
    private int flag1;
    private int flag2;
    private int flag3;
    private int flag4;
    private int flag5;
    private static DecimalFormat df = new DecimalFormat("0.00");

    /**
     * This constructor displays the menubar, data table for document and parcel, zones, types of package and weight for user to input.
     */
    public NextDayDelivery() {
        JFrame frame = new JFrame("Next-Day Delivery");

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

        String columnA1[]={"","Document (2kg and below)","Parcel (above 2kg)"};
        String columnA2[]={" ","First 500gm","Subsequent 250gm","2.001-2.5kg","Subsequent 500gm"};
        String data1[][]={ {"Zone 1","                        4.90","                        0.80","                        10.50","                        0.50"},
                {"Zone 2","                        5.40","                        1.00","                        16.00","                        2.00"},
                {"Zone 3","                        6.90","                        1.50","                        21.00","                        3.00"},
                {"Zone 4","                        7.40","                        1.50","                        26.00","                        3.50"},
                {"Zone 5","                        7.90","                        2.00","                        31.00","                        4.00"},
        };

        String data0[][]={};

        JTable jt2=new JTable(data0,columnA1);
        jt2.getColumnModel().getColumn(0).setPreferredWidth(5);
        jt2.getColumnModel().getColumn(1).setPreferredWidth(210);
        jt2.getColumnModel().getColumn(2).setPreferredWidth(210);
        jt2.setPreferredScrollableViewportSize(jt2.getPreferredSize());

        JTable jt=new JTable(data1,columnA2);

        JScrollPane sp2=new JScrollPane(jt2);
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







        zone[0] = new JCheckBox("Zone 1");
        zone[0].setBounds(10, 180, 90, 20);//150
        zone[1] = new JCheckBox("Zone 2");
        zone[1].setBounds(110, 180, 90, 20);
        zone[2] = new JCheckBox("Zone 3");
        zone[2].setBounds(210, 180, 90, 20);
        zone[3] = new JCheckBox("Zone 4");
        zone[3].setBounds(310, 180, 90, 20);
        zone[4] = new JCheckBox("Zone 5");
        zone[4].setBounds(410, 180, 90, 20);

        for (int i = 0; i < 5; i++) {
            frame.add(zone[i]);
            zone[i].addActionListener(this);
        }

        JLabel label1 = new JLabel("Type of package(Document/Parcel): ");
        label1.setBounds(10, 230, 250, 25);
        frame.add(label1);
        JTextField textField1 = new JTextField(20);
        textField1.setBounds(220, 230, 150, 25);
        frame.add(textField1);

        JLabel label2 = new JLabel("Package Weight(g): ");
        label2.setBounds(10, 280, 250, 25);
        frame.add(label2);
        JTextField textField2 = new JTextField(20);
        textField2.setBounds(220, 280, 150, 25);
        frame.add(textField2);

        JButton button = new JButton("Confirm");
        button.setBounds(100, 350, 80, 25);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(JCheckBox box: zone){
                    if(box.isSelected()) {
                        int dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                        if (dialogButton == JOptionPane.YES_OPTION) {
                            if (e.getSource() == button) {
                                One = textField1.getText().trim();
                                One.toUpperCase();
                                Two = (int) Double.parseDouble(textField2.getText());

                                switch (index) {
                                    case 0: {
                                        if (One.toUpperCase().equals("DOCUMENT")) {
                                            if (Two <= 2000) {
                                                if (Two <= 2000) {
                                                    charge1 = 4.90 + (Two - 500) / 250 * 0.80;
                                                }
                                                if (Two <= 500) {
                                                    charge1 = 4.90;
                                                }
                                                flag1=1;
                                            }
                                            if (Two > 2000) {
                                                JOptionPane.showMessageDialog(null, "Please select PARCEL if more than 2000");
                                                textField1.setText("");
                                                flag1=0;
                                            }
                                            if (Two<=0){
                                                JOptionPane.showMessageDialog(null, "Please enter more than 0");
                                                textField2.setText("");
                                                flag1=0;
                                            }
                                        }
                                        else if (One.toUpperCase().equals("PARCEL")) {
                                            if (Two > 2000) {
                                                if (Two <= 2500) {
                                                    charge1 = 10.50;
                                                }
                                                if (Two > 2500) {
                                                    charge1 = 10.50 + (Two - 2500) / 500 * 0.50;
                                                }
                                                flag1=1;
                                            }
                                            if (Two <= 2000) {
                                                JOptionPane.showMessageDialog(null, "Please select DOCUMENT for 2kg and below");
                                                textField1.setText("");
                                                flag1=0;
                                            }
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Please enter DOCUMENT or PARCEL");
                                            textField1.setText("");
                                        }
                                    }
                                    break;

                                    case 1: {
                                        if (One.toUpperCase().equals("DOCUMENT")) {
                                            if (Two <= 2000) {
                                                if (Two <= 2000) {
                                                    charge1 = 5.40 + (Two - 500) / 250 * 1.00;
                                                }
                                                if (Two <= 500) {
                                                    charge1 = 5.40;
                                                }
                                                flag2=2;
                                            }
                                            if (Two > 2000) {
                                                JOptionPane.showMessageDialog(null, "Please select PARCEL if more than 2000");
                                                textField1.setText("");
                                                flag2=0;
                                            }
                                            if (Two<=0){
                                                JOptionPane.showMessageDialog(null, "Please enter more than 0");
                                                textField2.setText("");
                                                flag2=0;
                                            }
                                        } else if (One.toUpperCase().equals("PARCEL")) {
                                            if (Two > 2000) {
                                                if (Two <= 2500) {
                                                    charge1 = 16.00;
                                                }
                                                if (Two > 2500) {
                                                    charge1 = 16.00 + (Two - 2500) / 500 * 2.00;
                                                }
                                                flag2=2;
                                            }
                                            if (Two <= 2000) {
                                                JOptionPane.showMessageDialog(null, "Please select DOCUMENT for 2kg and below");
                                                textField1.setText("");
                                                flag2=0;
                                            }
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Please enter DOCUMENT or PARCEL");
                                            textField1.setText("");
                                        }
                                    }
                                    break;

                                    case 2: {
                                        if (One.toUpperCase().equals("DOCUMENT")) {
                                            if (Two <= 2000) {
                                                if (Two <= 2000) {
                                                    charge1 = 6.90 + (Two - 500) / 250 * 1.50;
                                                }
                                                if (Two <= 500) {
                                                    charge1 = 6.90;
                                                }
                                                flag3=3;
                                            }
                                            if (Two > 2000) {
                                                JOptionPane.showMessageDialog(null, "Please select PARCEL if more than 2000");
                                                textField1.setText("");
                                                flag3=0;
                                            }
                                            if (Two<=0){
                                                JOptionPane.showMessageDialog(null, "Please enter more than 0");
                                                textField2.setText("");
                                                flag3=0;
                                            }
                                        } else if (One.toUpperCase().equals("PARCEL")) {
                                            if (Two > 2000) {
                                                if (Two <= 2500) {
                                                    charge1 = 21.00;
                                                }
                                                if (Two > 2500) {
                                                    charge1 = 21.00 + (Two - 2500) / 500 * 3.00;
                                                }
                                                flag3=3;
                                            }
                                            if (Two <= 2000) {
                                                JOptionPane.showMessageDialog(null, "Please select DOCUMENT for 2kg and below");
                                                textField1.setText("");
                                                flag3=0;
                                            }
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Please enter DOCUMENT or PARCEL");
                                            textField1.setText("");
                                        }
                                    }
                                    break;

                                    case 3: {
                                        if (One.toUpperCase().equals("DOCUMENT")) {
                                            if (Two <= 2000) {
                                                if (Two <= 2000) {
                                                    charge1 = 7.40 + (Two - 500) / 250 * 1.50;
                                                }
                                                if (Two <= 500) {
                                                    charge1 = 7.40;
                                                }
                                                flag4=4;
                                            }
                                            if (Two > 2000) {
                                                JOptionPane.showMessageDialog(null, "Please select PARCEL if more than 2000");
                                                textField1.setText("");
                                                flag4=0;
                                            }
                                            if (Two<=0){
                                                JOptionPane.showMessageDialog(null, "Please enter more than 0");
                                                textField2.setText("");
                                                flag4=0;
                                            }
                                        } else if (One.toUpperCase().equals("PARCEL")) {
                                            if (Two > 2000) {
                                                if (Two <= 2500) {
                                                    charge1 = 26.00;
                                                }
                                                if (Two > 2500) {
                                                    charge1 = 26.00 + (Two - 2500) / 500 * 3.50;
                                                }
                                                flag4=4;
                                            }
                                            if (Two <= 2000) {
                                                JOptionPane.showMessageDialog(null, "Please select DOCUMENT for 2kg and below");
                                                textField1.setText("");
                                                flag4=0;
                                            }
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Please enter DOCUMENT or PARCEL");
                                            textField1.setText("");
                                        }
                                    }
                                    break;

                                    case 4: {

                                        if (One.toUpperCase().equals("DOCUMENT")) {
                                            if (Two <= 2000) {
                                                if (Two <= 2000) {
                                                    charge1 = 7.90 + (Two - 500) / 250 * 2.00;
                                                }
                                                if (Two <= 500) {
                                                    charge1 = 7.90;
                                                }
                                                flag5=5;
                                            }
                                            if (Two > 2000) {
                                                JOptionPane.showMessageDialog(null, "Please select PARCEL if more than 2000");
                                                textField1.setText("");
                                                flag5=0;
                                            }
                                            if (Two<=0){
                                                JOptionPane.showMessageDialog(null, "Please enter more than 0");
                                                textField2.setText("");
                                                flag5=0;
                                            }
                                        } else if (One.toUpperCase().equals("PARCEL")) {
                                            if (Two > 2000) {
                                                if (Two <= 2500) {
                                                    charge1 = 31.00;
                                                }
                                                if (Two > 2500) {
                                                    charge1 = 31.00 + (Two - 2500) / 500 * 4.00;
                                                }
                                                flag5=5;
                                            }
                                            if (Two <= 2000) {
                                                JOptionPane.showMessageDialog(null, "Please select DOCUMENT for 2kg and below");
                                                textField1.setText("");
                                                flag5=0;
                                            }
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Please enter DOCUMENT or PARCEL");
                                            textField1.setText("");
                                        }
                                    }
                                    break;
                                }

                                if (flag1==1 || flag2==2 || flag3==3 || flag4==4 || flag5==5) {
                                    summery = ("\nCustomer Name : " + Choice.One) + ("\nNext-Day Delivery") + ("\nZone " + (index + 1)) + ("\nType of package: " + One.toUpperCase()) + ("\nPackage Weight : " + Two + "g") + ("\nCharge : RM" + df.format(charge1)) + ("\nTotal Charge : RM" + df.format(total+=charge1)) + ("\nRM" + df.format(total));

                                    String Data = NextDayDelivery.summery;
                                    try {
                                        BufferedWriter reader = new BufferedWriter(new FileWriter(new File("NextDayDelivery.txt"), true));
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
        });

        frame.add(button);

        JButton button2 = new JButton("Back");
        button2.setBounds(200, 350, 80, 25);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new MainFrame().setVisible(true);
                frame.dispose();
            }
        });
        frame.add(button2);

        frame.add(sp2, BorderLayout.NORTH);
        frame.add(sp, BorderLayout.CENTER);
        frame.setSize(1000,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    /**
     *
     * @param e This method is for customer's Next Day Delivery zone option.
     */
    public void actionPerformed(ActionEvent e) {
        int max = 0;

        for (int i = 0; i < 5; i++) {
            if (zone[i].isSelected()) {
                max++;
                index= Arrays.asList(zone).indexOf(zone[i]);
            }
        }

        for (JCheckBox box : zone) {
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

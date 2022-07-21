package my.example;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This class is used to display the details of Same Day Delivery services.
 * @author Wei Jun Shong
 * @author Beh He-Hong
 */
public class SameDayDelivery {

    private String area;
    private int weight;
    private double surcharge = 0;
    private double domestic_charge = 0;
    static String summery;
    private static DecimalFormat df = new DecimalFormat("0.00");

    /**
     * The amount needed to be paid by customer based on the town area and package weight.
     */
    public static double payment = 0;
    /**
     * The total amount needed to be paid by customer based on the town area and package weight.
     */
    public static double total = 0;

    /**
     * This constructor displays the menubar, data table for local town and cross town, town area and package weight for user to input.
     */
    public SameDayDelivery(){
        JFrame frame = new JFrame("Same Day Delivery");

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

        JLabel label1 = new JLabel("Town Area(Local/Cross): ");
        label1.setBounds(10,230,250,25);
        frame.add(label1);
        JTextField textField1 = new JTextField(20);
        textField1.setBounds(220,230,150,25);
        frame.add(textField1);

        JLabel label2 = new JLabel("Package Weight(g): ");
        label2.setBounds(10, 280,250,25 );
        frame.add(label2);
        JTextField textField2 = new JTextField(20);
        textField2.setBounds(220,280,150,25);
        frame.add(textField2);

        JButton button = new JButton("Confirm");
        button.setBounds(100,350,80,25);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int dialogButton = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (dialogButton == JOptionPane.YES_OPTION) {
                    if (e.getSource() == button) {

                        area = textField1.getText().trim();

                        weight = (int) Double.parseDouble(textField2.getText());

                        switch (area.toUpperCase()) {
                            case "LOCAL":
                                if (weight <= 500) {
                                    domestic_charge = 4.90;
                                    surcharge = 6.00;
                                }
                                if (weight > 500 && weight <= 750) {
                                    domestic_charge = 5.70;
                                    surcharge = 6.00;
                                }
                                if (weight > 750 && weight <= 1000) {
                                    domestic_charge = 6.50;
                                    surcharge = 6.00;
                                }
                                if (weight > 1000) {
                                    JOptionPane.showMessageDialog(null, "Please enter 1kg or below");
                                    textField2.setText("");
                                }
                                break;
                            case "CROSS":
                                if (weight <= 500) {
                                    domestic_charge = 5.40;
                                    surcharge = 7.50;
                                }
                                if (weight > 500 && weight <= 750) {
                                    domestic_charge = 6.40;
                                    surcharge = 7.50;
                                }
                                if (weight > 750 && weight <= 1000) {
                                    domestic_charge = 7.40;
                                    surcharge = 7.50;
                                }
                                if (weight > 1000) {
                                    JOptionPane.showMessageDialog(null, "Please enter 1kg or below");
                                    textField2.setText("");
                                }
                                break;
                            default: {
                                JOptionPane.showMessageDialog(null, "Please select Local or Cross");
                                textField1.setText("");
                                break;
                            }
                        }

                        if (("LOCAL".equals(area.toUpperCase())) || ("CROSS".equals(area.toUpperCase()))) {
                            if (weight <= 1000) {
                                payment = domestic_charge + surcharge;

                                summery = ("\nCustomer Name : " + Choice.One) + ("\nSame-Day Delivery") + ("\nTown Area : " + (area.toUpperCase())) + ("\nPackage Weight : " + weight + "g") + ("\nDomestic Charge : RM" + df.format(domestic_charge)) + ("\nSurcharge : RM" + df.format(surcharge)) + ("\nCharge : RM" + df.format(payment)) + ("\nTotal Charge : RM" + df.format(total+=payment)) + ("\nRM" + df.format(total));


                                String Data = SameDayDelivery.summery;
                                try {
                                    BufferedWriter reader = new BufferedWriter(new FileWriter(new File("SameDayDelivery.txt"), true));
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
        });
        frame.add(button);

        JButton button2 = new JButton("Back");
        button2.setBounds(200, 350, 80, 25);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainFrame();
                frame.dispose();
            }
        });

        frame.add(button2);

        String columnA1[]={"","Local Town","Cross Town"};
        String columnA2[]={" ","Domestic Charge","Surcharge","Total","Domestic Charge","Surcharge","Total"};
        String data1[][]={ {"Below 500gm","                  4.90","                  6.00","                  10.90","                  5.40","                  7.50","                  12.90"},
                {"500gm - 750gm","                  5.70","                  6.00","                  11.70","                  6.40","                  7.50","                  13.90"},
                {"750gm - 1kg","                  6.50","                  6.00","                  12.50","                  7.40","                  7.50","                  14.90"},
        };

        String data0[][]={};

        JTable jt2=new JTable(data0,columnA1);
        jt2.getColumnModel().getColumn(0).setPreferredWidth(5);
        jt2.getColumnModel().getColumn(1).setPreferredWidth(295);
        jt2.getColumnModel().getColumn(2).setPreferredWidth(295);
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

        frame.add(sp2,BorderLayout.NORTH);
        frame.add(sp, BorderLayout.CENTER);
        frame.setSize(1000,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}



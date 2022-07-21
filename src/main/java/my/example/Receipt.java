package my.example;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * This class is for the purpose to display customer's total price.
 * @author Wei Jun Shong
 * @author Beh He-Hong
 */
public class Receipt extends javax.swing.JFrame {
    ArrayList<Price> pricelist;
    String header[] = new String[]{"Name", "Transaction Date", "Transaction Time"};
    DefaultTableModel dtm;

    /**
     *This constructor is to display the format of receipt that has been setup in class initComponents().
     */
    public Receipt() {
        initComponents();
        pricelist = new ArrayList<>();
        dtm = new DefaultTableModel(header, 0);
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is to modify and to organize the format and the visual of receipt that will be displayed.
     */
    private void initComponents(){
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        jLabel11 = new JLabel();
        jLabel12 = new JLabel();
        jLabel13 = new JLabel();
        jLabel14 = new JLabel();
        jLabel15 = new JLabel();
        jLabel16 = new JLabel();
        jLabel17 = new JLabel();
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jPanel3 = new JPanel();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();

        overall = (NextDayDelivery.total + SameDayDelivery.total + Envelope.total + PrepaidBox.total + PosEkspres.total);
        summery = ("\nCustomer Name : " + Choice.One) + ("\nTotal Charge : RM" + df.format(overall)) + ("\nRM" + df.format(overall));
        String Data = Receipt.summery;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Receipt Total Charge.txt"), true));
            writer.write(Data);
            writer.newLine();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader name = new BufferedReader(new FileReader("Name.txt"));
            BufferedReader nextdaydelivery = new BufferedReader(new FileReader("NextDayDelivery.txt"));
            BufferedReader samedaydelivery = new BufferedReader(new FileReader("SameDayDelivery.txt"));
            BufferedReader prepaidboxenvelope = new BufferedReader(new FileReader("Prepaid Box and Envelope.txt"));
            BufferedReader posekspres = new BufferedReader(new FileReader("Pos Ekspres.txt"));
            BufferedReader receipttotal = new BufferedReader(new FileReader("Receipt Total Charge.txt"));

            String read1, read2, read3, read4, read5, read6 = "";

            while((read1 = name.readLine()) != null){
                jLabel10 = new JLabel(read1);
            }
            name.close();

            while ((read2 = nextdaydelivery.readLine()) != null){
                jLabel13 = new JLabel(read2);
            }
            nextdaydelivery.close();

            while((read3 = samedaydelivery.readLine()) != null){
                jLabel14 = new JLabel(read3);
            }
            samedaydelivery.close();

            while ((read4 = prepaidboxenvelope.readLine()) != null){
                jLabel15 = new JLabel(read4);
            }
            prepaidboxenvelope.close();

            while((read5 = posekspres.readLine()) != null){
                jLabel16 = new JLabel(read5);
            }
            posekspres.close();

            while((read6 = receipttotal.readLine()) != null){
                jLabel17 = new JLabel(read6);
            }
            receipttotal.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        jLabel11 = new JLabel(date);

        String time = new SimpleDateFormat("HH:mm:ss").format(new Date());
        jLabel12 = new JLabel(time);

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Receipt");
        setResizable(false);
        setVisible(true);

        jPanel1.setBorder(BorderFactory.createEtchedBorder());

        jLabel2.setText("Name");
        jLabel3.setText("Transaction Date");
        jLabel4.setText("Transaction Time");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel10)
                                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel12))))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel2.setBorder(BorderFactory.createEtchedBorder());

        jLabel5.setText("Next-Day Delivery");
        jLabel6.setText("Same-Day Delivery");
        jLabel7.setText("Prepaid Box & Envelope");
        jLabel8.setText("Pos Ekspres");
        jLabel9.setText("Total charge");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jLabel7)
                                                        .addComponent(jLabel8)
                                                        .addComponent(jLabel9))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel13)
                                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel15)
                                                        .addComponent(jLabel16)
                                                        .addComponent(jLabel17))))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setBorder(BorderFactory.createEtchedBorder());

        jButton1.setText("Reset");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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

                jLabel13.setText("-----------");
                jLabel14.setText("-----------");
                jLabel15.setText("-----------");
                jLabel16.setText("-----------");
                jLabel17.setText("RM0.00");
            }
        });

        jButton2.setText("Back");
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainFrame();
                dispose();
            }
        });

        jButton3.setText("Exit");
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(SwingConstants.HORIZONTAL, new Component[]{jButton1, jButton2, jButton3});

        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2)
                                        .addComponent(jButton3))
                                .addContainerGap())
        );

        jPanel3Layout.linkSize(SwingConstants.VERTICAL, new Component[]{jButton1, jButton2, jButton3});

        javax.swing.GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13))
        );

        pack();
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    static String summery;
    private double overall = 0;
    private static DecimalFormat df = new DecimalFormat("0.00");

    /**
     * This class is to collect the data of user's name and add on the current date and current time.
     * @author Wei Jun Shong
     * @author Beh He-Hong
     */
    class Price {
        String name, transactiondate, transactiontime;

        /**
         * This method is for obtaining the user's name, the current date and current time.
         * @param n Your full name which includes the first name and last name.
         * @param td The current date you use this program.
         * @param tt The current time you use this program.
         */
        public Price(String n, String td, String tt){
            this.name = n;
            this.transactiondate = td;
            this.transactiontime = tt;
        }
    }
}


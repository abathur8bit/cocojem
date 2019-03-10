/*
 * Created by JFormDesigner on Sat Feb 23 15:48:11 EST 2019
 */

package com.axorion.coco;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Lee Patterson
 */
public class Registers extends JDialog {
    AppFrame parent;
    public Registers(AppFrame parent) {
        super(parent);
        initComponents();
        this.parent = parent;
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        registersPanel = new JPanel();
        panel1 = new JPanel();
        label1 = new JLabel();
        label2 = new JTextField();
        panel2 = new JPanel();
        label3 = new JLabel();
        label4 = new JTextField();
        panel3 = new JPanel();
        label5 = new JLabel();
        textField1 = new JTextField();
        panel4 = new JPanel();
        label7 = new JLabel();
        label8 = new JTextField();
        panel5 = new JPanel();
        label9 = new JLabel();
        label10 = new JTextField();
        panel6 = new JPanel();
        label11 = new JLabel();
        label12 = new JTextField();
        panel7 = new JPanel();
        label13 = new JLabel();
        label14 = new JTextField();
        panel8 = new JPanel();
        label15 = new JLabel();
        label16 = new JTextField();
        panel9 = new JPanel();
        label17 = new JLabel();
        label18 = new JTextField();
        panel10 = new JPanel();
        label19 = new JLabel();
        label20 = new JTextField();
        panel11 = new JPanel();
        label21 = new JLabel();
        label22 = new JTextField();

        //======== this ========
        setTitle("Registers");
        setName("Registers");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== registersPanel ========
        {
            registersPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
            registersPanel.setMinimumSize(new Dimension(250, 350));
            registersPanel.setLayout(new BoxLayout(registersPanel, BoxLayout.Y_AXIS));

            //======== panel1 ========
            {
                panel1.setLayout(new GridLayout(0, 2));

                //---- label1 ----
                label1.setText("A");
                panel1.add(label1);

                //---- label2 ----
                label2.setText("00");
                panel1.add(label2);
            }
            registersPanel.add(panel1);

            //======== panel2 ========
            {
                panel2.setLayout(new GridLayout());

                //---- label3 ----
                label3.setText("B");
                panel2.add(label3);

                //---- label4 ----
                label4.setText("00");
                panel2.add(label4);
            }
            registersPanel.add(panel2);

            //======== panel3 ========
            {
                panel3.setLayout(new GridLayout());

                //---- label5 ----
                label5.setText("X");
                panel3.add(label5);

                //---- textField1 ----
                textField1.setText("0000");
                panel3.add(textField1);
            }
            registersPanel.add(panel3);

            //======== panel4 ========
            {
                panel4.setLayout(new GridLayout());

                //---- label7 ----
                label7.setText("Y");
                panel4.add(label7);

                //---- label8 ----
                label8.setText("0000");
                panel4.add(label8);
            }
            registersPanel.add(panel4);

            //======== panel5 ========
            {
                panel5.setLayout(new GridLayout());

                //---- label9 ----
                label9.setText("U");
                panel5.add(label9);

                //---- label10 ----
                label10.setText("0000");
                panel5.add(label10);
            }
            registersPanel.add(panel5);

            //======== panel6 ========
            {
                panel6.setLayout(new GridLayout());

                //---- label11 ----
                label11.setText("S");
                panel6.add(label11);

                //---- label12 ----
                label12.setText("0000");
                panel6.add(label12);
            }
            registersPanel.add(panel6);

            //======== panel7 ========
            {
                panel7.setLayout(new GridLayout());

                //---- label13 ----
                label13.setText("PC");
                panel7.add(label13);

                //---- label14 ----
                label14.setText("0000");
                panel7.add(label14);
            }
            registersPanel.add(panel7);

            //======== panel8 ========
            {
                panel8.setLayout(new GridLayout());

                //---- label15 ----
                label15.setText("V");
                panel8.add(label15);

                //---- label16 ----
                label16.setText("0000");
                panel8.add(label16);
            }
            registersPanel.add(panel8);

            //======== panel9 ========
            {
                panel9.setLayout(new GridLayout());

                //---- label17 ----
                label17.setText("E");
                panel9.add(label17);

                //---- label18 ----
                label18.setText("00");
                panel9.add(label18);
            }
            registersPanel.add(panel9);

            //======== panel10 ========
            {
                panel10.setLayout(new GridLayout());

                //---- label19 ----
                label19.setText("F");
                panel10.add(label19);

                //---- label20 ----
                label20.setText("00");
                panel10.add(label20);
            }
            registersPanel.add(panel10);

            //======== panel11 ========
            {
                panel11.setLayout(new GridLayout());

                //---- label21 ----
                label21.setText("CC");
                panel11.add(label21);

                //---- label22 ----
                label22.setText("00 =");
                panel11.add(label22);
            }
            registersPanel.add(panel11);
        }
        contentPane.add(registersPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JPanel registersPanel;
    private JPanel panel1;
    private JLabel label1;
    private JTextField label2;
    private JPanel panel2;
    private JLabel label3;
    private JTextField label4;
    private JPanel panel3;
    private JLabel label5;
    private JTextField textField1;
    private JPanel panel4;
    private JLabel label7;
    private JTextField label8;
    private JPanel panel5;
    private JLabel label9;
    private JTextField label10;
    private JPanel panel6;
    private JLabel label11;
    private JTextField label12;
    private JPanel panel7;
    private JLabel label13;
    private JTextField label14;
    private JPanel panel8;
    private JLabel label15;
    private JTextField label16;
    private JPanel panel9;
    private JLabel label17;
    private JTextField label18;
    private JPanel panel10;
    private JLabel label19;
    private JTextField label20;
    private JPanel panel11;
    private JLabel label21;
    private JTextField label22;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

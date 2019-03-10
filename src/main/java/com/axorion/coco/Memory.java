/*
 * Created by JFormDesigner on Mon Mar 04 19:37:09 EST 2019
 */

package com.axorion.coco;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Lee Patterson
 */
public class Memory extends JDialog {
    AppFrame parent;
    int[] memory;
    public Memory(Frame owner) {
        super(owner);
        parent = (AppFrame)owner;
        initComponents();
//        memTop.setIcon(new ImageIcon(parent.loadImage("text01.png")));
    }

    public void setDump(int[] memory,int start,int end) {
        memRangeFromTextField.setText(String.format("%04X",start));
        memRangeToTextField.setText(String.format("%04X",end));
        this.memory = memory;
        StringBuilder sb = new StringBuilder();
        StringBuilder addr = new StringBuilder();
        StringBuilder dump = new StringBuilder();
        StringBuilder ascii = new StringBuilder();
        int count=0;
        for(int i=start; i<=end; i++) {
            if(addr.length() == 0) {
                addr.append(String.format("%04X: ",i));
            }

            int mem = memory[i];
            dump.append(String.format("%02X ",mem));
            if(Character.isLetterOrDigit(mem)) {
                ascii.append(String.format("%c",mem));
            } else {
                ascii.append('.');
            }
            count++;
            if(count >= 16) {
                sb.append(addr.toString()+dump.toString()+ascii.toString()+"\n");
                dump.delete(0,dump.length());
                ascii.delete(0,ascii.length());
                addr.delete(0,addr.length());
                count=0;
            }
        }
        if(dump.length()>0) {
            sb.append(addr.toString()+dump.toString()+ascii.toString()+"\n");
        }
        textArea.setText(sb.toString());
        textArea.setCaretPosition(0);
    }

    private void dumpButtonActionPerformed(ActionEvent e) {
        int from = Integer.valueOf(memRangeFromTextField.getText(),16);
        int to = Integer.valueOf(memRangeToTextField.getText(),16);
        setDump(memory,from,to);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        panel4 = new JPanel();
        panel1 = new JPanel();
        panel3 = new JPanel();
        label1 = new JLabel();
        memRangeFromTextField = new JTextField();
        panel2 = new JPanel();
        label2 = new JLabel();
        memRangeToTextField = new JTextField();
        dumpButton = new JButton();
        memPanel = new JPanel();
        scrollPane1 = new JScrollPane();
        textArea = new JTextArea();

        //======== this ========
        setTitle("Memory Dump");
        setMinimumSize(new Dimension(450, 100));
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== panel4 ========
        {
            panel4.setLayout(new BorderLayout());

            //======== panel1 ========
            {
                panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

                //======== panel3 ========
                {
                    panel3.setLayout(new BorderLayout());

                    //---- label1 ----
                    label1.setText("Range From:");
                    panel3.add(label1, BorderLayout.WEST);

                    //---- memRangeFromTextField ----
                    memRangeFromTextField.setText("0400");
                    panel3.add(memRangeFromTextField, BorderLayout.CENTER);
                }
                panel1.add(panel3);

                //======== panel2 ========
                {
                    panel2.setLayout(new BorderLayout());

                    //---- label2 ----
                    label2.setText("To");
                    panel2.add(label2, BorderLayout.LINE_START);

                    //---- memRangeToTextField ----
                    memRangeToTextField.setText("0C00");
                    panel2.add(memRangeToTextField, BorderLayout.CENTER);
                }
                panel1.add(panel2);

                //---- dumpButton ----
                dumpButton.setText("Dump");
                dumpButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dumpButtonActionPerformed(e);
                    }
                });
                panel1.add(dumpButton);
            }
            panel4.add(panel1, BorderLayout.CENTER);
        }
        contentPane.add(panel4, BorderLayout.NORTH);

        //======== memPanel ========
        {
            memPanel.setPreferredSize(new Dimension(225, 100));
            memPanel.setLayout(new GridLayout(0, 1));

            //======== scrollPane1 ========
            {

                //---- textArea ----
                textArea.setFont(new Font("Monospaced", Font.PLAIN, 10));
                textArea.setEditable(false);
                scrollPane1.setViewportView(textArea);
            }
            memPanel.add(scrollPane1);
        }
        contentPane.add(memPanel, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JPanel panel4;
    private JPanel panel1;
    private JPanel panel3;
    private JLabel label1;
    private JTextField memRangeFromTextField;
    private JPanel panel2;
    private JLabel label2;
    private JTextField memRangeToTextField;
    private JButton dumpButton;
    private JPanel memPanel;
    private JScrollPane scrollPane1;
    private JTextArea textArea;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

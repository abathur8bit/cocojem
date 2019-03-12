/* *****************************************************************************
 * Copyright 2018 Lee Patterson <https://github.com/abathur8bit>
 *
 * You may use and modify at will. Please credit me in the source.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ******************************************************************************/

package com.axorion.coco;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author Lee Patterson
 */
public class AboutDialog extends JDialog {
    AppFrame parent;
    public AboutDialog(Frame owner) {
        super(owner);
        this.parent = (AppFrame)owner;
        initComponents();
        icon.setIcon(new ImageIcon(parent.loadImage("CocoJEM-logo01.jpg")));
    }

    public AboutDialog(Dialog owner) {
        super(owner);
        initComponents();
    }

    private void okButtonActionPerformed(ActionEvent e) {
        setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        panel1 = new JPanel();
        panel2 = new JPanel();
        icon = new JLabel();
        panel4 = new JPanel();
        panel3 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        buttonBar = new JPanel();
        okButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BorderLayout());

                //======== panel1 ========
                {
                    panel1.setLayout(new GridLayout(0, 1));

                    //======== panel2 ========
                    {
                        panel2.setLayout(new BorderLayout());

                        //---- icon ----
                        icon.setPreferredSize(new Dimension(128, 128));
                        panel2.add(icon, BorderLayout.LINE_START);

                        //======== panel4 ========
                        {
                            panel4.setBorder(new EmptyBorder(10, 10, 10, 10));
                            panel4.setLayout(new BorderLayout());

                            //======== panel3 ========
                            {
                                panel3.setMinimumSize(new Dimension(200, 66));
                                panel3.setLayout(new GridLayout(0, 1));

                                //---- label1 ----
                                label1.setText("CocoJEM");
                                label1.setHorizontalAlignment(SwingConstants.LEFT);
                                label1.setFont(new Font(".SF NS Text", Font.BOLD, 18));
                                panel3.add(label1);

                                //---- label2 ----
                                label2.setText("Protype 1");
                                label2.setHorizontalAlignment(SwingConstants.LEFT);
                                panel3.add(label2);

                                //---- label3 ----
                                label3.setText("8BitCoder.com");
                                label3.setFont(new Font(".SF NS Text", Font.PLAIN, 10));
                                panel3.add(label3);
                            }
                            panel4.add(panel3, BorderLayout.NORTH);
                        }
                        panel2.add(panel4, BorderLayout.CENTER);
                    }
                    panel1.add(panel2);
                }
                contentPanel.add(panel1, BorderLayout.CENTER);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                okButton.setSelected(true);
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        okButtonActionPerformed(e);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel icon;
    private JPanel panel4;
    private JPanel panel3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JPanel buttonBar;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

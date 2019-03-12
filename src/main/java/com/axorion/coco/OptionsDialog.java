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
public class OptionsDialog extends JDialog {
    public OptionsDialog(Frame owner) {
        super(owner);
        initComponents();
    }

    public OptionsDialog(Dialog owner) {
        super(owner);
        initComponents();
    }

    private void okButtonActionPerformed(ActionEvent e) {
        setVisible(false);
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        tabbedPane1 = new JTabbedPane();
        machinePanel = new JPanel();
        panel3 = new JPanel();
        machineTypePanel = new JPanel();
        radioButton8 = new JRadioButton();
        radioButton9 = new JRadioButton();
        radioButton10 = new JRadioButton();
        specsPanel = new JPanel();
        cpuPanel = new JPanel();
        radioButton6 = new JRadioButton();
        radioButton7 = new JRadioButton();
        monitorTypePanel = new JPanel();
        radioButton11 = new JRadioButton();
        radioButton12 = new JRadioButton();
        memoryPanel = new JPanel();
        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        radioButton3 = new JRadioButton();
        radioButton4 = new JRadioButton();
        radioButton5 = new JRadioButton();
        displayPanel = new JPanel();
        monitorTypePanel2 = new JPanel();
        radioButton13 = new JRadioButton();
        radioButton14 = new JRadioButton();
        checkBox1 = new JCheckBox();
        inputPanel = new JPanel();
        buttonBar = new JPanel();
        cancelButton = new JButton();
        okButton = new JButton();

        //======== this ========
        setResizable(false);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BorderLayout());

                //======== tabbedPane1 ========
                {

                    //======== machinePanel ========
                    {
                        machinePanel.setLayout(new GridLayout());

                        //======== panel3 ========
                        {
                            panel3.setLayout(new GridLayout());

                            //======== machineTypePanel ========
                            {
                                machineTypePanel.setBorder(new TitledBorder("Type"));
                                machineTypePanel.setLayout(new BoxLayout(machineTypePanel, BoxLayout.Y_AXIS));

                                //---- radioButton8 ----
                                radioButton8.setText("Color Computer 1");
                                machineTypePanel.add(radioButton8);

                                //---- radioButton9 ----
                                radioButton9.setText("Color Computer 2");
                                machineTypePanel.add(radioButton9);

                                //---- radioButton10 ----
                                radioButton10.setText("Color Computer 3");
                                radioButton10.setSelected(true);
                                machineTypePanel.add(radioButton10);
                            }
                            panel3.add(machineTypePanel);

                            //======== specsPanel ========
                            {
                                specsPanel.setLayout(new GridLayout(0, 1));

                                //======== cpuPanel ========
                                {
                                    cpuPanel.setBorder(new TitledBorder("CPU"));
                                    cpuPanel.setLayout(new BoxLayout(cpuPanel, BoxLayout.Y_AXIS));

                                    //---- radioButton6 ----
                                    radioButton6.setText("Motorola MC6809");
                                    cpuPanel.add(radioButton6);

                                    //---- radioButton7 ----
                                    radioButton7.setText("Hitachi HD6309");
                                    radioButton7.setSelected(true);
                                    cpuPanel.add(radioButton7);
                                }
                                specsPanel.add(cpuPanel);

                                //======== monitorTypePanel ========
                                {
                                    monitorTypePanel.setBorder(new TitledBorder("Monitor Type"));
                                    monitorTypePanel.setLayout(new BoxLayout(monitorTypePanel, BoxLayout.Y_AXIS));

                                    //---- radioButton11 ----
                                    radioButton11.setText("RGB");
                                    radioButton11.setSelected(true);
                                    monitorTypePanel.add(radioButton11);

                                    //---- radioButton12 ----
                                    radioButton12.setText("Composite");
                                    monitorTypePanel.add(radioButton12);
                                }
                                specsPanel.add(monitorTypePanel);
                            }
                            panel3.add(specsPanel);

                            //======== memoryPanel ========
                            {
                                memoryPanel.setBorder(new TitledBorder("Memory"));
                                memoryPanel.setLayout(new BoxLayout(memoryPanel, BoxLayout.Y_AXIS));

                                //---- radioButton1 ----
                                radioButton1.setText("16 K");
                                memoryPanel.add(radioButton1);

                                //---- radioButton2 ----
                                radioButton2.setText("32 K");
                                memoryPanel.add(radioButton2);

                                //---- radioButton3 ----
                                radioButton3.setText("64 K");
                                memoryPanel.add(radioButton3);

                                //---- radioButton4 ----
                                radioButton4.setText("128 K");
                                memoryPanel.add(radioButton4);

                                //---- radioButton5 ----
                                radioButton5.setText("512 K");
                                radioButton5.setSelected(true);
                                memoryPanel.add(radioButton5);
                            }
                            panel3.add(memoryPanel);
                        }
                        machinePanel.add(panel3);
                    }
                    tabbedPane1.addTab("Machine", machinePanel);

                    //======== displayPanel ========
                    {
                        displayPanel.setLayout(null);

                        //======== monitorTypePanel2 ========
                        {
                            monitorTypePanel2.setBorder(new TitledBorder("Monitor Type"));
                            monitorTypePanel2.setLayout(new BoxLayout(monitorTypePanel2, BoxLayout.Y_AXIS));

                            //---- radioButton13 ----
                            radioButton13.setText("RGB");
                            radioButton13.setSelected(true);
                            monitorTypePanel2.add(radioButton13);

                            //---- radioButton14 ----
                            radioButton14.setText("Composite");
                            monitorTypePanel2.add(radioButton14);

                            //---- checkBox1 ----
                            checkBox1.setText("Lock at 60Hz");
                            monitorTypePanel2.add(checkBox1);
                        }
                        displayPanel.add(monitorTypePanel2);
                        monitorTypePanel2.setBounds(0, 0, 166, 148);

                        { // compute preferred size
                            Dimension preferredSize = new Dimension();
                            for(int i = 0; i < displayPanel.getComponentCount(); i++) {
                                Rectangle bounds = displayPanel.getComponent(i).getBounds();
                                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                            }
                            Insets insets = displayPanel.getInsets();
                            preferredSize.width += insets.right;
                            preferredSize.height += insets.bottom;
                            displayPanel.setMinimumSize(preferredSize);
                            displayPanel.setPreferredSize(preferredSize);
                        }
                    }
                    tabbedPane1.addTab("Display", displayPanel);

                    //======== inputPanel ========
                    {
                        inputPanel.setLayout(new BorderLayout());
                    }
                    tabbedPane1.addTab("Input", inputPanel);
                }
                contentPanel.add(tabbedPane1, BorderLayout.CENTER);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        cancelButtonActionPerformed(e);
                    }
                });
                buttonBar.add(cancelButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        okButtonActionPerformed(e);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());

        //---- buttonGroup1 ----
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(radioButton8);
        buttonGroup1.add(radioButton9);
        buttonGroup1.add(radioButton10);

        //---- buttonGroup3 ----
        ButtonGroup buttonGroup3 = new ButtonGroup();
        buttonGroup3.add(radioButton6);
        buttonGroup3.add(radioButton7);

        //---- buttonGroup5 ----
        ButtonGroup buttonGroup5 = new ButtonGroup();
        buttonGroup5.add(radioButton11);
        buttonGroup5.add(radioButton12);

        //---- buttonGroup2 ----
        ButtonGroup buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(radioButton1);
        buttonGroup2.add(radioButton2);
        buttonGroup2.add(radioButton3);
        buttonGroup2.add(radioButton4);
        buttonGroup2.add(radioButton5);

        //---- buttonGroup4 ----
        ButtonGroup buttonGroup4 = new ButtonGroup();
        buttonGroup4.add(radioButton13);
        buttonGroup4.add(radioButton14);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JTabbedPane tabbedPane1;
    private JPanel machinePanel;
    private JPanel panel3;
    private JPanel machineTypePanel;
    private JRadioButton radioButton8;
    private JRadioButton radioButton9;
    private JRadioButton radioButton10;
    private JPanel specsPanel;
    private JPanel cpuPanel;
    private JRadioButton radioButton6;
    private JRadioButton radioButton7;
    private JPanel monitorTypePanel;
    private JRadioButton radioButton11;
    private JRadioButton radioButton12;
    private JPanel memoryPanel;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JPanel displayPanel;
    private JPanel monitorTypePanel2;
    private JRadioButton radioButton13;
    private JRadioButton radioButton14;
    private JCheckBox checkBox1;
    private JPanel inputPanel;
    private JPanel buttonBar;
    private JButton cancelButton;
    private JButton okButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

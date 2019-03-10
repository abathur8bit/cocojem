package com.axorion.coco;

import javax.sound.sampled.AudioFormat;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CocoJEM
{
    public static void main(String[] args) throws Exception
    {
        AppFrame instance = new AppFrame();
        instance.setSize(1024,768);
        instance.setLocationRelativeTo(null);
        instance.setVisible(true);
        instance.startCoco();
    }
}

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

import img.Img;
import sun.awt.image.ToolkitImage;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.util.HashMap;

public class CocoPanel extends JPanel implements Tickable {
    protected AppFrame parent;
    protected BufferedImage buffer;
    protected Image nuclear;
    protected FontMap cocoFont;
    AffineTransform identity;
    AffineTransform at;
    long timer;
    double frames;
    double fps=0;
    final long TIMER_DELAY = 1000;
    final long CURSOR_DELAY = 1000/10;
    long cursorTimer = 0;
    long repaintTimer;
    Color[] colors = {
            Color.green,
            Color.yellow,
            Color.blue,
            Color.red,
            Color.white,
            Color.cyan,
            Color.magenta,
            Color.orange,
    };
    String[] colorBlock = {"143","159","175","191","207","223","239","255"};
    int colorIndex=0;
    Image[] strip;
    int stripIndex=0;
    Color nuclearGreen = new Color(0,255,0);
    public int[] memory = new int[512*1024];
    BufferedImage doubleBuffer;
    Graphics2D g2buff;

    String[] keys = {
            //inverted
            "000","001","002","003","004","005","006","007","008","009","010","011","012","013","014","015","016","017","018","019","020","021","022","023","024","025","026","027","028","029","030","031",
            //regular
            " ","!","\"","#","$","%","&","'","(",")","*","+",",","-",".","/","0","1","2","3","4","5","6","7","8","9",":",";","<","=",">","?",
            "@","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","[","\\","]","^","<",
            //inverted/lower case
            "@@","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","[[","\\\\","]","^^","<<",
            //graphic blocks
            "128","129","130","131","132","133","134","135","136","137","138","139","140","141","142","143",    //green
            "144","145","146","147","148","149","150","151","152","153","154","155","156","157","158","159",    //red
            "160","161","162","163","164","165","166","167","168","169","170","171","172","173","174","175",    //light blue
            "176","177","178","179","180","181","182","183","184","185","186","187","188","189","190","191",    //grey
            "192","193","194","195","196","197","198","199","200","201","202","203","204","205","206","207",    //white
            "208","209","210","211","212","213","214","215","216","217","218","219","220","221","222","223",    //cyan
            "224","225","226","227","228","229","230","231","232","233","234","235","236","237","238","239",    //dark blue
            "240","241","242","243","244","245","246","247","248","249","250","251","252","253","254","255"     //orange
    };

    public CocoPanel(AppFrame parent) {
        Img.thing();
        this.parent = parent;
        identity = new AffineTransform();
        at = new AffineTransform();
        nuclear = parent.loadImage("CocoScreenshot.png");
        timer = System.currentTimeMillis()+TIMER_DELAY;
        repaintTimer = 0;
        frames = 0;
        cursorTimer = timer+CURSOR_DELAY;

        doubleBuffer = new BufferedImage(nuclear.getWidth(null),nuclear.getHeight(null),BufferedImage.TYPE_INT_RGB);
        g2buff = doubleBuffer.createGraphics();
        strip = parent.loadImageStrip("font32x16_grid32x48.png",32*8,32,48,0);
        stripIndex = 0;
        cocoFont = new FontMap(parent,keys,strip);
//        setSize(nuclear.getWidth(null)/2,nuclear.getHeight(null));
    }

    public void tick(long now) {
        if(now > timer) {
            fps = frames;
            frames = 0;
            timer = now + TIMER_DELAY;
        }
        if(now > cursorTimer) {
            cursorTimer = now + CURSOR_DELAY;
            colorIndex++;
            if(colorIndex > colorBlock.length-1) {
                colorIndex = 0;
            }
        }
        if(now > repaintTimer) {
            repaintTimer = now;  //60Hz
            repaint();
        }
    }

    public int getFps() {
        return (int)fps;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        g2buff.drawImage(nuclear,0,0,null);
        drawCursor(g2buff);

        g2.drawImage(doubleBuffer,0,0,getWidth(),getHeight(),null);

        frames++;
    }

    protected void drawCursor(Graphics2D g2) {
        cocoFont.drawChar(colorBlock[colorIndex],0,48*6,g2);
    }

    protected Dimension calculateSize(Image im) {
        final double aspect = (double)im.getHeight(this)/(double)im.getWidth(this);
        int width = getWidth();
        int height = (int)((double)width * aspect);
        if(height > getHeight()) {
            width = (int)((double)height/aspect);
        }
        Dimension size = new Dimension(width,height);
        return size;
    }
}

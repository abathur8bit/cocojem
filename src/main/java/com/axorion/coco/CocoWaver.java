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

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class CocoWaver extends JPanel implements Tickable {
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
    final long CURSOR_DELAY = 1000/30;
    long cursorTimer = 0;
    long repaintTimer;
    int offset[] = {1,2,3,4,6,8,10,12,14,16,20,22,24,26,27,28,28,28,28,28,27,26,24,22,20,18,16,14,12,10,9,8,7,6,5,4,3,2,1,0,0,0};
    int offsetIndex=0;
    int colors[] = {9,9,12,12,12,12,12,12,12,9,9,14,14,15,15,15,15,15,15,15,15,14,14,14,9,9,9,12,12,12,12,12,12,9,9};
    String[] colorBlock = {"143","159","175","191","207","223","239","255"};
    int colorIndex=0;
    Image[] strip;
    int stripIndex=0;
    Color nuclearGreen = new Color(0,255,0);
    public int[] memory = new int[512*1024];

//    String[] startup = "EXTENDED COLOR BASIC 2.0","COPR. 1982, 1986 BY TANDY","UNDER LICENSE FROM MICROSOFT","AND MICROWARE SYSTEMS CORP."};
    String startup = "HELLO WAVER";

    public CocoWaver(AppFrame parent) {
        for(int i=0; i<memory.length; i++) {
            memory[i] = 143;
        }
        for(int i=0; i<startup.length(); i++) {
            memory[0x400+i] = startup.charAt(i);
        }

        this.parent = parent;
//        identity = new AffineTransform();
//        at = new AffineTransform();
//        nuclear = parent.loadImage("CocoScreenshot.png");
        timer = System.currentTimeMillis()+TIMER_DELAY;
        repaintTimer = 0;
        frames = 0;
        cursorTimer = timer+CURSOR_DELAY;

//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        String []fontFamilies = ge.getAvailableFontFamilyNames();
//
//        for(String font : fontFamilies) {
//            System.out.println("Font="+font);
//        }

        strip = parent.loadImageStrip("font32x16_grid32x48.png",32*7,32,48,0);
        stripIndex = 0;
        String[] keys = {
                " ","!","\"","#","$","%","&","'","(",")","*","+",",","-",".","/","0","1","2","3","4","5","6","7","8","9",":",";","<","=",">","?",
                "@","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","[","\\","]","^","<",
                "@@","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","[[","\\\\","]","^^","<<",
                "128",
                "129",
                "130",
                "131",
                "132",
                "133",
                "134",
                "135",
                "136",
                "137",
                "138",
                "139",
                "140",
                "141",
                "142",
                "143",
                "144",
                "145",
                "146",
                "147",
                "148",
                "149",
                "150",
                "151",
                "152",
                "153",
                "154",
                "155",
                "156",
                "157",
                "158",
                "159",
                "160",
                "161",
                "162",
                "163",
                "164",
                "165",
                "166",
                "167",
                "168",
                "169",
                "170",
                "171",
                "172",
                "173",
                "174",
                "175",
                "176",
                "177",
                "178",
                "179",
                "180",
                "181",
                "182",
                "183",
                "184",
                "185",
                "186",
                "187",
                "188",
                "189",
                "190",
                "191",
                "192",
                "193",
                "194",
                "195",
                "196",
                "197",
                "198",
                "199",
                "200",
                "201",
                "202",
                "203",
                "204",
                "205",
                "206",
                "207",
                "208",
                "209",
                "210",
                "211",
                "212",
                "213",
                "214",
                "215",
                "216",
                "217",
                "218",
                "219",
                "220",
                "221",
                "222",
                "223",
                "224",
                "225",
                "226",
                "227",
                "228",
                "229",
                "230",
                "231",
                "232",
                "233",
                "234",
                "235",
                "236",
                "237",
                "238",
                "239",
                "240",
                "241",
                "242",
                "243",
                "244",
                "245",
                "246",
                "247",
                "248",
                "249",
                "250",
                "251",
                "252",
                "253",
                "254",
                "255"
        };
        cocoFont = new FontMap(parent,keys,strip);
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

            scrollDown();
            drawBar(offset[offsetIndex]);
            if(++offsetIndex >= offset.length) {
                offsetIndex=0;
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
        int x=0;
        int y=0;
        for(int i=0x400; i<0x600; i++) {
            cocoFont.drawChar(memory[i],x*32,y*48,g2);
            if(++x >= 32) {
                x=0;
                ++y;
            }
            if(y >= 16) {
                y = 0;
            }
        }
        frames++;
    }

    void drawBar(int offset)
    {
        int dest = 0x400;     //text video memory
        while(dest < 0x420)
        {
            int n = colors[offset] << 4;
            n += 0xF;
            memory[dest] = n;
            ++dest;
            if(++offset >= colors.length)
                offset = 0;
        }
    }

    void scrollDown()
    {
        int source = 0x5DF;
        int dest   = 0x5FF;
        do
        {
            memory[dest] = memory[source];
            --dest;
            --source;
        } while(source > 0x3FF);
    }
}

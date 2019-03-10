package com.axorion.coco;

import java.awt.*;
import java.util.HashMap;

public class FontMap {
    AppFrame parent;
    Image[] fontSheet;
    HashMap<String,Image> fontHashMap = new HashMap<String,Image>();
    HashMap<Integer,Image> fontIndexMap = new HashMap<Integer,Image>();
    Image defaultImage;

    public FontMap(AppFrame parent,String[] keys,Image[] images) {
        this.parent = parent;
        this.fontSheet = images;
        for(int i=0; i<keys.length; i++) {
            fontHashMap.put(keys[i],images[i]);
            fontIndexMap.put(i,images[i]);
            System.out.println("key["+keys[i]+"] = image["+images[i]+"]");
        }
        defaultImage = parent.createPlaceholder(images[0].getWidth(null),images[0].getHeight(null),Color.RED);
    }

    public void drawString(String s,int x,int y,Graphics2D g2) {
        for(int i=0; i<s.length(); i++) {
            String key = ""+s.charAt(i);
            Image im = fontHashMap.get(key);
            draw(im,x,y,g2);
            x += im.getWidth(parent);
        }
    }

    public void drawChar(String key,int x,int y,Graphics2D g2) {
        draw(fontHashMap.get(key),x,y,g2);
    }

    public void drawChar(int index,int x,int y,Graphics2D g2) {
        draw(fontIndexMap.get(index),x,y,g2);
    }

    public void draw(Image im,int x,int y,Graphics2D g2) {
        if(im == null) {
            im = defaultImage;
        }
        if(im != null) {
            g2.drawImage(im,x,y,parent);
        }
    }
}

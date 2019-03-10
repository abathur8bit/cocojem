/*
 * Created by JFormDesigner on Sat Feb 23 09:23:53 EST 2019
 */

package com.axorion.coco;

import com.antws.tools.RandomTool;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileFilter;

/**
 * @author Lee Patterson
 */
public class AppFrame extends JFrame implements InvocationHandler {
    MediaTracker mediaTracker;
    Registers registers;
    Memory memoryDialog;
    DebugWindow debugWindow;
    JFileChooser fileChooser;
    FileDialog dlg;
    boolean IS_MAC = false;

    public static final String HIDE_REGISTERS = "Hide Registers";
    public static final String SHOW_REGISTERS = "Show Registers";
    protected boolean isMac;
    protected CocoWaver cocoPanel;
    private boolean running;
    private long fpsDelay = 0;

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
    int colorIndex=0;

    public AppFrame() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super("Color Computer 3 (NTSC; HD6309)");
        String lcOSName = System.getProperty("os.name").toLowerCase();
        IS_MAC = lcOSName.startsWith("mac os x");
        System.out.println("os.name="+lcOSName+" ismac="+IS_MAC);
        if(IS_MAC) {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
        }
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        if(!isMac) {
            customizeNonMac();
        }
        setResizable(true);

        mediaTracker = new MediaTracker(this);

        recentMenu.add(new JMenuItem("Dungeons of Daggorath"));

        registers = new Registers(this);
        memoryDialog = new Memory(this);
        debugWindow = new DebugWindow(this);

//        cocoPanel = new CocoPanel(this);
        cocoPanel = new CocoWaver(this);
        getContentPane().add(cocoPanel,BorderLayout.CENTER);


        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new OpenFileFilter("jpeg","Photo in JPEG format") );
        fileChooser.addChoosableFileFilter(new FileFilter() {

            public String getDescription() {
                return "ROM Pak (*.rom)";
            }

            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".rom");
                }
            }
        });
        fileChooser.addChoosableFileFilter(new FileFilter() {

            public String getDescription() {
                return "Binary (*.bin)";
            }

            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".bin");
                }
            }
        });

        try {
            Class quitHandlerClass = Class.forName("com.apple.mrj.MRJQuitHandler");
            Class aboutHandlerClass = Class.forName("com.apple.mrj.MRJAboutHandler");
            Class prefHandlerClass = Class.forName("com.apple.mrj.MRJPrefsHandler");

            Class mrjapputilsClass = Class.forName("com.apple.mrj.MRJApplicationUtils");
            Object methodHandler = Proxy.newProxyInstance(quitHandlerClass.getClassLoader(),new Class[] {quitHandlerClass,aboutHandlerClass,prefHandlerClass},this);

            Method appUtilsObj = mrjapputilsClass.getMethod("registerQuitHandler",new Class[] {quitHandlerClass});
            appUtilsObj.invoke(null,new Object[] {methodHandler});

            appUtilsObj = mrjapputilsClass.getMethod("registerAboutHandler",new Class[] {aboutHandlerClass});
            appUtilsObj.invoke(null,new Object[] {methodHandler});

            appUtilsObj = mrjapputilsClass.getMethod("registerPrefsHandler",new Class[] {prefHandlerClass});
            appUtilsObj.invoke(null,new Object[] {methodHandler});

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void startCoco() {
        Thread loop = new Thread() {
            public void run() {
                gameLoop();
            }
        };
        running = true;
        loop.start();
    }

    private void gameLoop() {
        final Tickable coco = (Tickable)cocoPanel;
        while(running) {
            long now = System.currentTimeMillis();
            coco.tick(now);
            if(now > fpsDelay) {
                fpsDelay = now+1000;
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        statusMessage.setText("FPS="+coco.getFps());
                    }
                });
            }
        }
    }

    protected void customizeNonMac() {
        final JFrame parent = this;
        JMenuItem item = new JMenuItem("Options...");
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OptionsDialog dlg = new OptionsDialog(parent);
                dlg.setVisible(true);
            }
        });
        fileMenu.add(item);
    }

    /**
     * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object,
     *      java.lang.reflect.Method, java.lang.Object[])
     */
    public Object invoke(Object proxy, Method meth, Object[] args) throws Throwable {
        if (meth.getName().equals("handleQuit")) {
            System.exit(0);
//            int retval = JOptionPane.showConfirmDialog(this,"Are you sure you want to quit?","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
//            if(retval == JOptionPane.YES_OPTION) {
//            }
        } else if (meth.getName().equals("handleAbout")) {
            AboutDialog dlg = new AboutDialog(this);
            dlg.setVisible(true);
        } else if (meth.getName().equals("handlePrefs")) {
            JDialog dlg = new OptionsDialog(this);
            dlg.setVisible(true);
        }

        return null;
    }

    private void openMenuActionPerformed(ActionEvent e) {
        fileChooser.showOpenDialog(this);

//        if(isMac) {
//            if(dlg == null) {
//                dlg = new FileDialog(this,"Open",FileDialog.LOAD);
//            }
//            dlg.setVisible(true);
//        } else {
//            fileChooser.showOpenDialog(this);
//        }
    }

    private void clickMeButtonActionPerformed(ActionEvent e) {
        registersMenuActionPerformed(e);
    }

    private void registersMenuActionPerformed(ActionEvent e) {
        if(registers.isVisible()) {
            registersMenu.setText(SHOW_REGISTERS);
            registers.setVisible(false);
        } else {
            registersMenu.setText(HIDE_REGISTERS);
            registers.setLocation(this.getX()+getWidth(),this.getY());
            registers.setVisible(true);
        }
    }

    private void exitMenuActionPerformed(ActionEvent e) {
        // TODO add your code here
    }

    private void newActionPerformed(ActionEvent e) {
        try {
            AppFrame f = new AppFrame();
            f.setSize(getSize());
            f.setVisible(true);
        } catch(Exception ex) {
            //ignore
        }
    }

    private void memoryMenuActionPerformed(ActionEvent e) {
//        memoryDialog.setMinimumSize(new Dimension(600,100));
//        memoryDialog.setSize(1024,768*2);
        memoryDialog.setDump(cocoPanel.memory,0x400,0x600);
        memoryDialog.setVisible(true);

//        debugWindow.setSize(500,100);
//        debugWindow.setVisible(true);

    }

    public Image loadImage(String filename) {
        String target = "/"+filename;
        URL url = AppFrame.class.getResource(target);
        System.out.println("ImageUtil filename="+target+" url="+url);
        if(url == null) {
            return createPlaceholder(128,128,Color.RED);
        }
        Image im = Toolkit.getDefaultToolkit().getImage(url);
        mediaTracker.addImage(im,0);
        System.out.println("Waiting for "+filename+" to load");
        try {
            long start = System.currentTimeMillis();
            mediaTracker.waitForAll();
            long end = System.currentTimeMillis();
            System.out.println(filename+" loaded in "+(end-start)+"ms");
        } catch(InterruptedException e) {
            //ignore
        }
        return im;
    }

    public Image createPlaceholder(int width,int height,Color c) {
        BufferedImage bi;
        bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bi.createGraphics();
        g2.setPaint(colors[colorIndex++]);
        if(colorIndex >= colors.length) {
            colorIndex = 0;
        }
        g2.fillRect(0,0,width,height);
        return bi;
    }

    public Image[] loadImageStrip(String filename,int numImages,int cellWidth,int cellHeight,int cellBorder) {
        Image[] images = new Image[numImages];
        Image img = loadImage(filename);
        System.out.println("Image w="+img.getWidth(null)+" h="+img.getHeight(null));
        int numCols = img.getWidth(null) / cellWidth;
        ImageProducer sourceProducer = img.getSource();
            for(int i=0; i<numImages; i++) {
            images[i] = //createPlaceholder(cellWidth,cellHeight,new Color(i%255,10,10));
                    loadCell(
                    sourceProducer,
                    ((i%numCols)*cellWidth)+cellBorder,
                    ((i/numCols)*cellHeight)+cellBorder,
                    cellWidth-cellBorder,
                    cellHeight-cellBorder);
            mediaTracker.addImage(images[i],0);
        }
        try {
            mediaTracker.waitForAll();
        } catch(InterruptedException e) {
            //ignore
        }
        return images;
    }

    public Image loadCell(ImageProducer ip,int x,int y,int w,int h) {
        return createImage(new FilteredImageSource(ip,new CropImageFilter(x,y,w,h)));
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner non-commercial license
        menuBar1 = new JMenuBar();
        fileMenu = new JMenu();
        newMenuItem = new JMenuItem();
        menuItem2 = new JMenuItem();
        exitMenu = new JMenuItem();
        recentMenu = new JMenu();
        menuItem3 = new JMenuItem();
        menuItem5 = new JMenuItem();
        menu2 = new JMenu();
        registersMenu = new JMenuItem();
        memoryMenuItem = new JMenuItem();
        menu1 = new JMenu();
        menuItem1 = new JMenuItem();
        menuItem4 = new JMenuItem();
        panel1 = new JPanel();
        statusMessage = new JLabel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== menuBar1 ========
        {

            //======== fileMenu ========
            {
                fileMenu.setText("File");

                //---- newMenuItem ----
                newMenuItem.setText("New");
                newMenuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        newActionPerformed(e);
                    }
                });
                fileMenu.add(newMenuItem);

                //---- menuItem2 ----
                menuItem2.setText("Save...");
                fileMenu.add(menuItem2);

                //---- exitMenu ----
                exitMenu.setText("Open...");
                exitMenu.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        exitMenuActionPerformed(e);
                    }
                });
                fileMenu.add(exitMenu);

                //======== recentMenu ========
                {
                    recentMenu.setText("Open Recent");
                }
                fileMenu.add(recentMenu);

                //---- menuItem3 ----
                menuItem3.setText("Soft Reset");
                fileMenu.add(menuItem3);

                //---- menuItem5 ----
                menuItem5.setText("Hard Reset");
                fileMenu.add(menuItem5);
            }
            menuBar1.add(fileMenu);

            //======== menu2 ========
            {
                menu2.setText("View");

                //---- registersMenu ----
                registersMenu.setText("Show Registers");
                registersMenu.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        registersMenuActionPerformed(e);
                    }
                });
                menu2.add(registersMenu);

                //---- memoryMenuItem ----
                memoryMenuItem.setText("Memory Dump");
                memoryMenuItem.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        memoryMenuActionPerformed(e);
                    }
                });
                menu2.add(memoryMenuItem);
            }
            menuBar1.add(menu2);

            //======== menu1 ========
            {
                menu1.setText("Develop");

                //---- menuItem1 ----
                menuItem1.setText("Run");
                menu1.add(menuItem1);

                //---- menuItem4 ----
                menuItem4.setText("Debug");
                menu1.add(menuItem4);
            }
            menuBar1.add(menu1);
        }
        setJMenuBar(menuBar1);

        //======== panel1 ========
        {
            panel1.setBorder(new BevelBorder(BevelBorder.LOWERED));
            panel1.setLayout(new FlowLayout(FlowLayout.LEFT));

            //---- statusMessage ----
            statusMessage.setText("Ready");
            panel1.add(statusMessage);
        }
        contentPane.add(panel1, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner non-commercial license
    private JMenuBar menuBar1;
    private JMenu fileMenu;
    private JMenuItem newMenuItem;
    private JMenuItem menuItem2;
    private JMenuItem exitMenu;
    private JMenu recentMenu;
    private JMenuItem menuItem3;
    private JMenuItem menuItem5;
    private JMenu menu2;
    private JMenuItem registersMenu;
    private JMenuItem memoryMenuItem;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem4;
    private JPanel panel1;
    private JLabel statusMessage;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

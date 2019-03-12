https://centerkey.com/mac/java/

[Good stack overflow article](https://stackoverflow.com/questions/11037693/convert-java-application-to-mac-os-x-app)

[Making a macOS app](https://centerkey.com/mac/java/) looks pretty good, even shows how to make a pkg installer.

[Java Extensions for Apple][javaextensions]

[Java Deployment Options for OS X](https://developer.apple.com/library/archive/documentation/Java/Conceptual/Java14Development/03-JavaDeployment/JavaDeployment.html)

[Converting to windows](https://stackoverflow.com/questions/147181/how-can-i-convert-my-java-program-to-an-exe-file/147233#147233) has a large number of utils that will create an EXE. 

# Setting look and feel
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

# Detecting if running a mac
        String lcOSName = System.getProperty("os.name").toLowerCase();
        boolean IS_MAC = lcOSName.startsWith("mac os x");
        System.out.println("os.name="+lcOSName+" ismac="+IS_MAC);

# Moving Menu bar
        System.setProperty("apple.laf.useScreenMenuBar", "true");


# Package
## Make icon

```
cd images
makeicons.sh CocoJEM-logo01.png cj
cd ..
mkdir CocoJEM.iconset
cp images/cj-macOS_128.png CocoJEM.iconset/icon_128x128.png
iconutil --convert icns CocoJEM.iconset
ls -l
```

## Create a Mac app

./makeapp.sh

```
javapackager -deploy \
    -title "CocoJEM" \
    -name "CocoJEM" \
    -appclass com.axorion.coco.CocoJEM \
    -native image \
    -outdir dist \
    -outfile CocoJEM.app \
    -srcfiles target/8BitCoco-1.0-SNAPSHOT.jar 
```

echo "Main-Class: com.axorion.coco.CocoJEM" > MainClass.txt

Created pkg file correctly:

    javapackager -deploy -native pkg -name ShowTime \
       -BappVersion=1.0.0 -Bicon=package/macosx/ShowTime.icns \
       -srcdir . -srcfiles target/8BitCoco-1.0-SNAPSHOT.jar -appclass com.axorion.coco.CocoJEM \
       -outdir out -v -outfile CocoJemu
   
Tries to create a dmg, but gets an error "Developer ID Application"
   
    javapackager -deploy \
        -title "CocoJemu" \
        -name "CocoJemu" \
        -appclass com.axorion.coco.CocoJEM \
        -native dmg \
        -outdir dist \
        -outfile CocoJemu \
        -srcfiles target/8BitCoco-1.0-SNAPSHOT.jar

Produces the error

    No base JDK. Package will use system JRE.
    No base JDK. Package will use system JRE.
    Building DMG package for CocoJemu
    Did not find a key matching 'Developer ID Application: '
    Error: Bundler "DMG Installer" (dmg) failed to produce a bundle.


# Golden T Game Engine

What I used for my tank game. 
https://github.com/jasjisdo/gtge

http://www.goldenstudios.or.id

[javaextensions]: https://developer.apple.com/library/archive/samplecode/AppleJavaExtensions/Introduction/Intro.html


# MAME
Add -debug to show the debugger.
-window
-ui_active makes it so you can use TAB to bring up the UI in the emu.


# VMC10
Nice looking debugging options for the MC10
http://www.geocities.ws/emucompboy/
https://github.com/PhoenixInteractiveNL/emuDownloadCenter/wiki/Emulator-virtualmc10?fbclid=IwAR3ATbYOokcIZ2qT81NBlQpFg3DDPQbctl1Z_Nl6T_EiFVOWiq3GRzI7A2Y#screenshot

#Graphics
[Graphics](https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics.html) | (Graphics2D)(https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html)

# Maven
Installing jar locally.

    mvn install:install-file -Dfile=/Users/lee/workspace/java/leelib/dist/antws.jar -DgroupId=com.antws \
        -DartifactId=antws -Dversion=1.0 -Dpackaging=jar

https://maven.apache.org/plugins/maven-install-plugin/usage.html


# JUnit
https://junit.org/junit5/ | https://junit.org/junit5/docs/current/user-guide/

# Pros Cons
con: No unsigned type. Use Integer unsigned operations.
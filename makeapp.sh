#!/bin/bash
# $@ - all command line params
# $1 - first param
# $# - number of command line params

mvn package

cp -v target/CocoJEM-1.0-SNAPSHOT.jar ~/Public/CocoJEM.jar

javapackager -deploy \
    -title "CocoJEM" \
    -name "CocoJEM" \
    -appclass com.axorion.coco.CocoJEM \
    -native image \
    -Bicon=CocoJEM.icns \
    -outdir dist \
    -outfile CocoJEM.app \
    -srcfiles target/CocoJEM-1.0-SNAPSHOT.jar

#! /bin/sh

now=$(date +%y%m%d%H%M%S)
targetpath="Simulation/PS-Products/"
server="ftp://minka.dynv6.net/"
deployment="travis/Pandemic\ Simulation/"

echo targetpath: ${targetpath}
echo server: ${server}

echo --------------rebuild at ${now}
cd Simulation
mvn -B -q -DskipTests=true -Dmaven.javadoc.skip=true package
cd ../

echo --------------preparing package
cp ${targetpath}PS-Analysis-Product/target/PandemicAnalysis-jar-with-dependencies.jar ${deployment}/java/PandemicAnalysis.
cp ${targetpath}PS-Simulation-Product/target/PandemicSimulation-jar-with-dependencies.jar ${deployment}/java/PandemicSimulation.jar

echo --------------zipping
7z a travis/PandemicSimulation.zip ${deployment}*

echo --------------uploading to server
# only the first one with --ftp-create-dirs and -S since errors should occur here first and after creation the ftp-create-dirs flag isn't needed anymore
curl --ftp-create-dirs --ftp-ssl -k -S -T travis/PandemicSimulation.zip ${server}PandemicSimulation/${now}/ --user ${ftp_user}:${ftp_pw}
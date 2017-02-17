del /S data
mkdir data

java -Dcom.github.themetalone.pandemic.config="config/Config.xml" jar java/PandemicSimulation.jar
java -Dcom.github.themetalone.pandemic.analysis.folder="data/analysis/" -Dcom.github.themetalone.pandemic.analysis.prefix="population-" -Dcom.github.themetalone.pandemic.analysis.csv="data/gtk-hsstates.csv" -jar java/PandemicAnalysis.jar 
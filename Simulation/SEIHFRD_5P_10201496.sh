java -Dcom.github.themetalone.pandemic.config="resources/simulation-config/SEIHFRD_5P_10201496.xml" -Dorg.slf4j.simpleLogger.defaultLogLevel="debug" -Dorg.slf4j.simpleLogger.logFile="log.txt" -jar PS-Products/PS-Simulation-Product/target/PandemicSimulation-jar-with-dependencies.jar
java -Dcom.github.themetalone.pandemic.analysis.prefix="population-" -Dcom.github.themetalone.pandemic.analysis.folder="target/SEIHFRD_5P_10201496/pretty/" -Dcom.github.themetalone.pandemic.analysis.csv="target/SEIHFRD_5P_10201496/SEIHFRD_5P_10201496-hsstates.csv" -jar PS-Products/PS-Analysis-Product/target/PandemicAnalysis-jar-with-dependencies.jar
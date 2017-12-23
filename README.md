#Google HashCode Project, TeamB

## Summary
1. About the project
    1. The goal
2. The modules
    1. Benchmark
    2. Engine
    3. Visualizer
3. Coming soon
4. Libraries
5. Author

## 1 - About the project

### 1.1 - The goal
The goal of this project is to solve 2017's Google HashCode challenge, offering at least 7 strategies.
<br>
The HashCode challenge was about solving cache and datacenter allocation, to offer the shortest delay to the EndPoints when watching videos.

## 2 - The modules

### 2.1 - Benchmark
To start the Benchmark of the solution, simply use this command line :
```
cd teamB/benchmark
mvn clean install
java -jar target/benchmarks.jar -rf json
```
But beware :  don't plan to do anything else, it might take up to 100% of your  CPU capacity for a few minutes.

### 2.2 - Engine
To start the Engine, use these commands :
```
cd teamB/engine
mvn exec:java
  -Dexec.mainClass="fr.uca.unice.polytech.si3.ps5.year17.teamX.engine.Main" \
  -Dexec.args="NUM_ID_STRAT \
      full/path/to/source/file/data.in \
      full/path/to/result/file/data.out \
      full/path/to/result/file/score.out"
```
Note : NUM_ID_STRAT has to be replaces by the number of the strategy you want to use.
<br>
If none match your argument, the best one will be selected.

### 2.3 - Visualizer
To start using the Visualizer you need to use the following commands : 
```
cd teamB/visualiser
mvn exec:java \
  -Dexec.mainClass="fr.uca.unice.polytech.si3.ps5.year17.teamX.visualiser.Main" \
  -Dexec.args="full/path/to/source/file/data.in \
    full/path/to/result/file1/data.out;full/path/to/result/file2/data.out \
    full/path/to/result/file1/score.out;full/path/to/result/file2/score.out \
    full/path/to/benchmark/file/result.json\
    full/path/to/result/folder"
```
##  3 - Coming soon
Nothing, this project was a one week challenge and the client will not ask for maintenance nor for more features.
##  4 - Libraries used
* Java8 : JDK 1.8
* Maven : JUnit 5
* R language : R, shiny, shinydashboard, ggplot2

##  5 - Authors
Team Collet wa mou Mosseiru :
* Younes Abdennadher
* Hugo François
* Alexandre Bolot
* Camille Le Roux

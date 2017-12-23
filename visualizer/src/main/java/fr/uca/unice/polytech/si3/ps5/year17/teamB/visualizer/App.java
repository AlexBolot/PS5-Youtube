package fr.uca.unice.polytech.si3.ps5.year17.teamB.visualizer;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Parser;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Video;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.visualizer.parser.OutputParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.List;


/**
 * <pre>
 * ||====================================================================||
 * ||//$\\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\//$\\||
 * ||(100)==================| FEDERAL RESERVE NOTE |================(100)||
 * ||\\$//        ~         '------========--------'                \\$//||
 * ||<< /        /$\              // ____ \\                         \ >>||
 * ||>>|  12    //L\\            // ///..) \\         L38036133B   12 |<<||
 * ||<<|        \\ //           || <||  >\  ||                        |>>||
 * ||>>|         \$/            ||  $$ --/  ||        One Hundred     |<<||
 * ||<<|      L38036133B        *\\  |\_/  //* series                 |>>||
 * ||>>|  12                     *\\/___\_//*   1989                  |<<||
 * ||<<\      Treasurer     ______/Franklin\________     Secretary 12 />>||
 * ||//$\                 ~|UNITED STATES OF AMERICA|~               /$\\||
 * ||(100)===================  ONE HUNDRED DOLLARS =================(100)||
 * ||\\$//\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\\$//||
 * ||====================================================================||
 * </pre>
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class App {
    public static void main(String[] args) throws IOException {
        String[] dataPaths = args[1].split(";");
        String[] scorePaths = args[2].split(";");

        ArrayList8<ArrayList8<Cache>> listOfCaches = new ArrayList8<>();
        ArrayList8<Float> listOfScores = new ArrayList8<>();

        OutputParser parser = new OutputParser();

        for (String dataPath : dataPaths) {
            listOfCaches.add(parser.parseDataOutput(dataPath));
        }

        for (String scorePath : scorePaths) {
            listOfScores.addAll(parser.parseScore(scorePath));
        }

        StringBuilder values = new StringBuilder();

        for (Float listOfScore : listOfScores) {
            values.append(listOfScore).append(", ");
        }

        values.deleteCharAt(values.length() - 1);
        values.deleteCharAt(values.length() - 1);

        StringBuilder str = new StringBuilder().append("myVals <- c(")
                .append(values.toString())
                .append(")\n")
                .append("barplot(myVals,")
                .append("main = \"Scoring by strategy\",\n")
                .append("xlab = \"Strategies\",\n")
                .append("ylab = \"Scoring\",\n")
                .append("names.arg = c(")
                .append("\"FirstIn\",")
                .append("\"ProbaTegy\",")
                .append("\"Random\"")
                .append("),\n")
                .append("col = \"darkred\",\n")
                .append("horiz = FALSE,\n")
                .append("cex.names = 0.75,\n")
                .append("ylim = c(0,680000))");

        File file = new File(args[args.length - 1] + "/graphScoring.r");
        if (!file.exists()) file.createNewFile();

        PrintStream printStream = new PrintStream(file.getPath());
        printStream.print(str);

        StringBuilder values2 = new StringBuilder();

        ArrayList8<Float> floatList = new ArrayList8<>();

        for (ArrayList8<Cache> cas : listOfCaches) {
            DataBundle dataBundle = new Parser(args[0]).getData();

            floatList.add(getPercentOfCovering(dataBundle, cas));
        }
        for (Float listOfScore : floatList) {
            values2.append(listOfScore).append(", ");
        }

        values2.deleteCharAt(values2.length() - 1);
        values2.deleteCharAt(values2.length() - 1);

        StringBuilder str2 = new StringBuilder().append("myVals <- c(")
                .append(values2.toString())
                .append(")\n")
                .append("barplot(myVals,")
                .append("main = \"Coverage by strategy\",\n")
                .append("xlab = \"Strategies\",\n")
                .append("ylab = \"Coverage\",\n")
                .append("names.arg = c(")
                .append("\"FirstIn\",")
                .append("\"ProbaTegy\",")
                .append("\"Random\"")
                .append("),\n")
                .append("col = \"deeppink\",\n")
                .append("horiz = FALSE,\n")
                .append("cex.names = 0.75,\n")
                .append("ylim = c(0,100))");

        File file2 = new File(args[args.length - 1] + "/graphCoverage.r");
        if (!file2.exists()) file2.createNewFile();

        PrintStream printStream2 = new PrintStream(file2.getPath());
        printStream2.print(str2);

        JSONParser jsonParser = new JSONParser();

        Object obj = null;

        try {
            obj = jsonParser.parse(new FileReader(args[3]));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        HashMap<String, Double> benchmarkData = new HashMap<>();

        JSONArray jsonArray = (JSONArray) obj;

        List<String> methods = new ArrayList8<>();
        List<String> scores = new ArrayList8<>();

        jsonArray.forEach(o -> {
            JSONObject jsonObject = (JSONObject) o;

            methods.add(((String) jsonObject.get("benchmark")).split("fr.uca.unice.polytech.si3.ps5.year17.teamB.benchmark.MyBenchmark.measure")[1]);

            scores.add(((Double) ((JSONObject) jsonObject.get("primaryMetric")).get("score") * Math.pow(10, -3)) + "");
        });

        System.out.println(methods);
        System.out.println(scores);

        StringBuilder value_method = new StringBuilder();
        StringBuilder value_score = new StringBuilder();

        for (int i = 0; i < methods.size(); i++) {
            value_method.append("\"").append(methods.get(i)).append("\" ,");
            value_score.append(scores.get(i)).append(" ,");
        }


        value_method.deleteCharAt(value_method.length() - 1);
        value_score.deleteCharAt(value_score.length() - 1);

        StringBuilder jsonBuilder = new StringBuilder().append("myVals <- c(")
                .append(value_score.toString())
                .append(")\n")
                .append("barplot(myVals,")
                .append("main = \"Execution time in second of benchmark method\",\n")
                .append("xlab = \"Method\",\n")
                .append("ylab = \"Time\",\n")
                .append("names.arg = c(")
                .append(value_method.toString())
                .append("),\n")
                .append("col = \"deeppink\",\n")
                .append("horiz = FALSE,\n")
                .append("cex.names = 0.75,\n")
                .append("ylim = c(0,500))");

        File benchmark = new File(args[4] + "/graphBenchmark.r");

        if (!benchmark.exists()) {
            try {
                benchmark.createNewFile();
                PrintStream printStream3 = new PrintStream(benchmark.getPath());
                printStream3.print(jsonBuilder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < methods.size(); i++) {
            benchmarkData.put(methods.get(i), Double.parseDouble(scores.get(i)));
        }

        List<List<Integer>> videoPerCache = new ArrayList8<>();

        for (List<Cache> cacheList : listOfCaches) {
            List<Integer> list = new ArrayList8<>();
            for (Cache cache : cacheList) {
                list.add(cache.getVideos().size());
            }
            videoPerCache.add(list);
        }

        RFileCreator creator = new RFileCreator(dataPaths.length, listOfScores, benchmarkData, videoPerCache);

        File visu = new File(args[4] + "/visu.r");

        if (!visu.exists()) {
            try {
                visu.createNewFile();
                PrintStream printStream4 = new PrintStream(visu.getPath());
                printStream4.print(creator.getRScript());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static float getPercentOfCovering(DataBundle dataBundle, ArrayList8<Cache> caches) {
        double totalPercent = 0;

        for (Cache realCache : dataBundle.getCaches()) {
            for (Cache simuCache : caches) {
                if (realCache.getId() != simuCache.getId()) continue;

                double initialSize = realCache.getSize();

                for (Video simuVideo : simuCache.getVideos()) {
                    Video video = dataBundle.getVideos().subList(video1 -> video1.getId() == simuVideo.getId()).get(0);
                    realCache.addVideo(video);
                }

                double finalSize = realCache.getSize();

                totalPercent += (initialSize - finalSize) * 100 / initialSize;
            }
        }

        int size = dataBundle.getCaches().size();

        return (float) (totalPercent / size);
    }
}
package fr.uca.unice.polytech.si3.ps5.year17.teamB.visualizer;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Parser;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Video;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.visualizer.parser.OutputParser;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;


/**
 <pre>
 ||====================================================================||
 ||//$\\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\//$\\||
 ||(100)==================| FEDERAL RESERVE NOTE |================(100)||
 ||\\$//        ~         '------========--------'                \\$//||
 ||<< /        /$\              // ____ \\                         \ >>||
 ||>>|  12    //L\\            // ///..) \\         L38036133B   12 |<<||
 ||<<|        \\ //           || <||  >\  ||                        |>>||
 ||>>|         \$/            ||  $$ --/  ||        One Hundred     |<<||
 ||<<|      L38036133B        *\\  |\_/  //* series                 |>>||
 ||>>|  12                     *\\/___\_//*   1989                  |<<||
 ||<<\      Treasurer     ______/Franklin\________     Secretary 12 />>||
 ||//$\                 ~|UNITED STATES OF AMERICA|~               /$\\||
 ||(100)===================  ONE HUNDRED DOLLARS =================(100)||
 ||\\$//\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\\$//||
 ||====================================================================||
 </pre>
 */
@SuppressWarnings ("ResultOfMethodCallIgnored")
public class App
{
    public static void main (String[] args) throws IOException
    {
        DataBundle dataBundle = new Parser(args[0]).getData();

        String[] dataPaths = args[1].split(";");
        String[] scorePaths = args[2].split(";");

        ArrayList8<ArrayList8<Cache>> listOfCaches = new ArrayList8<>();
        ArrayList8<Float> listOfScores = new ArrayList8<>();

        OutputParser parser = new OutputParser();

        for (String dataPath : dataPaths)
        {
            listOfCaches.add(parser.parseDataOutput(dataPath));
        }

        for (String scorePath : scorePaths)
        {
            listOfScores.addAll(parser.parseScore(scorePath));
        }

        StringBuilder values = new StringBuilder();

        for (Float listOfScore : listOfScores)
        {
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
                                               .append("\"Average\",")
                                               .append("\"CacheIfQuery\",")
                                               .append("\"FirstIn\",")
                                               .append("\"LightestsInCache\",")
                                               .append("\"ProbaTegy\",")
                                               .append("\"Random\"")
                                               .append("),\n")
                                               .append("col = \"darkred\",\n")
                                               .append("horiz = FALSE,\n")
                                               .append("cex.names = 0.75,\n")
                                               .append("ylim = c(0,250000))");

        File file = new File(args[args.length - 1] + "/graphScoring.r");
        if (!file.exists()) file.createNewFile();

        PrintStream printStream = new PrintStream(file.getPath());
        printStream.print(str);

        StringBuilder values2 = new StringBuilder();

        ArrayList8<Float> floatList = new ArrayList8<>();

        for (ArrayList8<Cache> cas : listOfCaches) {
            floatList.add(getPercentOfCovering(dataBundle,cas ));
        }
        for (Float listOfScore :floatList ) {
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
                .append("\"Average\",")
                .append("\"CacheIfQuery\",")
                .append("\"FirstIn\",")
                .append("\"LightestsInCache\",")
                .append("\"ProbaTegy\",")
                .append("\"Random\"")
                .append("),\n")
                .append("col = \"deeppink\",\n")
                .append("horiz = FALSE,\n")
                .append("cex.names = 0.75,\n")
                .append("ylim = c(0,250000))");

        File file2 = new File(args[args.length - 1] + "/graphCoverage.r");
        if (!file2.exists()) file2.createNewFile();

        PrintStream printStream2 = new PrintStream(file2.getPath());
        printStream2.print(str2);
    }

    private static float getPercentOfCovering (DataBundle dataBundle, ArrayList8<Cache> caches)
    {
        double totalPercent = 0;

        for (Cache realCache : dataBundle.getCaches())
        {
            for (Cache simuCache : caches)
            {
                if (realCache.getId() != simuCache.getId()) continue;

                double initialSize = realCache.getSize();

                for (Video simuVideo : simuCache.getVideos()) {
                    Video video = dataBundle.getVideos().subList(video1 -> video1.getId() == simuVideo.getId()).get(0);
                    realCache.addVideo(video);
                }

                double finalSize = realCache.getSize();

                totalPercent += (initialSize - finalSize) * 100 / finalSize;
            }
        }

        return (float) (totalPercent / dataBundle.getCaches().size());
    }
}
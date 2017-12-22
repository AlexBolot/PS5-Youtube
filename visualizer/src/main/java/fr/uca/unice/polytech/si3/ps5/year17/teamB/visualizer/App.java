package fr.uca.unice.polytech.si3.ps5.year17.teamB.visualizer;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Parser;
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

        File file = new File(args[args.length - 1] + "/graphN.r");
        if (!file.exists()) file.createNewFile();

        PrintStream printStream = new PrintStream(file.getPath());
        printStream.print(str);
    }

    private static float getPercentOfCovering (DataBundle dataBundle, ArrayList8<Cache> caches)
    {
        float totalPercent = 0;

        for (Cache realCache : dataBundle.getCaches())
        {
            for (Cache simuCache : caches)
            {
                if (realCache.getId() != simuCache.getId()) continue;

                float initialSize = realCache.getSize();

                simuCache.getVideos().forEach(realCache::addVideo);

                float finalSize = realCache.getSize();

                totalPercent += (finalSize - initialSize) * 100 / finalSize;
            }
        }

        return totalPercent / dataBundle.getCaches().size();
    }
}
package fr.uca.unice.polytech.si3.ps5.year17.teamB.visualizer;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Parser;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Video;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 Hello world!
 */
@SuppressWarnings ("ResultOfMethodCallIgnored")
public class App
{
    public static void main (String[] args) throws IOException
    {
        List<String> strings = Arrays.asList(args[0].split(" "));

        strings.replaceAll(String::trim);

        DataBundle dataBundle = new Parser(strings.get(0)).getData();

        String[] dataPaths = strings.get(1).split(";");
        String[] scorePaths = strings.get(2).split(";");

        ArrayList8<ArrayList8<Cache>> listOfListOfCaches = new ArrayList8<>();
        ArrayList8<Float> listOfScores = new ArrayList8<>();

        for (String dataPath : dataPaths)
        {
            listOfListOfCaches.addAll(parseData(dataPath));
        }

        for (String scorePath : scorePaths)
        {
            listOfScores.addAll(parseScore(scorePath));
        }

        StringBuilder values = new StringBuilder();

        for (int i = 0; i < listOfScores.size(); i++)
        {
            Float score = listOfScores.get(i);
            ArrayList8<Cache> caches = listOfListOfCaches.get(i);

            float percent = getPercentOfCovering(dataBundle, caches);

            values.append(score).append(", ");
            //values.append(percent).append(", ");
        }

        values.deleteCharAt(values.length() - 1);
        values.deleteCharAt(values.length() - 1);

        Float maxScore = listOfScores.max(Float::compare).get();
        Float minScore = listOfScores.min((o1, o2) -> (o1 == 0 || o2 == 0) ? -Float.compare(o1, o2) : Float.compare(o1, o2)).get();

        StringBuilder str = new StringBuilder();

        str.append("myVals = c(").append(values.toString()).append(")\n");

        str.append(
                "mydata <- matrix(myVals ,ncol = 1, byrow = TRUE, dimnames = list(c(\"Strat1\", \"Strat2\", \"Strat3\", \"Strat4\", \"Strat5\"), \"Reading/Writing\"))\n");

        str.append("colors <- c(\"darkblue\", \"red\")\n");

        str.append("barplot(t(mydata), beside = TRUE, col = colors, ylim = c(").append(minScore * .85).append(", ").append(maxScore).append(
                "), axes = FALSE, xlab = \"Strategies\", main = \"Scores and percent of coverage\")\n");

        str.append("axis(2, at =").append(minScore * .85).append(":").append(maxScore).append(")\n");

        str.append("legend(\"topright\", colnames(mydata), fill = colors, bty = \"n\")\n");


        File file = new File(strings.get(strings.size() - 1) + "/graphN.r");
        if (!file.exists()) file.createNewFile();

        PrintStream printStream = new PrintStream(file.getPath());
        printStream.print(str);


/*

"full/path/to/source/file/data.in full/path/to/result/file1/data.out;full/path/to/result/file2/data.out full/path/to/result/file1/score.out;full/path/to/result/file2/score.out full/path/to/benchmark/file1/result.json;full/path/to/benchmark/file2/result.json /Users/alexandre/Desktop/results"

/Users/alexandre/Desktop/me_at_the_zoo.in/data.out /Users/alexandre/Desktop/me_at_the_zoo.in/score.out /Users/alexandre/Desktop/results/

*/
    }

    private static ArrayList8<ArrayList8<Cache>> parseData (String path)
    {
        ArrayList8<ArrayList8<Cache>> listOfListOfCache = new ArrayList8<>();

        try (BufferedReader in = new BufferedReader(new FileReader(path)))
        {
            String line;

            while ((line = in.readLine()) != null)
            {
                if (line.split(" ").length == 1)
                {
                    int cacheAmount = tryParseInt(line);

                    if (cacheAmount == 0) continue;

                    ArrayList8<Cache> caches = new ArrayList8<>();

                    for (int i = 0; i < cacheAmount; i++)
                    {
                        String[] strings = in.readLine().split(" ");

                        ArrayList8<Video> videos = new ArrayList8<>();

                        for (int j = 1; j < strings.length - 1; j++)
                        {
                            int videoID = tryParseInt(strings[j]);
                            videos.add(new Video(videoID, 0));
                        }

                        int cacheID = tryParseInt(strings[0]);
                        caches.add(new Cache(cacheID, Integer.MAX_VALUE, videos));
                    }

                    //throw away blank line
                    //spearating 2 sets of data
                    in.readLine();

                    listOfListOfCache.add(caches);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return listOfListOfCache;
    }

    private static ArrayList8<Float> parseScore (String path)
    {
        ArrayList8<Float> floats = new ArrayList8<>();

        try (BufferedReader in = new BufferedReader(new FileReader(path)))
        {
            String[] strScores = in.readLine().split(" ");

            floats = Arrays.stream(strScores).map(App::tryParseFloat).collect(Collectors.toCollection(ArrayList8::new));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        floats.removeIf(aFloat -> aFloat == 0);

        return floats;
    }

    /**
     <hr>
     <h2>Tries to parse [string] into a Float</h2>
     <h3>If not possible —>  IllegalArgumentException.</h3>
     <br>
     <hr>

     @param string String to parse
     @return The Float value of [string] if parsable.<br>(if not, IAE has already been thrown)
     */
    private static float tryParseFloat (String string)
    {
        Objects.requireNonNull(string, "String param is null");

        if (string.isEmpty()) throw new IllegalArgumentException("String param is empty");

        try
        {
            return Float.parseFloat(string);
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException(string + " can't be parsed into a float");
        }
    }

    /**
     <hr>
     <h2>Tries to parse [string] into an Integer</h2>
     <h3>If not possible —>  IllegalArgumentException.</h3>
     <br>
     <hr>

     @param string String to parse
     @return The Integer value of [string] if parsable.<br>(if not, IAE has already been thrown)
     */
    private static int tryParseInt (String string)
    {
        Objects.requireNonNull(string, "String param is null");

        if (string.isEmpty()) throw new IllegalArgumentException("String param is empty");

        try
        {
            return Integer.parseInt(string);
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException(string + " can't be parsed into an Integer");
        }
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
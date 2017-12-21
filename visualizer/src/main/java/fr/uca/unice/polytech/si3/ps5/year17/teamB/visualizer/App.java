package fr.uca.unice.polytech.si3.ps5.year17.teamB.visualizer;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.DataBundle;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Parser;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Video;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class App {
    public static void main(String[] args) throws IOException {
        DataBundle dataBundle = new Parser(args[0]).getData();

        String[] dataPaths = args[1].split(";");
        String[] scorePaths = args[2].split(";");

        ArrayList8<ArrayList8<Cache>> listOfCaches = new ArrayList8<>();
        ArrayList8<Float> listOfScores = new ArrayList8<>();

        for (String dataPath : dataPaths) {
            listOfCaches.add(parseData(dataPath));
        }

        for (String scorePath : scorePaths) {
            listOfScores.addAll(parseScore(scorePath));
        }

        StringBuilder values = new StringBuilder();

        for (int i = 0; i < listOfScores.size(); i++) {
            values.append(listOfScores.get(i)).append(", ");
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

    private static ArrayList8<Cache> parseData(String path) {
        ArrayList8<Cache> caches = new ArrayList8<>();

        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String line = in.readLine();

            int cacheAmount = tryParseInt(line);

            for (int i = 0; i < cacheAmount; i++) {
                String[] strings = in.readLine().split(" ");

                ArrayList8<Video> videos = new ArrayList8<>();

                for (int j = 1; j < strings.length - 1; j++) {
                    int videoID = tryParseInt(strings[j]);
                    videos.add(new Video(videoID, 0));
                }

                int cacheID = tryParseInt(strings[0]);
                caches.add(new Cache(cacheID, Integer.MAX_VALUE, videos));
            }

            return caches;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return caches;
    }

    private static ArrayList8<Float> parseScore(String path) {
        ArrayList8<Float> floats = new ArrayList8<>();

        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String[] strScores = in.readLine().split(" ");

            floats = Arrays.stream(strScores).map(App::tryParseFloat).collect(Collectors.toCollection(ArrayList8::new));
        } catch (IOException e) {
            e.printStackTrace();
        }

        floats.removeIf(aFloat -> aFloat == 0);

        return floats;
    }

    /**
     * <hr>
     * <h2>Tries to parse [string] into a Float</h2>
     * <h3>If not possible —>  IllegalArgumentException.</h3>
     * <br>
     * <hr>
     *
     * @param string String to parse
     * @return The Float value of [string] if parsable.<br>(if not, IAE has already been thrown)
     */
    private static float tryParseFloat(String string) {
        Objects.requireNonNull(string, "String param is null");

        if (string.isEmpty()) throw new IllegalArgumentException("String param is empty");

        try {
            return Float.parseFloat(string);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(string + " can't be parsed into a float");
        }
    }

    /**
     * <hr>
     * <h2>Tries to parse [string] into an Integer</h2>
     * <h3>If not possible —>  IllegalArgumentException.</h3>
     * <br>
     * <hr>
     *
     * @param string String to parse
     * @return The Integer value of [string] if parsable.<br>(if not, IAE has already been thrown)
     */
    private static int tryParseInt(String string) {
        Objects.requireNonNull(string, "String param is null");

        if (string.isEmpty()) throw new IllegalArgumentException("String param is empty");

        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(string + " can't be parsed into an Integer");
        }
    }

    private static float getPercentOfCovering(DataBundle dataBundle, ArrayList8<Cache> caches) {
        float totalPercent = 0;

        for (Cache realCache : dataBundle.getCaches()) {
            for (Cache simuCache : caches) {
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
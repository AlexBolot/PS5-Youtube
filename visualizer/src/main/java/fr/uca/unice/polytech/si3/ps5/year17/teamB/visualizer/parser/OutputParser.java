package fr.uca.unice.polytech.si3.ps5.year17.teamB.visualizer.parser;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Cache;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.Video;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.visualizer.App;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class OutputParser {

    public ArrayList8<Cache> parseDataOutput(String path) {
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


    public ArrayList8<Float> parseScore(String path) {
        ArrayList8<Float> floats = new ArrayList8<>();

        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String[] strScores = in.readLine().split(" ");

            floats = Arrays.stream(strScores).map(this::tryParseFloat).collect(Collectors.toCollection(ArrayList8::new));
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
    public float tryParseFloat(String string) {
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
    public int tryParseInt(String string) {
        Objects.requireNonNull(string, "String param is null");

        if (string.isEmpty()) throw new IllegalArgumentException("String param is empty");

        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(string + " can't be parsed into an Integer");
        }
    }
}

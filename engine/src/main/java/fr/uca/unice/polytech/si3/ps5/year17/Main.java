package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.strategies.*;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


/**
 <pre>
 ______
 / ____|      _ _      _                                                    _     _                    _
 | |     ___ | | | ___| |_    __      ____ _     _ __ ___   ___  _   _     | \  / | ___  ___ ___  ___ (_) _ __ _   _
 | |    / _ \| | |/ _ \ __|   \ \ /\ / / _` |   | '_ ` _ \ / _ \| | | |    | |\/| |/ _ \/ __/ __|/ _ \| || '__| | | |
 | |___| (_) | | |  __/ |_     \ V  V / (_| |   | | | | | | (_) | |_| |    | |  | | (_) \__ \__ \  __/| || |  | |_| |
 \_____\____/|_|_|\___|\__|     \_/\_/ \__,_|   |_| |_| |_|\___/ \__,_|    |_|  |_|\___/|___/___/\___||_||_|   \__,_|

 __   _          _   _ _____   ___ ___
 | \ | |   /\   | \ | |_   _| |__ \__ \
 |  \| |  /  \  |  \| | | |      ) | ) |
 | . ` | / /\ \ | . ` | | |     / / / /
 | |\  |/ ____ \| |\  |_| |_   |_| |_|
 |_| \_/_/    \_\_| \_|_____|  (_) (_)

 </pre>

 @author Collet wa Mou Mosseiru
 @version 1.0 */
public class Main
{
    public static void main (String args[])
    {
        Parser parser = new Parser();

        try
        {
            String path = Main.class.getResource("/me_at_the_zoo.in").getPath();
            parser.parse(path);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        final Controller controller = new Controller(parser.getConnexions(),
                                                     parser.getCaches(),
                                                     parser.getEndpoints(),
                                                     parser.getVideos(),
                                                     parser.getDataCenter());

        StringBuilder scores = new StringBuilder();
        StringBuilder output = new StringBuilder();

        ArrayList8<Strategy> strategies = new ArrayList8<Strategy>()
        {{
            add(new ProbaTegy(new Controller(controller)));
            add(new FirstInStrategy(new Controller(controller)));
            add(new CacheIfQueryStrategy(new Controller(controller)));
            add(new AllInDataCenterStrategy(new Controller(controller)));
        }};

        for (Strategy strategy : strategies)
        {
            String stratName = strategy.getClass().getSimpleName();

            System.out.println("Stratégie : " + stratName);

            strategy.apply();

            System.out.println(controller.scoring());
            scores.append(controller.scoring()).append("\n\n");

            int cacheUsed = controller.getCaches().countIf(c -> !c.getVideos().isEmpty());

            StringBuilder str = new StringBuilder();
            str.append(cacheUsed).append('\n');

            for (int i = 0; i < cacheUsed; i++)
            {
                ArrayList8<Video> videos = controller.getCaches().get(i).getVideos();

                if (!videos.isEmpty()) str.append(i);

                videos.forEach(v -> str.append(' ').append(v.getId()));

                if (!videos.isEmpty()) str.append('\n');
            }

            output.append(str.toString()).append("\n\n");
        }

        try (PrintWriter writer = new PrintWriter(args[0] + "/data.out", "UTF-8"))
        {
            writer.write(output.toString());
        }
        catch (FileNotFoundException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter(args[0] + "/score.out", "UTF-8"))
        {
            writer.write(scores.toString());
        }
        catch (FileNotFoundException | UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }

/*
        Controller c2 = new Controller(parser.getConnexions(),
                                       parser.getCaches(),
                                       parser.getEndpoints(),
                                       parser.getVideos(),
                                       parser.getDataCenter());

        Strategy strategy2 = new ProbaTegy(c2.getConnexions(), c2.getCaches(), c2.getEndPoints(), c2.getVideos());

        System.out.println("Strategy Three : Probability algorithm");
        strategy2.apply();

        System.out.println(c2.scoring());

        c2.generateOutput(args[0], "");*/
    }

}

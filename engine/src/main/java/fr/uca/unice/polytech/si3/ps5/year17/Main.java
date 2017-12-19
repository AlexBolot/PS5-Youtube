package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.strategies.AllInDataCenterStrategy;
import fr.uca.unice.polytech.si3.ps5.year17.strategies.FirstInStrategy;
import fr.uca.unice.polytech.si3.ps5.year17.strategies.ProbaTegy;
import fr.uca.unice.polytech.si3.ps5.year17.strategies.Strategy;

import java.io.IOException;


/**
 <pre>
 _____     _ _      _                                                    __  __                    _
 / ____|    | | |    | |                                                  |  \/  |                  (_)
 | |     ___ | | | ___| |_    __      ____ _     _ __ ___   ___  _   _     | \  / | ___  ___ ___  ___ _ _ __ _   _
 | |    / _ \| | |/ _ \ __|   \ \ /\ / / _` |   | '_ ` _ \ / _ \| | | |    | |\/| |/ _ \/ __/ __|/ _ \ | '__| | | |
 | |___| (_) | | |  __/ |_     \ V  V / (_| |   | | | | | | (_) | |_| |    | |  | | (_) \__ \__ \  __/ | |  | |_| |
 \_____\___/|_|_|\___|\__|     \_/\_/ \__,_|   |_| |_| |_|\___/ \__,_|    |_|  |_|\___/|___/___/\___|_|_|   \__,_|

 </pre>

 <pre>
 _   _          _   _ _____   ___ ___
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

        Controller controller = new Controller(parser.getConnexions(),
                                               parser.getCaches(),
                                               parser.getEndpoints(),
                                               parser.getVideos(),
                                               parser.getDataCenter());

        Strategy strategy = new AllInDataCenterStrategy(controller.getConnexions(), controller.getCaches(), controller.getEndPoints());

        System.out.println("Strategy One : All in DataCenter");
        strategy.apply();

        System.out.println(controller.scoring());

        controller = new Controller(parser.getConnexions(),
                                    parser.getCaches(),
                                    parser.getEndpoints(),
                                    parser.getVideos(),
                                    parser.getDataCenter());

        strategy = new FirstInStrategy(controller.getConnexions(),
                                       controller.getCaches(),
                                       controller.getEndPoints(),
                                       controller.getVideos());

        System.out.println("Strategy Two : Greedy algorithm");
        strategy.apply();

        System.out.println(controller.scoring());

        controller.generateOutput(args[0], "");

        Controller c2 = new Controller(parser.getConnexions(),
                                       parser.getCaches(),
                                       parser.getEndpoints(),
                                       parser.getVideos(),
                                       parser.getDataCenter());

        Strategy strategy2 = new ProbaTegy(c2.getConnexions(), c2.getCaches(), c2.getEndPoints(), c2.getVideos());

        System.out.println("Strategy Three : Probability algorithm");
        strategy2.apply();

        System.out.println(c2.scoring());

        c2.generateOutput(args[0], "");
    }

}

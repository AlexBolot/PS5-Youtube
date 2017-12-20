package fr.uca.unice.polytech.si3.ps5.year17;

import fr.uca.unice.polytech.si3.ps5.year17.strategies.*;
import fr.uca.unice.polytech.si3.ps5.year17.utils.ArrayList8;
import fr.uca.unice.polytech.si3.ps5.year17.strategies.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


/**
 * <pre>
 * ______
 * / ____|      _ _      _                                                    _     _                    _
 * | |     ___ | | | ___| |_    __      ____ _     _ __ ___   ___  _   _     | \  / | ___  ___ ___  ___ (_) _ __ _   _
 * | |    / _ \| | |/ _ \ __|   \ \ /\ / / _` |   | '_ ` _ \ / _ \| | | |    | |\/| |/ _ \/ __/ __|/ _ \| || '__| | | |
 * | |___| (_) | | |  __/ |_     \ V  V / (_| |   | | | | | | (_) | |_| |    | |  | | (_) \__ \__ \  __/| || |  | |_| |
 * \_____\____/|_|_|\___|\__|     \_/\_/ \__,_|   |_| |_| |_|\___/ \__,_|    |_|  |_|\___/|___/___/\___||_||_|   \__,_|
 *
 * __   _          _   _ _____   ___ ___
 * | \ | |   /\   | \ | |_   _| |__ \__ \
 * |  \| |  /  \  |  \| | | |      ) | ) |
 * | . ` | / /\ \ | . ` | | |     / / / /
 * | |\  |/ ____ \| |\  |_| |_   |_| |_|
 * |_| \_/_/    \_\_| \_|_____|  (_) (_)
 *
 * </pre>
 *
 * @author Collet wa Mou Mosseiru
 * @version 1.0
 */
public class Main {

    public static void main(String args[]) {

        if (args.length != 4) {
            System.err.println("Wrong number of argument, expected : <strat_id> <path_to_input_file> <path_to_data.out> <path_to_score.out>");
            System.exit(1);
        }

        Parser parser = new Parser();

        String path = args[1];
        parser.parse(path);

        Strategy strategy;

        switch (Integer.parseInt(args[0])) {
            case 0:
                strategy = new AllInDataCenterStrategy(parser.getData());
                break;
            case 1:
                strategy = new ProbaTegy(parser.getData());
                break;
            case 2:
                strategy = new RandomStrategy(parser.getData());
                break;
            case 3:
                strategy = new FirstInStrategy(parser.getData());
                break;
            case 4:
                strategy = new LightestsInCache(parser.getData());
                break;
            case 5:
                strategy = new CacheIfQueryStrategy(parser.getData());
                break;
            default:
                strategy = new CacheIfQueryStrategy(parser.getData());
                break;
        }

        Controller controller = new Controller(strategy);

        controller.generateOutput(args[2], args[3]);
    }

}

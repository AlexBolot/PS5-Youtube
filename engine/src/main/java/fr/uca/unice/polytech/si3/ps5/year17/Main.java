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
        Parser parser = new Parser();

        String inputFileName = "/trending_today.in";

        try {
            String path = Main.class.getResource(inputFileName).getPath();
            parser.parse(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Controller controller = new Controller();

        controller.addStrategy(new AllInDataCenterStrategy(parser.getData()));
        controller.addStrategy(new CacheIfQueryStrategy(parser.getData()));
        controller.addStrategy(new FirstInStrategy(parser.getData()));
        controller.addStrategy(new ProbaTegy(parser.getData()));
        controller.addStrategy(new RandomStrategy(parser.getData()));

        controller.generateOutput(args[0], inputFileName);
    }

}

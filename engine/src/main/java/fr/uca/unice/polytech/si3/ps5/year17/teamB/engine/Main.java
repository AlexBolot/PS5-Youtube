package fr.uca.unice.polytech.si3.ps5.year17.teamB.engine;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies.CacheIfQueryStrategy;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.strategies.Strategy;
import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        if (args.length != 4)
        {
            System.err.println("Wrong number of argument, expected : <strat_id> <path_to_input_file> <path_to_data.out> <path_to_score.out>");
            System.exit(1);
        }

        Parser parser = new Parser(args[1]);

        int id = Integer.parseInt(args[0]);

        DataBundle data = parser.getData();

        Optional<Strategy> optionalStrategy = getStrategyById(id, data);

        Strategy strategy;

        strategy = optionalStrategy.orElseGet(() -> new CacheIfQueryStrategy(data));

        Controller controller = new Controller(strategy);

        controller.generateOutput(args[2], args[3]);
    }

    /**
     Method that will check if a class that inherit from Strategy is in the "strategies" package
     and therefore check if the id passed is in the possible strategy

     @param id         The id that the user requested
     @param dataBundle The data of the input file
     @return The strategy of with the id passed in parameter, is none of them correspond, return empty optional
     */
    public static Optional<Strategy> getStrategyById (int id, DataBundle dataBundle)
    {
        List<Strategy> strategies = new ArrayList8<>();

        Reflections reflections = new Reflections("fr.uca.unice.polytech.si3.ps5.year17.teamB.engine");

        Set<Class<? extends Strategy>> strategySubType = reflections.getSubTypesOf(Strategy.class);

        for (Class<? extends Strategy> cl : strategySubType)
        {
            Class[] types = new Class[]{DataBundle.class};

            Constructor ct;
            Strategy o2;
            try
            {
                ct = cl.getConstructor(types);
                o2 = (Strategy) ct.newInstance(dataBundle);
                strategies.add(o2);
            }
            catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e)
            {
                e.printStackTrace();
            }
        }

        for (Strategy strategy : strategies)
        {
            if (strategy.getStratID() == id) return Optional.of(strategy);
        }

        return Optional.empty();
    }
}

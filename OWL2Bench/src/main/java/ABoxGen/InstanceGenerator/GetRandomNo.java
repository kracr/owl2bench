/** gets random number from given min max range 
* In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the config.properties file */

package ABoxGen.InstanceGenerator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class GetRandomNo {
    public static Random random_ = new Random();

    public GetRandomNo() {
    }

    public static void setSeed(long seed) {
        random_.setSeed(seed);
    }

    public static int getRandomFromRange(int min, int max) {
    	
        return min + random_.nextInt(max - min + 1);
    }

}


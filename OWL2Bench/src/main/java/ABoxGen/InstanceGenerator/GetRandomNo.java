/* gets random number from given min max range */
/* The default values for random range (min and max for each parameter) are specified in the generator.java file. 
 In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the ConfigFile.java file */

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

    public static LinkedList<Integer> getRandomList(int num, int n) {
        LinkedList<Integer> list = new LinkedList();
        HashSet<Integer> hash = new HashSet();

        for(int i = 0; i < num && n != list.size(); ++i) {
            int index;
            for(index = getRandomFromRange(0, n - 1); hash.contains(index); index = getRandomFromRange(0, n - 1)) {
            }

            hash.add(index);
            list.add(index);
        }

        return list;
    }
}


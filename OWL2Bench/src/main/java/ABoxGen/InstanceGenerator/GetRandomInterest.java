/*gets random interest from all the available interests
/* The default values for random range (min and max for each parameter) are specified in the generator.java file. 
 In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the ConfigFile.java file */

package ABoxGen.InstanceGenerator;

public class GetRandomInterest {
    String interest;
    String TOKEN[]={"Shopping","StampCollecting","PCGame","VideoGame","ActionMovie","ComedyMovie","FictionMovie","HorrorMovie","ThrillerMovie","RomanticMovie","DocumentaryMovie","ClassicalMusic","RockAndRollMusic","PopMusic","AbstractPainting","OilPainting","BookReading","ArticleReading","NewspaperReading","Badminton","Basketball","Cricket","T20Cricket","Football","Soccer","Tennis","Swimming","Travelling"};
    public String getInterest(){
        interest=TOKEN[GetRandomNo.getRandomFromRange(0,27)];
        return interest;
    }
}
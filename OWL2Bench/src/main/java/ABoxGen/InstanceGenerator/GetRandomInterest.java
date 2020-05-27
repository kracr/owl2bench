/**gets random interest from all the available interests
* In order to modify the min-max range,that is, to modify the density of each node, user can make changes in the config.properties file */

package ABoxGen.InstanceGenerator;

public class GetRandomInterest {
    String interest;
    String TOKEN1[]={"VideoGame","ActionMovie","ComedyMovie","HorrorMovie","ThrillerMovie","RomanticMovie","DocumentaryMovie","ClassicalMusic","RockAndRollMusic","PopMusic","AbstractPainting","OilPainting","BookReading","ArticleReading","BadmintonSingles","Basketball","T20Cricket","Football","TableTennis","LawnTennis","BackStroke","SoloTravelling"};
    String TOKEN2[]={"Shopping","StampCollecting","PCGame","FictionMovie","NewspaperReading","FrontCrawl"};
    
    public String getInterestLikes(){
        interest=TOKEN1[GetRandomNo.getRandomFromRange(0,21)];
        return interest;
    }
    public String getInterestDislikes(){
        interest=TOKEN2[GetRandomNo.getRandomFromRange(0,5)];
        return interest;
    }
}
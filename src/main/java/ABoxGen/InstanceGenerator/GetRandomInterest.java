package ABoxGen.InstanceGenerator;

public class GetRandomInterest {
    String interest;
    String TOKEN[]={"Shopping","StampCollecting","PCGame","VideoGame","ActionMovie","ComedyMovie","FictionMovie","HorrorMovie","ThrillerMovie","RomanticMovie","DocumentaryMovie","ClassicalMusic","RockAndRollMusic","PopMusic","AbstractPainting","OilPainting","BookReading","ArticleReading","NewspaperReading","Badminton","Basketball","Cricket","T20Cricket","Football","Soccer","Tennis","Swimming","Travelling"};
    public String getInterest(){
        interest=TOKEN[GetRandomNo.getRandomFromRange(0,27)];
        return interest;
    }
}
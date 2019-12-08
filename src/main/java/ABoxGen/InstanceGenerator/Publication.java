package ABoxGen.InstanceGenerator;

//also covers hasCollaborationWith Property
import java.util.HashSet;
import java.util.Iterator;

public class Publication {

    Generator gen;
    int publicationIndex,univIndex,authorNum;
    static String[] TOKEN = new String[]{"Book", "Specification", "Article", "TechnicalReport", "UnofficialPublication", "ConferencePaper", "Publication", "JournalArticle", "Manual", "Software"};
    String publicationType, publicationInstance;
    HashSet<String> hash = new HashSet();
    University university;
    GetRandomPerson getRandomPerson;
    University universities[];


    public Publication(Generator gen,University universities[],int publicationIndex) {
        this.gen=gen;
        this.universities=universities;
        this.university=universities[univIndex];
        this.publicationIndex=publicationIndex;
        this.publicationInstance="publication"+ publicationIndex;
        this.publicationType=TOKEN[GetRandomNo.getRandomFromRange(0,9)];
        gen.classAssertion(gen.getClass(publicationType),gen.getNamedIndividual(publicationInstance));
        this.authorNum=GetRandomNo.getRandomFromRange(1,5);
        this.getRandomPerson=new GetRandomPerson();

        while(hash.size()!=authorNum)
        {
            String person = getRandomPerson.getRandomStudentOrFaculty(gen,universities);
            if(person!=null) {
                hash.add(person);
            }
        }

        Object[] arrayItem = hash.toArray();
        int j=1;
        for(int i = 0; i < (hash.size()-1);++i){
            gen.objectPropertyAssertion(gen.getObjectProperty("hasCollaborationWith"),gen.getNamedIndividual(arrayItem[i].toString() ),gen.getNamedIndividual(arrayItem[j].toString()));
            j=j+1;
        }
        Iterator<String> i=hash.iterator();

        while(i.hasNext())
        {
            gen.dataPropertyAssertion(gen.getDataProperty("hasPublicationDate"),gen.getNamedIndividual(publicationInstance),gen.getLiteral("xx/xx/xx"));
            gen.objectPropertyAssertion(gen.getObjectProperty("hasAuthor"),gen.getNamedIndividual(publicationInstance ),gen.getNamedIndividual(i.next()));
        }

        }

    }


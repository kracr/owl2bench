package ABoxGen.InstanceGenerator;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.parameters.Imports;
import org.semanticweb.owlapi.profiles.OWL2DLProfile;
import org.semanticweb.owlapi.profiles.OWL2ELProfile;
import org.semanticweb.owlapi.profiles.OWL2QLProfile;
import org.semanticweb.owlapi.profiles.OWL2RLProfile;
import org.semanticweb.owlapi.profiles.OWLProfileReport;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Profile {

    public static void main(String... args) throws OWLOntologyCreationException {


        if (args.length == 0) {
            System.err.println("Usage: owl-metrics [-v] file.owl");
            System.exit(0);
        }

        final boolean verbose = args[0].equals("-v") || args[0].equals("-verbose");

        int i = verbose ? 1 : 0;

        OWLOntology ontology = OWLManager.createOWLOntologyManager().loadOntologyFromOntologyDocument(new File("C:\\Users\\Gunjan\\Documents\\GitHub\\owl2dl-benchmark\\OWL2QL-1-output.owl"));
        Map<String, Object> metrics = new LinkedHashMap<>();
        Stream.of(new OWL2DLProfile(), new OWL2RLProfile(), new OWL2ELProfile(), new OWL2QLProfile())
                .forEach(profile -> {
                            OWLProfileReport report = profile.checkOntology(ontology);
                            System.out.println(report);
                            if (!verbose) {
                                metrics.put(profile.getName(), report.isInProfile());
                            } else {
                                metrics.put(profile.getName(), report.toString());
                            }
                        }
                );

       metrics.forEach((k, v) -> System.out.format("%s: %s\n", k, v));

    }

}
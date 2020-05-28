
import static au.com.bytecode.opencsv.CSVWriter.DEFAULT_SEPARATOR;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import org.apache.log4j.PropertyConfigurator;
import au.com.bytecode.opencsv.CSVWriter;
import edu.monash.infotech.owl2metrics.metrics.jgrapht.DirectoryProcessor;
import edu.monash.infotech.owl2metrics.metrics.jgrapht.DirectoryProcessor.*;
import edu.monash.infotech.owl2metrics.metrics.jgrapht.DirectoryProcessor2.*;
import org.apache.log4j.Logger;

public class MetricCalculator {
    private static final Logger LOGGER = Logger.getLogger(DirectoryProcessor.class);

    public static void main(String[] args) throws IOException {
        URL url = DirectoryProcessor.class.getClassLoader().getResource("log4j.properties");
        PropertyConfigurator.configure(url); //PropertyConfigurator.configure("log4j.properties");
        
        String dirName = "C:/Users/Vertika/Downloads/OREfiles10Dones/";
        String filesName = "C:/Users/Vertika/Downloads/1Ontology.csv";
        directoryFilesWriter(dirName,filesName);
        File processFile = null;
        processFile = new File(filesName);
        
        String csvName = "C:/Users/Vertika/Downloads/results10.csv";
        
        char delimiter = DEFAULT_SEPARATOR;
        File dir = new File(dirName);
        LOGGER.info("Parameters: ontsDir = " + dirName + ", csvName = " + csvName + ", delimiter = " + delimiter + ", to process onts in file: " + processFile);
        DirectoryProcessor processor = new DirectoryProcessor(csvName, delimiter, true, processFile);
        processor.writeHeader();
        processor.processDirectory(new File[]{dir});
        LOGGER.info("All ontologies processed!");
    }
    
    public static void directoryFilesWriter(String dirName,String csv) throws IOException {
		File fold = new File(dirName);
		String[] subs = fold.list();
		ArrayList<String> names = new ArrayList<String>();
		for(String file : subs){
			File fil = new File(fold,file);
			String path = fil.getAbsolutePath();
			int ind = path.lastIndexOf('\\');
			String name = path.substring(41);
			names.add(name);
		}
		
		String records[] = new String[names.size()];
        for (int j = 0; j < names.size(); j++) {
        	records[j] = names.get(j);
        	System.out.println(records[j]);
        }
        
		CSVWriter writer = new CSVWriter(new FileWriter(csv, true),'\n',
			    CSVWriter.NO_QUOTE_CHARACTER,
			    CSVWriter.DEFAULT_ESCAPE_CHARACTER,
			    CSVWriter.DEFAULT_LINE_END);
		writer.writeNext(records);
		writer.close();
    }

}

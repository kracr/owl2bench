import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.math3.util.Pair;

import edu.monash.infotech.r2o2.demo.DataGeneration.*;
import weka.core.Instances;

public class DataGeneration {
    public static void main(String args[])
    		throws Exception {

        // To generate the dataset ErrorsRemoved, set how_to_handle_err_onts to be 0;
        // To generate the dataset ErrorsReplaced, set how_to_handle_err_onts to be 1.
        int how_to_handle_err_onts = 0;

        edu.monash.infotech.r2o2.demo.DataGeneration dataGeneration = new edu.monash.infotech.r2o2.demo.DataGeneration();

        // Indicate the directory name. For example, if how_to_handle_err_onts = 1, then set dir_name to be ErrorsRemoved, otherwise ErrorsReplaced
        // in the provided demo package.
        
        File fold = new File("data/ErrorsRemoved");
        String dir = fold.getAbsolutePath();
        /*
        System.out.println(fold.getAbsolutePath());
		String[] subs = fold.list();
		for(String file : subs){
			File fil = new File(fold,file);
			String path = fil.getAbsolutePath();
			System.out.println(path);
		}
		*/
		
        // Give a random seed for shuffling of the entire corpus (for reproducibility)
        int data_gen_seed = 9;

        // Cross-validation (10 means 10-fold cross-validation). We will generate 10 different datasets where each dataset has the different training
        // and testing data
        int nFold = 10;

        // Ontology metrics file
        String metricsFile = dir + "/ore2015.csv";
        //String metricsFile = dir + "/results.csv";

        // Pre-chosen ontology metrics from the 91 metrics. The 29 metrics have been chosen after preprocessing on ORE2014 dataset that is a larger dataset.
        // If this file is not given, we will apply the preprocessing technique on the current ontology metrics files (e.g. ore2015.csv).
        File predefined_metric_file = new File(dir + "/29_metrics.txt");

        // Generate metrics data that are used by any of reasoners successfully. That is, the metrics data don't include error ontologies.
        String newMetricsFile = dataGeneration.genMetricData(metricsFile, dir);
        
        // perform pre-processing on the metrics data
        Pair<String, Instances> pair = dataGeneration.preprocess(newMetricsFile, predefined_metric_file);
        String dupOntFileName = pair.getKey();
        Instances data = pair.getValue();
        
        // If predefined_metric_file is given, we only keep the ontologies in the file.
        if (predefined_metric_file != null)
            data = dataGeneration.getInstancesByMetrics(data, predefined_metric_file);
        
        // For each reasoner, generate its training example by combining metric values and reasoning times of the sample ontologies
        dataGeneration.genMetricAndClsTimeData(data, dupOntFileName, dir, how_to_handle_err_onts);
        
        // Divide the data for each reasoner into three different parts:
        // 1) for training prediction model, 2) for training meta-reasoner (ranking matrix), and 3) testing meta-reasoner
        //dataGeneration.genMetaData(dir, nFold, data_gen_seed);
    }
}
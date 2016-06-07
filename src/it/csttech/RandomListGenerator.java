package it.csttech;

import it.csttech.randomlist.*;
import java.io.*;
import java.security.*;
import java.util.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.*;

/**
* This class generates a list of integers contained in a given range.<br>
* No consecutive numbers can be generated.<br>
* See .properties file for default values.<br>
* Launch it together with the -h option to see the possible options available.
*/
public class RandomListGenerator {

	private static final Logger log = LogManager.getLogger(RandomListGenerator.class.getName());
	private static long iMin;
	private static long iMax;
	private static int iVar;
	private static int iSize;
	private static final String DEFAULT_PROPERTIES = "./cfg/RandomListGenerator.properties";
	private static boolean appender;
	private static long lLength;
	private static File outputFile;
	private static OptionsHandler opt;

	public RandomListGenerator(PropertiesHandler propertiesHandler){
		iMin  = propertiesHandler.getMin();
		iMax  = propertiesHandler.getMax();
		iVar  = propertiesHandler.getVar();
		iSize = propertiesHandler.getSize();
		outputFile = propertiesHandler.getOutputFile();
		appender = propertiesHandler.isAppender();
		lLength = Math.round(Math.ceil(Math.log10(iMax)));

		RandomListBuilder randomListBuilder = new RandomListBuilder(iMin, iMax, iVar, iSize);
		List<Long> list = randomListBuilder.getList();
		if(propertiesHandler.hasInputFile()){
			long shuffleSeed = System.nanoTime();
			Collections.shuffle(list, new Random(shuffleSeed));
			printOutput(list, outputFile, appender, lLength, propertiesHandler.getNameList());
		} else {
			printOutput(list, outputFile, appender, lLength);
		}
	}

	/**
	* Main method.
	* @param args Possible options.
	*/
	public static void main(String[] args) {

		opt = new OptionsHandler(args);
		CommandLine commandLine = opt.getCommandLine();
		String propFile = commandLine.getOptionValue("p", DEFAULT_PROPERTIES);
		PropertiesHandler propertiesHandler = new PropertiesHandler(commandLine, propFile);

		if(opt.isHelpCalled()) {
			return;
		}

		RandomListGenerator randomListGenerator = new RandomListGenerator(propertiesHandler);

		log.trace("Done! Check output file.\n"); //Exit message

	}

	private static void printOutput(List<Long> list, File outputFile, boolean appender, long lLength) {
		try(PrintWriter printout = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, appender)))) {
			for(int i = 0; i < list.size(); i++) {
				printout.printf("%0"+lLength+"d%n", list.get(i));
			}

		} catch (IOException e) {
			e.getMessage();
		}

	}

	private static void printOutput(List<Long> list, File outputFile, boolean appender, long lLength, List<String> nameList) {
		try(PrintWriter printout = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, appender)))) {
			int iMaximumLength = calculateMaximumLength(nameList);
			String spaces;
			for(int i = 0; i < list.size(); i++) {
				spaces = "";
				if(nameList.get(i).length()<iMaximumLength) {
					spaces = new String(new char[iMaximumLength - nameList.get(i).length()]).replace("\0", " ");
				}
				printout.print(nameList.get(i) + spaces + "\t");
				printout.printf( "%0" + lLength + "d%n", list.get(i));
			}

		} catch (IOException e) {
			e.getMessage();
		}

	}

	private static int calculateMaximumLength(List<String> nameList){
		int result = 0;
		for ( String j : nameList ) {
			if( ! j.equals(null) ){
				if (j.length() > result) {
					result = j.length();
				}
			}
		}
		return result;
	}

}

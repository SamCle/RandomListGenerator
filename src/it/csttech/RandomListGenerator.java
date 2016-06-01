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

	/**
	* Main method.
	* @param args Possible options.
	*/
	public static void main(String[] args) {

		final String DEFAULT_PROPERTIES = "config/RandomListGenerator.properties";
		Logger log = LogManager.getLogger();

		OptionsHandler opt = new OptionsHandler(args);
		CommandLine commandLine = opt.commandLine;

		String propFile = commandLine.getOptionValue("p", DEFAULT_PROPERTIES);
		Properties properties = OptionsHandler.readProperties(propFile);

		if(opt.helpCalled) {
			return;
		}

		long iMin =   Long.parseLong(commandLine.getOptionValue("m", properties.getProperty("default.minimum"   )));
		long iMax =   Long.parseLong(commandLine.getOptionValue("M", properties.getProperty("default.Maximum"   )));
		int  iVar = Integer.parseInt(commandLine.getOptionValue("v", properties.getProperty("dafault.variation" )));
		int iSize = Integer.parseInt(commandLine.getOptionValue("s", properties.getProperty("default.size"      )));
		File outputFile =   new File(commandLine.getOptionValue("f", properties.getProperty("default.outputFile")));
		boolean appender =           commandLine.hasOption("a");
		long lLength = Math.round(Math.ceil(Math.log10(iMax)));

		List<Long> list = new ArrayList<Long>(iSize);
		List<Long> separators = new ArrayList<Long>();
		UniformRandom uniformRandom = new UniformRandom(iMin, iMax, iSize);

		separators = setSeparators(iMin, iMax, iSize, iVar, uniformRandom);
		list = setList(iSize, separators, uniformRandom);

		printOutput(list, outputFile, appender, lLength);

		log.trace("Done! Check output file.\n"); //Exit message
	}

	private static List<Long> setSeparators(long iMin, long iMax, int iSize, int iVar, UniformRandom uniformRandom) {
		List<Long> separators = new ArrayList<Long>();
		long separator;

		separators.add(0, iMin);
		for(int i = 1; i < iSize; i++) {
			separator =  (i) * (iMax - iMin) / iSize + iMin;
			separator += Math.floor(2 * (uniformRandom.nextDouble() - 0.5) * ((iMax - iMin) / iSize) * iVar / 100);
			separators.add(i, separator);
		}
		separators.add(iSize, iMax);

		return separators;
	}

	private static List<Long> setList(int iSize, List<Long> separators, UniformRandom uniformRandom) {
		List<Long> list = new ArrayList<Long>(iSize);
		long point;

		for(int i = 0; i < iSize; ) {
			point = (long) Math.floor(uniformRandom.nextDouble() * (separators.get(i+1) - separators.get(i)) + separators.get(i));
			if(uniformRandom.checkNext( (i == 0? -2 : list.get(i-1)), point)) {
				list.add(i, point);
				i++;
			} else { }
		}

		return list;
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
}

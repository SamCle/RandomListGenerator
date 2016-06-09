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
	private static OptionsHandler opt;

	public RandomListGenerator(PropertiesHandler propertiesHandler) throws RandomlistException {
		long min  = propertiesHandler.getMin();
		long max  = propertiesHandler.getMax();
		int var  = propertiesHandler.getVar();
		int size = propertiesHandler.getSize();
		File outputFile = propertiesHandler.getOutputFile();
		boolean appender = propertiesHandler.isAppender();
		long length = Math.round(Math.ceil(Math.log10(max)));
		RandomListBuilder randomListBuilder = new RandomListBuilder(min, max, var, size);
		List<Long> list = randomListBuilder.getList();
		if(propertiesHandler.hasInputFile()) {
			long shuffleSeed = System.nanoTime();
			Collections.shuffle(list, new Random(shuffleSeed));
			RandomOutput.printOutput(list, outputFile, appender, length, propertiesHandler.getNameList());
		} else {
			RandomOutput.printOutput(list, outputFile, appender, length);
		}
	}

	/**
	* Main method.
	* @param args Possible options.
	*/
	public static void main(String[] args) {

		try {
			opt = new OptionsHandler(args);
			CommandLine commandLine = opt.getCommandLine();
			String propFile = commandLine.getOptionValue("p", commandLine.getOptionValue("dp"));
			PropertiesHandler propertiesHandler = new PropertiesHandler(commandLine, propFile);

			if(opt.isHelpCalled()) {
				return;
			}
			RandomListGenerator randomListGenerator = new RandomListGenerator(propertiesHandler);
			log.trace("Done! Check output file.\n"); //Exit message
		} catch (RandomlistException e) {
			e.getMessage();
		}
	}
}

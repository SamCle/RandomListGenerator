package it.csttech;

import it.csttech.randomlist.*;
import java.io.*;
import java.security.*;
import java.util.*;

public class RandomListGenerator {
	public static void main(String[] args) {

		boolean appender = true;
		OptionsHandler opt = new OptionsHandler(args);
		if(opt.lOptions.size() == 0) {
			return; 	// opt.lOptions.size() is equal to zero iff the help option has been invoked.
						// In this case, we close the main immediately.
		}

		long iMin = opt.lOptions.get("m");
		long iMax = opt.lOptions.get("M");
		int iVar = opt.iOptions.get("v");
		int iSize = opt.iOptions.get("s");
		File outputFile = new File(opt.sOptions.get("f"));
		List<Long> list = new ArrayList<Long>(iSize);
		List<Long> separators = new ArrayList<Long>();
		UniformRandom uniformRandom = new UniformRandom(iMin, iMax, iSize);

		separators = setSeparators(iMin, iMax, iSize, iVar, uniformRandom);
		list = setList(iSize, separators, uniformRandom);

		printOutput(list, outputFile, appender);
		System.out.println("Done! Check output file");
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
			} else {}
		}

		return list;
	}

	private static void printOutput(List<Long> list, File outputFile, boolean appender) {
		try(PrintWriter printout = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, appender)))) {
			for(int i = 0; i < list.size(); i++) {
				printout.printf("%05d%n", list.get(i));
			}

		} catch (IOException e) {
			e.getMessage();
		}
		
	}
}

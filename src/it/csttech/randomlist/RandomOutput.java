package it.csttech;

import it.csttech.randomlist.*;
import java.io.*;
import java.security.*;
import java.util.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.*;

public class RandomOutput {

	public static void printOutput(List<Long> list, File outputFile, boolean appender, long length) {
		try(PrintWriter printout = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, appender)))) {
			for(int i = 0; i < list.size(); i++) {
				printout.printf("%0"+length+"d%n", list.get(i));
			}

		} catch (IOException e) {
			e.getMessage();
		}

	}

	public static void printOutput(List<Long> list, File outputFile, boolean appender, long length, List<String> nameList) {
		try(PrintWriter printout = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, appender)))) {
			int iMaximumLength = calculateMaximumLength(nameList);
			for(int i = 0; i < list.size(); i++) {
				printout.printf("%-" + iMaximumLength + "s", nameList.get(i));
				printout.print("\t");
				printout.printf( "%0" + length + "d%n", list.get(i));
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

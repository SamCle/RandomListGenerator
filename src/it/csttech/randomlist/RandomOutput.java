package it.csttech;

import it.csttech.randomlist.*;
import java.io.*;
import java.security.*;
import java.util.*;
import org.apache.commons.cli.*;
import org.apache.logging.log4j.*;

public class RandomOutput {

	public static void printOutput(List<Long> list, File outputFile, boolean appender, long lLength) {
		try(PrintWriter printout = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, appender)))) {
			for(int i = 0; i < list.size(); i++) {
				printout.printf("%0"+lLength+"d%n", list.get(i));
			}

		} catch (IOException e) {
			e.getMessage();
		}

	}

	public static void printOutput(List<Long> list, File outputFile, boolean appender, long lLength, List<String> nameList) {
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
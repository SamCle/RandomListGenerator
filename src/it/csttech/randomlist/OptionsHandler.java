package it.csttech;

import java.io.*;
import java.util.*;
import java.util.LinkedHashMap;
import org.apache.commons.*;
import org.apache.commons.cli.*;

class OptionsHandler {

  protected Map<String,String>  sOptions;
  protected Map<String,Integer> iOptions;
  protected Map<String,Double>  dOptions;

  OptionsHandler(String[] args){

    this.sOptions = new HashMap<String,String>(0);
    this.iOptions = new HashMap<String,Integer>(0);

    Options options = new Options();
    DefaultParser parser = new DefaultParser();
    options.addOption(new Option("h", "help",    false, "Shows help."                 ));
    options.addOption(new Option("m", "minimum", true,  "Lower bound for the result." ));
    options.addOption(new Option("M", "Maximum", true,  "Upper bound for the result." ));
    options.addOption(new Option("s", "size",    true,  "Size of the result."         ));
    options.addOption(new Option("f", "file",    true,  "Output file name."           ));

    CommandLine commandLine = null;

    try {
      commandLine = parser.parse(options, args);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (commandLine.hasOption("h")) {
      HelpFormatter formatter = new HelpFormatter();
      formatter.printHelp("Converter", options);
      return;
    }

    iOptions.put("m", Integer.parseInt(commandLine.getOptionValue("m",     "0")));
    iOptions.put("M", Integer.parseInt(commandLine.getOptionValue("M", "99999")));
    iOptions.put("s", Integer.parseInt(commandLine.getOptionValue("s", " 1000")));
    sOptions.put("f", commandLine.getOptionValue("f", "output.txt")));
    

    /*    String input = ""; // calcola o legge l'input e ne ricava l'estensione
    if(cmd.hasOption("s")){
    input = cmd.getOptionValue("s");
  } else {
  input = cmd.getOptionValue("k", "fw");
  input = "test\\Test" + input.toUpperCase() + "." + input;
}
System.out.println(input);
String extension = input.substring(input.lastIndexOf(".") + 1, input.length());

optionsMap.put("extension", extension);
optionsMap.put("input", input);

String newExtension = ( extension.equals("csv") ? "fw" : "csv" ); // calcola o legge l'output e ne ricava l'estensione
newExtension = cmd.getOptionValue("e", newExtension);
String defaultOutput = input.substring(0, input.lastIndexOf(".")) + "Output" + newExtension.toUpperCase() + "." + newExtension;
String output = cmd.getOptionValue("o", defaultOutput);
System.out.println(output);

optionsMap.put("newExtension", newExtension);
optionsMap.put("output", output);

final String separatorCSV = cmd.getOptionValue("scsv", ";"); // alloca le costanti
final String boundaryCSV = cmd.getOptionValue("bcsv", "\"");
final String fillerFW = cmd.getOptionValue("ffw", " ");
final int standardLengthFW = Integer.parseInt(cmd.getOptionValue("lfw", "-1"));

optionsMap.put("separatorCSV", separatorCSV);
optionsMap.put("boundaryCSV", boundaryCSV);
optionsMap.put("fillerFW", fillerFW);
optionsMap.put("standardLengthFW", new Integer(standardLengthFW).toString());

return optionsMap;
*/
}

}

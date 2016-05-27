package it.csttech;
import java.io.*;
import java.util.*;
import org.apache.commons.cli.*;
import org.apache.commons.*;

class OptionsHandler {
/*
final String[] KEYS           = { "-b", "-f", "-s"        , "-m"};
final String[] DEFAULT_VALUES = { "16", "3" , "Source.xls", "fast" };
 */

  static CommandLine run(String[] args){
    Options options = new Options();
    DefaultParser parser = new DefaultParser();
    options.addOption(new Option("h", "help",   false, "Mostra l'help."                  ));
    options.addOption(new Option("s", "source", true,  "File di input."                  ));
    options.addOption(new Option("b", "books",  true,  "Numero dei candidati."           ));
    options.addOption(new Option("f", "fields", true,  "Numero di variabili."            ));
    options.addOption(new Option("m", "mode",   true,  "Modo di scelta fast/slow/basic." ));

    try {
      CommandLine cmd = parser.parse(options, args);
          if (cmd.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("SceltaConJava [options]", options);
          }
      return cmd;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}

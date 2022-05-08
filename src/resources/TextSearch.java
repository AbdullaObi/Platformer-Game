package resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Class containing static methods related to the loading and the saving of the datas
 */
public class TextSearch {
	/**
	 * This function load a file and save its line in a list of String
	 * @param _file, the path of the file
	 * @return the lines of the file
	 */
	public static List<String> ReadTextLevel(String _file){
		List<String> listeLines = new LinkedList<>();
	    try
	    {
	      File file = new File(_file);
	      FileReader fr = new FileReader(file);    
	      BufferedReader br = new BufferedReader(fr);  
	      String line;
	      while((line = br.readLine()) != null)
	      {
	        listeLines.add(line);    
	      }
	      fr.close();    
	    }
	    catch(IOException e)
	    {
	      e.printStackTrace();
	    }
	    return listeLines;
	}

	/**
	 * This function analysis a line, whose arguments are splitted by ;
	 * @param line, the line
	 * @return a list of string containing the arguments
	 */
	public static List<String> AnalyseLine(String line) {
		List<String> toSend = new LinkedList<>();
		String str = "";
		for (char c : line.toCharArray()) {
			if (c==';') {
				toSend.add(str);
				str="";
			}
			else {
				str+=c;
			}
		}
		return toSend;
	}

	/**
	 * Add a line into a file
	 * @param filename, the name of the file
	 * @param text, the text we want to append to the file
	 */
    public static void appendLine(String filename, String text) {
        BufferedWriter bufWriter = null;
        FileWriter fileWriter = null;

        try {
			fileWriter = new FileWriter(filename, true);
            bufWriter = new BufferedWriter(fileWriter);
            bufWriter.newLine();
            bufWriter.write(text);
            bufWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }  
}

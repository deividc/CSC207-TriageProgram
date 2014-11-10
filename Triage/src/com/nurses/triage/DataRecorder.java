package com.nurses.triage;

/*
 * This class consists of methods that operates reading or writing strings of/on files.
 * 
 * The method fileRecorder needs two parameters, FILEPATH and data. 
 * The method fileReader needs only one parameter, FILEPATH. It returns a string
 * containing all the data on the file.
 * 
 * <p>The data is a string containing the specific data to be recorded.
 * <p>FILEPATH can be the path or the file name to record or to read the data in the same
 * folder of the program.
 * 
 * @author Deivid Cavalcante da Silva
 * @version 1.0.0
 * @date 2014-11-07
 */

import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.IOException;

public class DataRecorder
{
    /*
     * Record a string of data in a file.
     * 
     * @param       the complete path of the file or just the name plus extension.
     * 
     * @param       string data previously formatted.
     * 
     * @return      boolean True for no exceptions, False for exceptions
     */
    public boolean fileRecorder(String FILEPATH, String data)
    {
        try {
        	PrintStream out = new PrintStream(new FileOutputStream(FILEPATH));
            out.print(data);
        }
        catch ( IOException e) {
            return false;
        }
        
        return true;
    }
    
    public String fileReader(String FILEPATH)
    {
        StringBuilder data = new StringBuilder();
        try {
        	Scanner scanner = new Scanner(new FileInputStream(FILEPATH));
            while (scanner.hasNextLine()) {
                data.append(scanner.nextLine());
                data.append("\n");
            }
            scanner.close();
        }
        catch (IOException e) {
            //To implement
        }
        
        return data.toString();
    }
}
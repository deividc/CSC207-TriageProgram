package com.nurses.triage;

import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.IOException;

public class DataRecorder
{
    public boolean fileRecorder(String filename, String data)
    {
        try {
        	PrintStream out = new PrintStream(new FileOutputStream(filename));
            out.print(data);
        }
        catch (IOException e)
        {
            return false;
        }
        
        return true;
    }
    
    public String fileReader(String filename)
    {
        StringBuilder data = new StringBuilder();
        try {
        	Scanner scanner = new Scanner(new FileInputStream(filename));
            while (scanner.hasNextLine()) {
                data.append(scanner.nextLine());
                data.append("\n");
            }
            scanner.close();
        }
        catch (IOException e)
        {
            
        }
        
        return data.toString();
    }
}
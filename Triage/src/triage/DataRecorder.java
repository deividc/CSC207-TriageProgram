package triage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DataRecorder
{
    public boolean fileRecorder(String filename, String data)
    {
        try {
        	FileWriter output = new FileWriter(filename);
            BufferedWriter writer = new BufferedWriter(output);
            writer.write(data);
        }
        catch ( IOException e)
        {
            return false;
        }
        
        return true;
    }
}

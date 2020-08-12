package Utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CustomFileReader {
    public static String loadFileAsString(String path){
        StringBuilder builder = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine())!= null){
                builder.append(line+"\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

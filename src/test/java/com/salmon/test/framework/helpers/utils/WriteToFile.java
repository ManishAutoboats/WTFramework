package com.salmon.test.framework.helpers.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author msmith
 * Simple class to enable writting to text file for persistant information storage
 */
public class WriteToFile {

  public static PrintWriter writer;

  public static void createNewFile(String location, String filename) throws FileNotFoundException, UnsupportedEncodingException {
    // use 'location' as reference if you want files to placed in specific area

    // Create new empty text file
    writer = new PrintWriter(filename+".txt", "UTF-8");
    }

    // Write contents to inside of file
    public static void writeToFile(String content){
    writer.println(content);
    }

    // close and essentially 'save' file
    public static void closeFile(){
    writer.close();
    }
}

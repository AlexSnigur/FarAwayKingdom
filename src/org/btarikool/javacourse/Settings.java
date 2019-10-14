package org.btarikool.javacourse;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
    Properties properties;


    static {
        try (InputStream input =new FileInputStream("conf/default.properties")){
        } catch(IOException ioe){
            System.out.println("properties file not found");

        }
    }
}

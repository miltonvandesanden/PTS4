/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 *
 * @author Jasper
 */
public class ReadLanguage {
    
    public static Properties getWords(String language) throws IOException{
        InputStream inputStream = ReadLanguage.class.getClassLoader()
                .getResourceAsStream(language + ".properties");
        
        Properties languageWords = new Properties();
        languageWords.load(inputStream);
        
        return languageWords;
    }
}

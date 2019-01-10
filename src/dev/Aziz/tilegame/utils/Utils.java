package dev.Aziz.tilegame.utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

//contains helper functions and methodes
public class Utils {

    public static String loadFileAsString(String path){
        StringBuilder builder = new StringBuilder();

        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;

            while((line = br.readLine()) != null){
                builder.append(line + "\n");
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static int parseInt(String number){

        try{
            return Integer.parseInt(number.trim());     //first String value: \n485
        }catch (NumberFormatException e){
            e.printStackTrace();
            return 0;
        }

    }

    public static String loadXMLFileAsString(String path, int index){

        try {

            File file = new File(path);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            factory.setValidating(false);


            DocumentBuilder builder = factory.newDocumentBuilder();

            try {
                Document document = builder.parse(file);

                String IDs = document.getElementsByTagName("data").item(index).getTextContent();

                return IDs;

            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return "FAIL";

    }

}

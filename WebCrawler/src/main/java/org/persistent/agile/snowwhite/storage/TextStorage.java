package org.persistent.agile.snowwhite.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import edu.uci.ics.crawler4j.parser.HtmlParseData;

public class TextStorage {

	   private static String crawlStorageFolder = "c:\\tmp\\data\\crawl\\content";
	
	    public static void Store(String filename, HtmlParseData htmlParseData ) {
	        BufferedWriter writer = null;
	        try {
	            //create a temporary file
	            
	            File HtmlFile = new File(crawlStorageFolder,filename);

	            // This will output the full path where the file will be written to...
	            System.out.println(HtmlFile.getCanonicalPath());

	            writer = new BufferedWriter(new FileWriter(HtmlFile));
	            writer.write(htmlParseData.getHtml());
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                // Close the writer regardless of what happens...
	                writer.close();
	            } catch (Exception e) {
	            }
	        }
	    }
}


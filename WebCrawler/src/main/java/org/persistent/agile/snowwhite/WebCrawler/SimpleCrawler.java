package org.persistent.agile.snowwhite.WebCrawler;

import java.util.Set;
import java.util.regex.Pattern;

import org.persistent.agile.snowwhite.storage.TextStorage;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class SimpleCrawler extends WebCrawler {

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpe?g"
                                                           + "|png|mp3|mp3|zip|gz))$");

    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
     @Override
     public boolean shouldVisit(Page referringPage, WebURL url) {
         String href = url.getURL().toLowerCase();
         return !FILTERS.matcher(href).matches() ;
         //TODO we can filter by set of website
         // && href.startsWith("http://www.ics.uci.edu/");
     }

     /**
      * This function is called when a page is fetched and ready
      * to be processed by your program.
      */
     @Override
     public void visit(Page page) {
         String url = page.getWebURL().getURL();
         System.out.println("URL: " + url);        
         System.out.println("Status code : " + page.getStatusCode() ) ;
         System.out.println("Content Type : " + page.getContentType() ) ;
         Object parseData = page.getParseData() ;
         //System.out.println("HTML Content : " + page.getParseData() ) ;
         if (parseData instanceof HtmlParseData) {
             HtmlParseData htmlParseData = (HtmlParseData) parseData;
             String text = htmlParseData.getText();
             String html = htmlParseData.getHtml();
             System.out.println("Text length: " + text.length());
             System.out.println("Html length: " + html.length());
             
             Set<WebURL> links =htmlParseData.getOutgoingUrls();

             System.out.println("Number of outgoing links: " + links.size());
             
             //Save file on disk for the moment in : 
             //TextStorage.Store(htmlParseData.getTitle(), htmlParseData);
             String filename = url.replace("http://","").replace('/','_').replace('~','_') ;
             TextStorage.Store(filename, htmlParseData);
                        
             System.out.println("Text length: " + text.length());
         } else {
        	 System.out.println("We do not have parseData for this url " + url ) ;
         }
         
         
    }
}
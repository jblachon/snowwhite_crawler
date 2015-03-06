package org.persistent.agile.snowwhite.WebCrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

	public class WebController {
	    public static void main(String[] args) throws Exception {
	        String crawlStorageFolder = "c:\\tmp\\data\\crawl\\root";
	        int numberOfCrawlers = 2;

	        CrawlConfig config = new CrawlConfig();
	        config.setCrawlStorageFolder(crawlStorageFolder);
	        int maxDepthOfCrawling=2;
			config.setMaxDepthOfCrawling(maxDepthOfCrawling);

			
			int maxPagesToFetch=50;
			config.setMaxPagesToFetch(maxPagesToFetch);
			
			
			int politenessDelay=200;
			config.setPolitenessDelay(politenessDelay);

			
			config.setProxyHost("frproxy.persistent.co.in");
			config.setProxyPort(8080);
			
			
			/*
	         * Instantiate the controller for this crawl.
	         */
	        PageFetcher pageFetcher = new PageFetcher(config);
	        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
	        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
	        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

	        /*
	         * For each crawl, you need to add some seed urls. These are the first
	         * URLs that are fetched and then the crawler starts following links
	         * which are found in these pages
	         */
	        //controller.addSeed("http://www.ics.uci.edu/~lopes/");
	        //controller.addSeed("http://www.ics.uci.edu/~welling/");
	        
	        controller.addSeed("http://www.qualitystreet.fr/2008/08/27/kanban-board-encore-plus-lean-encore-plus-agile/");
	        
	        //controller.addSeed("http://www.ics.uci.edu/");

	        /*
	         * Start the crawl. This is a blocking operation, meaning that your code
	         * will reach the line after this only when crawling is finished.
	         */
	        controller.start(SimpleCrawler.class, numberOfCrawlers);
	        	        
	    }
	}

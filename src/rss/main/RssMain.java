package rss.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import rss.Impl.EmailServiceImpl;
import rss.Impl.FaceBookServiceImpl;
import rss.action.RSSFeedParser;
import rss.model.Feed;
import rss.model.FeedMessage;

public class RssMain {

	private static final EmailServiceImpl emailimpl = new EmailServiceImpl();
	private static final FaceBookServiceImpl fbiml = new FaceBookServiceImpl();
	private static final String FILENAME = "C:\\Test\\RSS.txt";
	private static BufferedWriter bw = null;
	private static FileWriter fw = null;

    private static final Logger LOGGER = Logger.getAnonymousLogger();

	 public static void main(String[] args) {
		try {
			
			RSSFeedParser parser = new RSSFeedParser("http://tech.uzabase.com/rss");
		    Feed feed = parser.readFeed();

		    int count = 0;
		    for (FeedMessage message : feed.getMessages()) {
		    	//implement with other service in future without dependency 
		    	LOGGER.info("Start email");
		    	emailimpl.sendMessage(message);
		    	LOGGER.info("End email");

		    	LOGGER.info("Start FB");
		    	fbiml.sendMessage(message);
		    	LOGGER.info("Start FB");
		   		    	
		    	WriteText(message.toString());
		    }
		
		}catch (Exception e) {
			LOGGER.warning("Error :" + e.getMessage());
		}
		
	 }

	 public static void WriteText(String msg){
		
			
			try {
				
				File theFile=new File (FILENAME);
					fw = new FileWriter(FILENAME,true);
					bw = new BufferedWriter(fw);
					bw.write(msg);
					bw.newLine();

					bw.flush();

			} catch (IOException e) {
				LOGGER.warning("Error :" + e.getMessage());

			} finally {

				try {

					if (bw != null)
						bw.close();

					if (fw != null)
						fw.close();

				} catch (IOException ex) {

					ex.printStackTrace();

				}

			}

	 }

}

package rss.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import rss.model.Feed;
import rss.model.FeedMessage;  
  
public class RSSFeedParser {  

  static final String TITLE = "title"; 
  static final String ITEM = "item"; 
  static final String LINK = "link";  
  static final String DESCRIPTION = "description";  
  static final String PUBDATE = "pubdate";  
  static final String GUID = "guid";  
  static final String ENCLOSURE = "enclosure";
  static final String CATEGORY = "category";
  
  final URL url;
  
  private static final Logger LOGGER = Logger.getAnonymousLogger();
  
public RSSFeedParser(String feedUrl) {  
    try {  
		 this.url = new URL(feedUrl);  
    } catch (MalformedURLException e) {  
      throw new RuntimeException(e);  
    }  
  }  
  
  public Feed readFeed() throws UnsupportedEncodingException {  
    Feed feed = null;  
    try {  
      boolean isFeedHeader = true;  

      String title = "";  
      String description = "";  
      String link  = "";  
      String pubdate = "";  
      String guid = "";  
      String enclosure = "";  
      String category = "";  

      XMLInputFactory inputFactory = XMLInputFactory.newInstance();  
      InputStream in = read();  

      inputFactory.setProperty(XMLInputFactory.IS_COALESCING,true);
      XMLEventReader eventReader = inputFactory.createXMLEventReader(in,"UTF-8");
     
      while (eventReader.hasNext()) {  
        XMLEvent event = eventReader.nextEvent();
        
        if (event.isStartElement()) { 
         
          String localPart = event.asStartElement().getName()  
              .getLocalPart();  
          

          switch (localPart) {  
          case ITEM:  
            if (isFeedHeader) {  
              isFeedHeader = false;  
              feed = new Feed(title, link, description);  
            }  
            event = eventReader.nextEvent();  
            break;  
          case TITLE:  
            title = getCharacterData(event, eventReader);  
            break;  
          case DESCRIPTION:  
            description = getCharacterData(event, eventReader);  
            break;  
          case LINK:  
            link = getCharacterData(event, eventReader);  
            break;  
         case PUBDATE:  
            pubdate = getCharacterData(event, eventReader);  
            break;
          case GUID:  
            guid = getCharacterData(event, eventReader);  
            break; 
          case ENCLOSURE:  
            enclosure = getCharacterData(event, eventReader);  
            break; 
          case CATEGORY:  
            category = getCharacterData(event, eventReader);  
            break; 
          }  
        } else if (event.isEndElement()) {  
          if (event.asEndElement().getName().getLocalPart() == (ITEM)) {  
            FeedMessage message = new FeedMessage();  
            message.setLink(link);  
            message.setTitle(title);  
            message.setDescription(description);  
            message.setPubdate(pubdate);  
            message.setGuid(guid);  
            message.setEnclosure(enclosure);  
            message.setCategory(category);  
           
   		  
            if (message.toString().indexOf("New Pick") == -1) {
            	feed.getMessages().add(message);  
            }
            event = eventReader.nextEvent();  
            continue;  
          }  
        }  
      }  
    } catch (XMLStreamException e) { 
		LOGGER.warning("Error :" + e.getMessage());

      throw new RuntimeException(e);  
    }  
    return feed;  
  }  
  
  private String getCharacterData(XMLEvent event, XMLEventReader eventReader)  
      throws XMLStreamException, UnsupportedEncodingException {  
    String result = ""; 

    event = eventReader.nextEvent(); 

   if (event instanceof Characters) {  
       result = event.asCharacters().getData();  
    }  
 

    return result;  
  }  
  
  private InputStream read() {  
    try {  

       // Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("x", 3333));
        //URLConnection urlc = url.openConnection(proxy);
    	
    	URLConnection urlc = url.openConnection();
    return urlc.getInputStream();
    } catch (IOException e) {  
    	LOGGER.warning("read()"+e.getMessage());
      throw new RuntimeException(e);  
    }  
  }  
}   
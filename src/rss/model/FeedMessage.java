package rss.model;

/* 
 * Represents one RSS message 
 */  
public class FeedMessage {  
  
  String title;  
  String description;  
  String link;  
  String guid;
  String pubdate;
  String enclosure;
  String category;  
  
  public String getTitle() {  
    return title;  
  }  
  
  public void setTitle(String title) {  
    this.title = title;  
  }  
  
  public String getDescription() {  
    return description;  
  }  
  
  public void setDescription(String description) {  
    this.description = description;  
  }  
  
  public String getLink() {  
    return link;  
  }  
  
  public void setLink(String link) {  
    this.link = link;  
  }  
  
  public String getGuid() {  
    return guid;  
  }  
  
  public void setGuid(String guid) {  
    this.guid = guid;  
  }  
  
   public void setPubdate(String pubdate) {
	    this.pubdate = pubdate;  
		
  }
  public String getPubdate() {  
	    return pubdate;  
  }  

  public void setEnclosure(String enclosure) {
	    this.enclosure = enclosure;  
		
  }
  
  public String getEnclosure() {  
	    return enclosure;  
  }

  public void setCategory(String category) {
	    this.category = category;  
  }  
	  
  public String getCategory() {  
	    return category;  
  }
  
  @Override  
  public String toString() {  
    return "FeedMessage [title=" + title + ", description=" + description  
        + ", link=" + link + ", guid=" + guid + ", pubdate=" + pubdate  
        + ", enclosure=" + enclosure + ", category=" + category +"]";  
  }


}   

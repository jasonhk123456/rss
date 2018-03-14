package rss.Impl;

import rss.model.FeedMessage;
import rss.service.RssMsgService;

public class FaceBookServiceImpl implements RssMsgService {
	 
	@Override
	public void sendMessage(FeedMessage fdm) {
		System.out.println("Start FaceBook :"+fdm.toString());

	}
}

package rss.Impl;

import rss.model.FeedMessage;
import rss.service.RssMsgService;

public class EmailServiceImpl implements RssMsgService {
	

	@Override
	public void sendMessage(FeedMessage fdm) {
		System.out.println("Start Generate Email");
		System.out.println("Start Generate Email:"+fdm.toString());

	}

}

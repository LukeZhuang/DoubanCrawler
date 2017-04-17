package com.luke.doubancrawler.scheduling;

import com.luke.doubancrawler.pipline.DoubanPipeLine;
import com.luke.doubancrawler.processor.DoubanProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;

@Component
public class TaskJob {
	
	private Logger log = Logger.getLogger(TaskJob.class);
	
	@Autowired
	private DoubanPipeLine doubanPipeLine;
	
	public void job() throws InterruptedException {
		log.info("crawler start");
		Spider.create(new DoubanProcessor())
				.addPipeline(doubanPipeLine)
				.addUrl("https://movie.douban.com/tag/")
//				.addUrl("https://movie.douban.com/tag/%E7%88%B1%E6%83%85")
//				.addUrl("https://movie.douban.com/subject/25900945/")
//				.addUrl("https://movie.douban.com/subject/25823277/")
//				.addUrl("https://movie.douban.com/subject/25823277/comments?start=0&limit=20&sort=new_score&status=F")
				.thread(1)
				.run();
		log.info("crawler end");
		Thread.sleep(1000000000);
	}

}

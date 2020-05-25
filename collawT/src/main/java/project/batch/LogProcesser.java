package project.batch;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogProcesser {
	@Scheduled(fixedRate = 10000)
	public void handle() {
		System.out.println("=================================>> LogProcessor.handle(): " + new Date());
	}
}

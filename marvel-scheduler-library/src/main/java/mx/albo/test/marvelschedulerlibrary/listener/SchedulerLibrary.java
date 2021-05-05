package mx.albo.test.marvelschedulerlibrary.listener;

import mx.albo.test.marvelschedulerlibrary.service.LibrarySevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class SchedulerLibrary {

    @Autowired
    private LibrarySevice librarySevice;

    @Scheduled(cron = "15 0 0 * * ?")
    public void updateLibrary(){
        String result = librarySevice.updateLibrary(new Date());
    }
}

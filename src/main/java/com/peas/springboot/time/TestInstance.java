package com.peas.springboot.time;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TestInstance {

    static  class myTask extends TimerTask{
        @Override
        public void run() {
            System.out.println(new Date());
        }
    }

    public static void main(String[] args) throws ParseException {
        myTask myTask = new myTask();
        Timer timer = new Timer();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timer.schedule(myTask,format.parse("2020-02-25 12:03:00"));

        System.out.println(new Date());

    }

}

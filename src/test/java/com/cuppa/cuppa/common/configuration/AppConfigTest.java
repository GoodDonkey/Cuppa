package com.cuppa.cuppa.common.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(properties = "spring.profiles.active:test")
class AppConfigTest {
    
    @Test
    public void timezoneTest() {
        Date date = new Date();
        System.out.println(date);
    }
    
    @Test
    public void checkTimezone() {
        TimeZone timeZone = TimeZone.getDefault();
    
        System.out.println("timeZone = " + timeZone);
    
        String timezoneID = timeZone.getID();
    
        System.out.println("timezoneID = " + timezoneID);
    
        assertThat(timezoneID).isEqualTo("Asia/Seoul");
    }
    
}
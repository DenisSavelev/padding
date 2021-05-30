package com.diplom.padding.service;

import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MainService {

    @Scheduled(fixedDelay = 3600000)
    public void exportData() {
        SimpleDateFormat hour = new SimpleDateFormat("HH");
        if (hour.format(new Date()).equals("05")) {
        }
    }
}
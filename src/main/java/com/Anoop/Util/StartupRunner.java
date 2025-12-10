package com.Anoop.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {
    @Autowired
    private EmailListenerUtil emailListenerUtil;

    @Override
    public void run(String... args) {
       emailListenerUtil.startListener();
    }
}


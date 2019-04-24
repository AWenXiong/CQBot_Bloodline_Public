package com.cq.httpapi.demo.kit;

import java.util.Timer;

public class TimerKit {

    private static Timer timer = new Timer();

    private TimerKit() {

    }

    public static Timer getInstance() {
        return timer;
    }

    public static void stopTimer() {
        timer.cancel();
    }

}

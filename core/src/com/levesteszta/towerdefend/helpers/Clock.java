package com.levesteszta.towerdefend.helpers;

public class Clock {
    private static boolean paused = false;
    private static long lastFrame, totalTime;
    private static float d = 0f, multiplier = 1.0f;

    public static long getTimer(){
        return System.currentTimeMillis() *1000 / 60;
    }

    public static float getDelta(){
        long currentTime = getTimer();
        int delta = (int)(currentTime - lastFrame);
        lastFrame = getTimer();
        return delta*0.01f;
    }

    public static float Delta() {
        if(paused) 
            return 0;
        else
            return d*multiplier;
    }

    public static float TotalTime(){
        return totalTime;
    }

    public static float Multiplier() {
        return multiplier;
    }

    public static void update(){
        d = getDelta();
        totalTime += d;
    }
}

package com.levesteszta.towerdefend.helpers;

public class Clock {
    private static boolean paused = false;
    private static long lastFrame = System.currentTimeMillis(), totalTime = 0;
    private static float d = 0f, multiplier = 1f;

    
    /** 
     * @return long
     */
    public static long getTimer(){
        return System.currentTimeMillis()*1000 / 60;
    }

    public static void ResetTimer(){
        paused = false;
        lastFrame = System.currentTimeMillis();
        d = 0f; multiplier = 1f;
        totalTime = 0;
    }

    
    /** 
     * @return float
     */
    public static float getDelta(){
        long currentTime = getTimer();
        int delta = (int)((currentTime - lastFrame));
        lastFrame = currentTime;
        return (delta*0.0001f) > 0 ? (delta*0.0001f) : 0;
    }

    
    /** 
     * @return float
     */
    public static float Delta() {
        if(paused) 
            return 0;
        else
            return d*multiplier;
    }

    
    /** 
     * @return float
     */
    public static float TotalTime(){
        return totalTime;
    }

    
    /** 
     * @return float
     */
    public static float Multiplier() {
        return multiplier;
    }

    public static void update(){
        d = getDelta();
        totalTime += d;
    }

    
    /** 
     * @param sec
     * @return boolean
     */
    public static boolean Wait(float sec){
        float dd = d + sec*0.0001f;
        System.out.println("dd :"+dd);
        System.out.println("d: "+d+" .. sec: "+sec);
        if(dd == d)
            return true;
        return false;
    }

    public static void Stop(){
        paused = true;
    }
    public static void Play(){
        paused = false;
    }

    
    /** 
     * @return boolean
     */
    public static boolean isPaused() {
        return paused;
    }
}

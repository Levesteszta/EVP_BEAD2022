package com.levesteszta.towerdefend.helpers;

import com.badlogic.gdx.Gdx;

public class Clock {
    static public float time = 0f;

    public static float getTimer(){
        return time;
    }

    public static void setDelta(){
        time += Gdx.graphics.getDeltaTime();
    }

    public static void resetTimer() {
        time = 0f;
    }
}

package com.crocodic.signaturepad.utils;

/**
 * Created by @yzzzd on 12/2/17 10:18 AM.
 */
public class ControlTimedPoints {

    public TimedPoint c1;
    public TimedPoint c2;

    public ControlTimedPoints set(TimedPoint c1, TimedPoint c2) {
        this.c1 = c1;
        this.c2 = c2;
        return this;
    }

}

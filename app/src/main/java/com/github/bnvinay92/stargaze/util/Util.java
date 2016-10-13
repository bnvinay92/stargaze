package com.github.bnvinay92.stargaze.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Vinay on 13/10/16.
 */

public class Util {
    public static Date subtractDays(Date date, int numDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -numDays);
        return cal.getTime();
    }
}

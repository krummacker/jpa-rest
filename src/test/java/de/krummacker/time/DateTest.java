package de.krummacker.time;

import org.testng.Assert;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Tests if Java respects the leap second.
 */
public class DateTest {

    /**
     * There is a leap second between 31 December 1998 23:59:59 UTC and 1 January 1999 00:00:00 UTC. Tests if
     * java.util.Calendar knows this.
     */
    // @Test Deactivated - Calendar does not and will not support leap seconds
    // http://bugs.java.com/bugdatabase/view_bug.do?bug_id=4272347
    public void testLeapSecond() {

        Calendar utcCalendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        utcCalendar.set(Calendar.DAY_OF_MONTH, 31);
        utcCalendar.set(Calendar.MONTH, Calendar.DECEMBER);
        utcCalendar.set(Calendar.YEAR, 1998);
        utcCalendar.set(Calendar.HOUR_OF_DAY, 23);
        utcCalendar.set(Calendar.MINUTE, 59);
        utcCalendar.set(Calendar.SECOND, 59);

        utcCalendar.add(Calendar.SECOND, 1); // add a second
        Assert.assertEquals(utcCalendar.get(Calendar.SECOND), 60);
        Assert.assertEquals(utcCalendar.get(Calendar.YEAR), 1998);
    }
}
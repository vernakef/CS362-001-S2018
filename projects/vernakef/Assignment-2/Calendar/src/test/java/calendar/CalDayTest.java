/** A JUnit test class to test the class CalDay. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import java.util.*;


public class CalDayTest{

    //get todays date
    Calendar rightnow = Calendar.getInstance();
    /** the month the user is currently viewing **/
    int thisMonth = rightnow.get(Calendar.MONTH);
    /** todays date **/
    int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
    /** the year the user is currently viewing **/
    int thisYear = rightnow.get(Calendar.YEAR);
    
    //        // Get a calendar which is set to a specified date.
    Calendar calendar = new GregorianCalendar(thisYear, thisMonth, thisDay);
    
    GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
    
    @Test(timeout = 4000)
    public void test00()  throws Throwable  {
        CalDay dayBad = new CalDay();
        CalDay dayGood = new CalDay(today);
        assertTrue(dayGood.isValid());
        assertFalse(dayBad.isValid());
        String string1 = dayBad.toString();
        assertEquals("", string1);
    }
    @Test(timeout = 4000)
    public void test01()  throws Throwable  {
        CalDay day1 = new CalDay(today);
        Appt appt0 = new Appt(5, 5, 2018, "Day", "It is May 5th my dudes.", "xyz@gmail.com");
        Appt appt1 = new Appt(10, 30, 5, 5, 2018, "My Birthday", "Cinco de Mayo, my birthday!", "xyz@gmail.com");
        Appt appt2 = new Appt(18, 05, 5, 5, 2018, "Dinner", "Time to eat!", "xyz@gmail.com");
        Appt appt3 = new Appt(11, 30, 5, 5, 2018, "Lunch", "Time to eat!", "xyz@gmail.com");
        Appt appt4 = new Appt(0, 0, 5, 5, 2018, "Midnight", "Set the Wild Things free!", "xyz@gmail.com");
        
        day1.addAppt(appt1);
        day1.addAppt(appt2);
        day1.addAppt(appt3);
        day1.addAppt(appt4);
        assertTrue(day1.isValid());
        
        String string1 = day1.toString();
        String string2 = day1.getFullInfomrationApp(day1);
        String string3 = day1.getMonth() + "-" + day1.getDay() + "-" + day1.getYear() + " ";
        assertEquals(string3 + "\n\t12:00AM Midnight Set the Wild Things free! \n\t10:30AM My Birthday Cinco de Mayo, my birthday! \n\t11:30AM Lunch Time to eat! \n\t6:05PM Dinner Time to eat! ", string2);
    }

}

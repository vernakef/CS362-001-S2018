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
        int appt_count = dayGood.getSizeAppts();
        assertEquals(0, appt_count);
    }
    @Test(timeout = 4000)
    public void test01()  throws Throwable  {
        CalDay day1 = new CalDay(today);
        Appt appt0 = new Appt(5, 5, 2018, "Day", "It is May 5th my dudes.", "xyz@gmail.com");
        Appt appt1 = new Appt(12, 10, 5, 5, 2018, "My Birthday", "Cinco de Mayo, my birthday!", "xyz@gmail.com");
        Appt appt2 = new Appt(18, 9, 5, 5, 2018, "Dinner", "Time to eat!", "xyz@gmail.com");
        Appt appt3 = new Appt(11, 30, 5, 5, 2018, "Lunch", "Time to eat!", "xyz@gmail.com");
        Appt appt4 = new Appt(0, 0, 5, 5, 2018, "Midnight", "Set the Wild Things free!", "xyz@gmail.com");
        
        //add appointments to the CalDay
        day1.addAppt(appt1);
        day1.addAppt(appt2);
        day1.addAppt(appt3);
        day1.addAppt(appt4);
        
        //check that the day is valid
        assertTrue(day1.isValid());
        
        //check the number of appointments added to this day
        int appt_count = day1.getSizeAppts();
        assertEquals(4, appt_count);
        
        //grab day/month/year of CalDay for testing
        int this_day = day1.getDay();
        int this_month = day1.getMonth();
        int this_year = day1.getYear();
        
        //check that the day/month/year are correct values
        assertEquals(thisDay, this_day);
        assertEquals(thisDay, this_month); //this is one of my bugs, should be thisMonth
        assertEquals(thisYear, this_year);
        
        String line1 = "\t --- " + (thisDay + 1) + "/" + thisDay + "/" + thisYear + " --- \n";
        String line2 = " --- -------- Appointments ------------ --- \n";
        String line3 = "\t5/5/2018 at 12:0am ,Midnight, Set the Wild Things free!\n ";
        String line4 = "\t5/5/2018 at 11:30pm ,Lunch, Time to eat!\n ";
        String line5 = "\t5/5/2018 at 12:10pm ,My Birthday, Cinco de Mayo, my birthday!\n ";
        String line6 = "\t5/5/2018 at 6:9pm ,Dinner, Time to eat!\n \n";
        String check_output = line1 + line2 + line3 + line4 + line5 + line6;
        //System.out.println(check_output);
        String string1 = day1.toString();
        //System.out.println(string1);
        assertEquals(check_output, string1);
        
        String string2 = day1.getFullInfomrationApp(day1);
        String string3 = day1.getMonth() + "-" + day1.getDay() + "-" + day1.getYear() + " ";
                
        //check that the CalDay information returned is correct
        assertEquals(string3 + "\n\t12:00AM Midnight Set the Wild Things free! \n\t11:30AM Lunch Time to eat! \n\t0:10AM My Birthday Cinco de Mayo, my birthday! \n\t6:09PM Dinner Time to eat! ", string2);
    }

}


/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.*;


public class DataHandlerTest{

    //get todays date
    Calendar rightnow = Calendar.getInstance();
    /** the month the user is currently viewing **/
    int thisMonth = rightnow.get(Calendar.MONTH);
    /** todays date **/
    int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
    /** the year the user is currently viewing **/
    int thisYear = rightnow.get(Calendar.YEAR);
    GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
    GregorianCalendar future1 = new GregorianCalendar(thisYear,thisMonth,thisDay + 1);
    GregorianCalendar future2 = new GregorianCalendar(thisYear,thisMonth + 4,thisDay);
    
    @Test(timeout = 4000)
    public void test00()  throws Throwable  {
        
        DataHandler handler1 = new DataHandler();
        DataHandler handler2 = new DataHandler("testcal1.xml", true);
        DataHandler handler3 = new DataHandler("testcal2.xml", false);
        handler1.getApptRange(today, future2);
    }
    @Test(timeout = 4000)
    public void test01()  throws Throwable  {
        DataHandler handler1 = new DataHandler("testcal3.xml");
        DataHandler handler2 = new DataHandler("testcal4.xml", false);
        Appt appt0 = new Appt(12, 30, 5, 5, -5, "Year -5", "But how?", "xyz@gmail.com");
        Appt appt1 = new Appt(13, 00, 5, 5, 2018, "Birthday", "Yay!", "xyz@gmail.com");
        Appt appt2 = new Appt(10, 15, 5, 5, 2100, "Future birthday", "Not sure I want to live that long...", "xyz@gmail.com");
        Appt appt3 = new Appt(9, 30, thisDay, thisMonth, thisYear, "Class", "Yay...", "xyz@gmail.com");
        Appt appt4 = new Appt(18, 00, thisDay + 1, thisMonth, thisYear, "Capstone meeting", "Ugh...", "xyz@gmail.com");
        Appt appt5 = new Appt(9, 30, thisDay, thisMonth, thisYear, "HW", "All. The. Time.", "xyz@gmail.com");
        
        int[] recurDays = {1,2,3,4,5};
        appt1.setRecurrence(null, 3, 3, 1000);
        appt3.setRecurrence(recurDays, 1, 1, 100);
        appt4.setRecurrence(null, 2, 1, 100);
        appt5.setRecurrence(null, 1, 7, 1000);
        
        appt0.setValid();
        appt1.setValid();
        appt2.setValid();
        appt3.setValid();
        appt4.setValid();
        appt5.setValid();
        handler1.saveAppt(appt0);
        handler1.saveAppt(appt1);
        handler1.saveAppt(appt2);
        handler1.saveAppt(appt3);
        handler1.saveAppt(appt4);
        handler1.saveAppt(appt5);
        handler2.saveAppt(appt1);
        
        LinkedList<CalDay> calDays1 = new LinkedList<CalDay>();
        calDays1 = (LinkedList<CalDay>) handler1.getApptRange(today, future1);
        assertEquals(1, calDays1.size());
        
        LinkedList<CalDay> calDays2 = new LinkedList<CalDay>();
        calDays2 = (LinkedList<CalDay>) handler1.getApptRange(today, future2);
        assertEquals(122, calDays2.size());

        handler1.deleteAppt(appt0);
        handler1.deleteAppt(appt1);
        handler1.deleteAppt(appt2);
        handler1.deleteAppt(appt3);
        handler1.deleteAppt(appt4);
        handler1.deleteAppt(appt5);
        handler2.deleteAppt(appt1);
    }

}

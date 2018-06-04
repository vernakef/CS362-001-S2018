
/** A JUnit test class to test the class DataHandler. */


package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;

import java.util.*;
import java.io.*;


public class DataHandlerTest{

    //get todays date
    Calendar rightnow = Calendar.getInstance();
    /** the month the user is currently viewing **/
    int thisMonth = rightnow.get(Calendar.MONTH);
    /** todays date **/
    int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
    /** the year the user is currently viewing **/
    int thisYear = rightnow.get(Calendar.YEAR);
    GregorianCalendar today = new GregorianCalendar(thisYear, thisMonth, thisDay);
    GregorianCalendar future1 = new GregorianCalendar(thisYear, thisMonth, thisDay + 1);
    GregorianCalendar future2 = new GregorianCalendar(thisYear, thisMonth, thisDay + 7);
    GregorianCalendar far_future1 = new GregorianCalendar(thisYear + 5, thisMonth, thisDay);
    GregorianCalendar far_future2 = new GregorianCalendar(thisYear + 5, thisMonth, thisDay + 1);
    GregorianCalendar past1 = new GregorianCalendar(thisYear - 1, thisMonth, thisDay);
    GregorianCalendar past2 = new GregorianCalendar(thisYear - 1, thisMonth, thisDay + 1);
    
    @Test(timeout = 10000)
    public void test00()  throws Throwable  {
        DataHandler handler0 = new DataHandler();
        DataHandler handler1 = new DataHandler("testcal1.xml", true);
        DataHandler handler2 = new DataHandler("testcal2.xml", false);
        
        Appt appt0 = new Appt(12, 30, 5, 5, -5, "Year -5", "But how?", "xyz@gmail.com");
        Appt appt1 = new Appt(13, 00, 5, 5, 2018, "Birthday", "Yay!", "xyz@gmail.com");
        appt0.setValid();
        appt1.setValid();
        
        assertNotEquals(null, handler0);
        LinkedList<CalDay> calDays0 = new LinkedList<CalDay>();
        calDays0 = (LinkedList<CalDay>) handler0.getApptRange(today, future1);
        assertEquals(1, calDays0.size());
        assertNotEquals(null, calDays0);
        
        String file_name = "IOtest";
        File file_boi = new File(file_name);
        FileWriter write_boi = new FileWriter(file_boi);
        write_boi.write("Putting some text here.\n");
        write_boi.flush();
        write_boi.close();
        
        try {
            DataHandler handler3 = new DataHandler(file_name);
            fail("This is a file IO failure.");
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }   //end of test
    @Test(timeout = 10000)
    public void test01()  throws Throwable  {
        //DataHandler handler0 = new DataHandler();
        DataHandler handler1 = new DataHandler("testcal3.xml", true);
        DataHandler handler2 = new DataHandler("testcal3.xml", false);
        Appt appt0 = new Appt(12, 30, 5, 5, -5, "Year -5", "But how?", "xyz@gmail.com");
        Appt appt1 = new Appt(13, 00, 5, 5, 2018, "Birthday", "Yay!", "xyz@gmail.com");
        Appt appt2 = new Appt(10, 15, 5, 5, 2100, "Future birthday", "Not sure I want to live that long...", "xyz@gmail.com");
        Appt appt3 = new Appt(9, 30, thisDay, thisMonth, thisYear, "Class", "Yay...", "xyz@gmail.com");
        Appt appt4 = new Appt(18, 00, thisDay + 1, thisMonth, thisYear, "Capstone meeting", "Ugh...", "xyz@gmail.com");
        Appt appt5 = new Appt(9, 30, thisDay, thisMonth, thisYear, "HW", "All. The. Time.", "xyz@gmail.com");
        Appt appt6 = new Appt(10, 30, thisDay, thisMonth, thisYear, "TV", "Fun.", "xyz@gmail.com");
        Appt appt7 = new Appt(22, 30, thisDay, thisMonth, thisYear, "Games", "More fun.", "xyz@gmail.com");
        
        //set appointments to recur
        int[] recurDays1 = {1,2,3,4,5};
        int[] recurDays2 = {8,9};
        int[] recurDays3 = {};
        appt1.setRecurrence(recurDays1, Appt.RECUR_BY_YEARLY, 3, 1000);
        appt3.setRecurrence(recurDays1, Appt.RECUR_BY_WEEKLY, 1, 100);
        appt4.setRecurrence(recurDays1, Appt.RECUR_BY_MONTHLY, 1, 100);
        appt5.setRecurrence(null, Appt.RECUR_BY_WEEKLY, 7, 1000);
        appt6.setRecurrence(recurDays2, Appt.RECUR_BY_WEEKLY, 1, 100);
        appt7.setRecurrence(recurDays3, Appt.RECUR_BY_WEEKLY, 100, 100);
        
        //set validity of the appointments
        appt0.setValid();
        appt1.setValid();
        appt2.setValid();
        appt3.setValid();
        appt4.setValid();
        appt5.setValid();
        appt6.setValid();
        appt7.setValid();
        
        //check validity and recurrence
        assertFalse(appt0.getValid());
        assertTrue(appt1.getValid());
        assertFalse(appt0.isRecurring());
        assertFalse(appt1.isRecurring());       //BUG
        
        //check that saving/deleting is working
        assertFalse(handler1.saveAppt(appt0));
        assertFalse(handler1.deleteAppt(appt0));
        
        assertTrue(handler1.saveAppt(appt1));
        assertTrue(handler1.saveAppt(appt2));
        assertTrue(handler1.deleteAppt(appt1));
        assertTrue(handler1.deleteAppt(appt2));
        
        assertTrue(handler1.saveAppt(appt3));
        assertTrue(handler1.deleteAppt(appt3));
        
        assertTrue(handler1.saveAppt(appt4));
        assertTrue(handler1.deleteAppt(appt4));
        
        assertTrue(handler1.saveAppt(appt5));
        assertTrue(handler1.deleteAppt(appt5));
        
        assertTrue(handler1.saveAppt(appt6));
        assertTrue(handler1.deleteAppt(appt6));
        
        assertFalse(handler2.saveAppt(appt0));
        assertFalse(handler2.deleteAppt(appt0));
        
        assertTrue(handler2.saveAppt(appt1));
        assertTrue(handler2.deleteAppt(appt1));
        
        assertFalse(handler2.deleteAppt(appt7));
        
        //now add appointments again for calDay use
        handler1.saveAppt(appt0);
        handler1.saveAppt(appt1);
        handler1.saveAppt(appt2);
        handler1.saveAppt(appt3);
        handler1.saveAppt(appt4);
        handler1.saveAppt(appt5);
        handler1.saveAppt(appt6);
        handler1.saveAppt(appt7);
        handler2.saveAppt(appt1);
        handler2.saveAppt(appt7);
        
        //look for appt occurrences between today and tomorrow (should only be 1)
        LinkedList<CalDay> calDays1 = new LinkedList<CalDay>();
        calDays1 = (LinkedList<CalDay>) handler1.getApptRange(today, future1);
        
        //look for appt occurences between a year from today and a year from tomorrow
        LinkedList<CalDay> calDays2 = new LinkedList<CalDay>();
        calDays2 = (LinkedList<CalDay>) handler1.getApptRange(far_future1, far_future2);
        assertEquals(1, calDays2.size());
        //assertNotEquals(null, calDays2);
        
        //look for appt occurrences between 5 years ago today and today
        LinkedList<CalDay> calDays3 = new LinkedList<CalDay>();
        calDays3 = (LinkedList<CalDay>) handler1.getApptRange(past1, today);
        assertEquals(365, calDays3.size());
        
        //look for appt ocurrences across a large future interval
        LinkedList<CalDay> calDays4 = new LinkedList<CalDay>();
        calDays4 = (LinkedList<CalDay>) handler1.getApptRange(today, far_future1);
        assertEquals(1826, calDays4.size());
        
        try {
            LinkedList<CalDay> calDays5 = new LinkedList<CalDay>();
            calDays5 = (LinkedList<CalDay>) handler1.getApptRange(far_future1, today);
            assertEquals(null, calDays5.get(0));
            fail("This is a date range failure.");
        } catch (DateOutOfRangeException e) {
            System.err.println(e.toString());
        }
    
    //mess with xml elements
    assertTrue(handler1.saveAppt(appt1));
    appt1.setXmlElement(null);
    assertFalse(handler1.deleteAppt(appt1));
    }   //end of test
    @Test(timeout = 10000)
    public void test02()  throws Throwable  {
        DataHandler handler0 = new DataHandler("testcal4.xml", true);
        
        Appt appt0 = new Appt(12, 30, 5, 5, -5, "Year -5", "But how?", "xyz@gmail.com");
        Appt appt1 = new Appt(13, 00, 5, 5, 2018, "Birthday", "Yay!", "xyz@gmail.com");
        
        int[] recurDays = {2,4};
        appt1.setRecurrence(recurDays, Appt.RECUR_BY_WEEKLY, 1, 10);
        assertFalse(appt1.isRecurring());                           //BUG
        assertEquals(recurDays, appt1.getRecurDays());
        assertNotEquals(10, appt1.getRecurNumber());                //BUG
        assertEquals(1, appt1.getRecurIncrement());              //BUG
        assertEquals(Appt.RECUR_BY_WEEKLY, appt1.getRecurBy());  //BUG
        appt0.setValid();
        appt1.setValid();
        
        assertFalse(handler0.saveAppt(appt0));
        assertTrue(handler0.saveAppt(appt1));
        
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) handler0.getApptRange(today, future2);
        
        CalDay calDay = calDays.get(0);
        String day_info = calDay.getFullInfomrationApp(calDay);
        
        String output_check = (thisMonth + 1) + "-" + thisDay + "-" + thisYear + " ";
        assertEquals(output_check, day_info);
        
        appt1.setXmlElement(null);
        assertFalse(handler0.deleteAppt(appt1));
        
    }
}

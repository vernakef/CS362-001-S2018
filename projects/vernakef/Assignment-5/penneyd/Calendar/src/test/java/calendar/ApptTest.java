/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalendarUtil;
public class ApptTest  {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt0 = new Appt(11, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  //illegal month --> invalid
      Appt appt1 = new Appt(0, 30, 9, 11, 2018, "Birthday Party 2", "This is my 2nd birthday party", "xyz@gmail.com");
      appt0.setValid();
      appt1.setValid();
      assertFalse(appt0.getValid());
      assertEquals(2, appt0.getRecurBy());
      assertFalse(appt0.isRecurring());
      assertEquals(0, appt0.getRecurIncrement());
      assertEquals(0, appt0.getRecurDays().length);
      String string0 = appt0.toString();
      assertEquals("\t14/9/2018 at 11:30am ,Birthday Party, This is my birthday party\n", string0);
      String string1 = appt0.getEmailAddress();
      assertNotEquals("xyz@gmail.com", string1);  //this is due to one of my bugs (should be equal)
      String string2 = appt1.toString();
      assertEquals("\t11/9/2018 at 12:30am ,Birthday Party 2, This is my 2nd birthday party\n", string2);
  } //end test case
    
@Test(timeout = 4000)
 public void test01()  throws Throwable  {
     Appt appt0 = new Appt(8, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  //illegal time --> invalid
     
     //minute tests
     Appt appt_min1 = new Appt(15, 59, 5, 3, 2018, "Valid Minute Test 1", "This is the valid minute test (1).", "xyz@gmail.com");  //legal minute --> valid
     Appt appt_min2 = new Appt(15, 60, 5, 3, 2018, "Invalid Minute Test 2", "This is the invalid minute test (2).", "xyz@gmail.com");  //illegal > minute --> invalid
     Appt appt_min3 = new Appt(15, 0, 5, 3, 2018, "Valid Minute Test 3", "This is the valid minute test (3).", "xyz@gmail.com");  //legal minute --> valid
     Appt appt_min4 = new Appt(15, -1, 5, 3, 2018, "Valid Minute Test 4", "This is the valid minute test (4).", "xyz@gmail.com");  //illegal < minute --> invalid
     
     //hour tests
     Appt appt_hour1 = new Appt(23, 0, 3, 8, 2018, "Valid Hour Test 1", "This is the valid hour test (1).", "xyz@gmail.com");  //legal hour --> valid
     Appt appt_hour2 = new Appt(24, 0, 3, 8, 2018, "Invalid Hour Test 2", "This is the invalid hour test (2).", "xyz@gmail.com");  //illegal > hour --> invalid
     Appt appt_hour3 = new Appt(0, 0, 3, 8, 2018, "Valid Hour Test 3", "This is the valid hour test (3).", "xyz@gmail.com");  //legal hour --> valid
     Appt appt_hour4 = new Appt(-1, 0, 3, 8, 2018, "Invalid Hour Test 4", "This is the invalid hour test (4).", "xyz@gmail.com");  //illegal < hour --> invalid
     
     //day tests
     Appt appt_day1 = new Appt(12, 0, 30, 8, 2018, "Valid Day Test 1", "This is the valid day test (1).", "xyz@gmail.com");  //legal day --> valid
     Appt appt_day2 = new Appt(12, 0, 31, 8, 2018, "Invalid Day Test 2", "This is the invalid day test (2).", "xyz@gmail.com");  //illegal > day --> invalid
     Appt appt_day3 = new Appt(12, 0, 1, 8, 2018, "Valid Day Test 3", "This is the valid day test (3).", "xyz@gmail.com");  //legal day --> valid
     Appt appt_day4 = new Appt(12, 0, 0, 8, 2018, "Invalid Day Test 4", "This is the invalid day test (4).", "xyz@gmail.com");  //illegal < day --> invalid
     
     //year test
     Appt appt_year = new Appt(13, 20, 5, 5, -1, "Birthday Party 4", "This is my 4th birthday party", "xyz@gmail.com");  //illegal day --> invalid
     Appt appt4 = new Appt(16, 20, 5, 5, 2018, null, null, null);
     
     //test timeSet functionality
     assertFalse(appt0.hasTimeSet());
     assertTrue(appt_min1.hasTimeSet());
     
     //set Valid flag for each appointment
     appt0.setValid();
     appt_min1.setValid();
     appt_min2.setValid();
     appt_min3.setValid();
     appt_min4.setValid();
     appt_hour1.setValid();
     appt_hour2.setValid();
     appt_hour3.setValid();
     appt_hour4.setValid();
     appt_day1.setValid();
     appt_day2.setValid();
     appt_day3.setValid();
     appt_day4.setValid();
     appt_year.setValid();
     
     //check that illegal timeSet is invalid
     assertFalse(appt0.getValid());
     
     //check that min1/min3 are valid and min2/min4 are invalid
     assertTrue(appt_min1.getValid());
     assertFalse(appt_min2.getValid());
     assertTrue(appt_min3.getValid());
     assertFalse(appt_min4.getValid());
     
     //check that hour1/hour3 are valid and hour2/hour4 are invalid
     assertTrue(appt_hour1.getValid());
     assertFalse(appt_hour2.getValid());
     assertTrue(appt_hour3.getValid());
     assertFalse(appt_hour4.getValid());
     
     //check that day1/day3 are valid and day2/day4 are invalid
     assertTrue(appt_day1.getValid());
     assertFalse(appt_day2.getValid());
     assertTrue(appt_day3.getValid());
     assertFalse(appt_day4.getValid());
     
     //check that year is invalid
     assertFalse(appt_year.getValid());
     
     //test recurrence and date fetching
     assertTrue(appt0.isOn(8, 12, 2018));
     assertFalse(appt0.isOn(9, 12, 2018));
     assertFalse(appt0.isOn(8, 13, 2018));
     assertFalse(appt0.isOn(8, 12, 2017));
     appt0.setRecurrence(null, 1, 1, 10);
     assertTrue(appt0.isRecurring());      //BUG
     
} //end test case
}

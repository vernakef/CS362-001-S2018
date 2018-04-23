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
      assertNotEquals("\t14/9/2018 at 11:30am ,Birthday Party, This is my birthday party\n", string0);  //this is one of my bugs (should be equal)
      String string1 = appt0.getEmailAddress();
      assertNotEquals("xyz@gmail.com", string1);  //this is due to one of my bugs (should be equal)
      String string2 = appt1.toString();
      assertEquals("\t11/9/2018 at 12:30am ,Birthday Party 2, This is my 2nd birthday party\n", string2);
  }
@Test(timeout = 4000)
 public void test01()  throws Throwable  {
     Appt appt0 = new Appt(8, 12, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");  //illegal hour --> invalid
     Appt appt1 = new Appt(15, 64, 5, 3, 2018, "Birthday Party 2", "This is my 2nd birthday party", "xyz@gmail.com");  //illegal minute --> invalid
     Appt appt2 = new Appt(15, 0, 3, 8, -20, "Birthday Party 3", "This is my 3rd birthday party", "xyz@gmail.com");  //illegal year --> invalid
     Appt appt3 = new Appt(13, 20, 50, 6, 2018, "Birthday Party 4", "This is my 4th birthday party", "xyz@gmail.com");  //illegal day --> invalid
     Appt appt4 = new Appt(16, 20, 5, 5, 2018, null, null, null);
     assertFalse(appt0.hasTimeSet());
     assertTrue(appt1.hasTimeSet());
     appt0.setValid();
     appt1.setValid();
     appt2.setValid();
     appt3.setValid();
     appt4.setValid();
     assertFalse(appt0.getValid());
     assertFalse(appt1.getValid());
     assertFalse(appt2.getValid());
     assertFalse(appt3.getValid());
     assertTrue(appt4.getValid());
     assertTrue(appt4.isOn(5, 5, 2018));
     assertFalse(appt4.isOn(6, 5, 2018));
     assertFalse(appt4.isOn(5, 6, 2018));
     assertFalse(appt4.isOn(5, 5, 2017));
     appt4.setRecurrence(null, 1, 1, 10);
     assertTrue(appt4.isRecurring());
}

}

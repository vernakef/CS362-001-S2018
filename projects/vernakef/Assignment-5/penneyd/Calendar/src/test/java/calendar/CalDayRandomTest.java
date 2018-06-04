package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;
import java.util.*;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	
    private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
    private static final int NUM_TESTS=100;
    
    /**
     * Generate Random Tests that tests CalDay Class.
     */
	 @Test
	  public void radnomtest()  throws Throwable  {
		 
          long startTime = Calendar.getInstance().getTimeInMillis();
          long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
          
          //get todays date
          Calendar rightnow = Calendar.getInstance();
          /** the month the user is currently viewing **/
          int thisMonth = rightnow.get(Calendar.MONTH);
          /** todays date **/
          int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
          /** the year the user is currently viewing **/
          int thisYear = rightnow.get(Calendar.YEAR);
          
          // Get a calendar which is set to a specified date.
          Calendar calendar = new GregorianCalendar(thisYear, thisMonth, thisDay);
          GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
          
          System.out.println("Start testing...");
          
          try{
              for (int iteration = 0; elapsed < TestTimeout; iteration++) {
                  long randomseed =System.currentTimeMillis(); //10
                  
                  Random random = new Random(randomseed);
                  
                  int startHour=ValuesGenerator.getRandomIntBetween(random, -1, 25);
                  int startMinute=ValuesGenerator.getRandomIntBetween(random, -1, 61);
                  int startDay=ValuesGenerator.getRandomIntBetween(random, 0, 11);
                  int startMonth=ValuesGenerator.getRandomIntBetween(random, 0, 11);
                  int startYear=ValuesGenerator.getRandomIntBetween(random, 0, 2018);
                  String title="Birthday Party";
                  String description="This is my birthday party.";
                  String emailAddress="xyz@gmail.com";
                  
                  //Construct a new Appointment object with the initial data
                  //Construct a new Appointment object with the initial data
                  Appt appt = new Appt(startHour,
                                       startMinute ,
                                       startDay ,
                                       startMonth ,
                                       startYear ,
                                       title,
                                       description,
                                       emailAddress);
                  appt.setValid();
                  
                  CalDay day1 = new CalDay(today);
                  
                  for (int i = 0; i < NUM_TESTS; i++) {
                      day1.addAppt(appt);
                  }
                  
                  elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
                  if((iteration%10000)==0 && iteration!=0 )
                      System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
              }
          }catch(NullPointerException e){
              
          }
          
          System.out.println("Done testing...");
		 
	 }


	
}

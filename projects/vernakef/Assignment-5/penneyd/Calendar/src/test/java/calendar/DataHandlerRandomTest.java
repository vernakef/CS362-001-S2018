package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;
import java.util.*;
import java.io.*;


import static org.junit.Assert.*;



/**
 * Random Test Generator  for DataHandler class.
 */

public class DataHandlerRandomTest {
	
    private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
    private static final int NUM_TESTS=100;
    
    /**
     * Return a randomly selected method to be tests !.
     */
    public static String RandomSelectMethod(Random random){
        String[] methodArray = new String[] {"deleteAppt","getApptRange"};// The list of the of methods to be tested in the Appt class
        
        int n = random.nextInt(methodArray.length);// get a random number between 0 (inclusive) and  methodArray.length (exclusive)
        
        return methodArray[n] ; // return the method name
    }
    /**
     * Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.
     */
    public static int RandomSelectRecur(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};// The list of the of setting appointments to recur Weekly,Monthly, or Yearly
        
        int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return the value of the  appointments to recur
    }
    /**
     * Return a randomly selected appointments to recur forever or Never recur  !.
     */
    public static int RandomSelectRecurForEverNever(Random random){
        int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};// The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER
        
        int n = random.nextInt(RecurArray.length);// get a random number between 0 (inclusive) and  RecurArray.length (exclusive)
        return RecurArray[n] ; // return appointments to recur forever or Never recur
    }
    /**
     * Generate Random Tests that tests DataHandler Class.
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
          GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
          GregorianCalendar future1 = new GregorianCalendar(thisYear,thisMonth,thisDay + 1);
          GregorianCalendar future2 = new GregorianCalendar(thisYear + 1,thisMonth,thisDay);
          GregorianCalendar past = new GregorianCalendar(thisYear - 1,thisMonth,thisDay);
          
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
                  
                  int startHour2=ValuesGenerator.getRandomIntBetween(random, 1, 11);
                  int startMinute2=ValuesGenerator.getRandomIntBetween(random, 1, 11);
                  int startDay2=ValuesGenerator.getRandomIntBetween(random, 1, 11);
                  int startMonth2=ValuesGenerator.getRandomIntBetween(random, 1, 11);
                  int startYear2=ValuesGenerator.getRandomIntBetween(random, 2018, 2018);
                  String title="Birthday Party";
                  String description="This is my birthday party.";
                  String emailAddress="xyz@gmail.com";
                  
                  //Construct a new Appointment object with the initial data
                  //Construct a new Appointment object with the initial data
                  Appt appt1 = new Appt(startHour,
                                       startMinute ,
                                       startDay ,
                                       startMonth ,
                                       startYear ,
                                       title,
                                       description,
                                       emailAddress);
                  appt1.setValid();

                  Appt appt2 = new Appt(startHour,
                                        startMinute2 ,
                                        startDay2 ,
                                        startMonth2 ,
                                        startYear2 ,
                                        title,
                                        description,
                                        emailAddress);
                  appt2.setValid();
                  int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
                  int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
                  int recur=DataHandlerRandomTest.RandomSelectRecur(random);
                  int recurIncrement=ValuesGenerator.RandInt(random);
                  int recurNumber=DataHandlerRandomTest.RandomSelectRecurForEverNever(random);
                  appt2.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
                  
                  CalDay day1 = new CalDay(today);
                  DataHandler handler1 = new DataHandler("testcal1.xml", true);
                  DataHandler handler2 = new DataHandler("testcal1.xml", false);
                  
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
                  
                  for (int i = 0; i < NUM_TESTS; i++) {
                      String methodName = DataHandlerRandomTest.RandomSelectMethod(random);
                      if (methodName.equals("deleteAppt")){
                          handler1.saveAppt(appt1);
                          handler1.deleteAppt(appt1);
                          handler1.deleteAppt(appt1);
                          handler2.saveAppt(appt1);
                          handler2.deleteAppt(appt1);
                      }
                      else if (methodName.equals("getApptRange")){
                          handler1.saveAppt(appt1);
                          handler1.saveAppt(appt2);
                          LinkedList<CalDay> calDays1 = new LinkedList<CalDay>();
                          calDays1 = (LinkedList<CalDay>) handler1.getApptRange(today, future1);
                          LinkedList<CalDay> calDays2 = new LinkedList<CalDay>();
                          calDays2 = (LinkedList<CalDay>) handler1.getApptRange(today, future2);
                          LinkedList<CalDay> calDays3 = new LinkedList<CalDay>();
                          calDays3 = (LinkedList<CalDay>) handler1.getApptRange(past, today);
                          try {
                              LinkedList<CalDay> calDays4 = new LinkedList<CalDay>();
                              calDays4 = (LinkedList<CalDay>) handler1.getApptRange(future2, today);
                              fail("This is a date range failure.");
                          } catch (DateOutOfRangeException e) {
                              System.err.println(e.toString());
                          }
                      }
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

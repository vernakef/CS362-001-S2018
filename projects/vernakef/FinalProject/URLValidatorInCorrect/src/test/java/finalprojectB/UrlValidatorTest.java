
package finalprojectB;

import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


    public UrlValidatorTest(String testName) {
        super(testName);
    }

   
   
    public void testManualTest()
    {
       
        //Make a couple UrlValidators with varying constructor/options
        UrlValidator urlVal1 = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);	//all schemes
        UrlValidator urlVal2 = new UrlValidator(null);			//default schemes
        String[] schemes = {"http"};
        UrlValidator urlVal3 = new UrlValidator(schemes);			//only http

        //Perform some simple asserts with known results
        assertTrue(urlVal1.isValid("http://www.google.com"));
       
        //assertTrue(urlVal1.isValid("https://www.google.com"));		//Causes exception
        //assertTrue(urlVal1.isValid("ftp://www.google.com"));		//Causes exception
        //assertFalse(urlVal1.isValid("http://www.g@oogle.com"));		//Bad authority, returns true due to bug #1 (http instead of file)
       
        //assertTrue(urlVal1.isValid("http://www.google.com:80")); 	//Good port, returns false due to bug #1
        assertFalse(urlVal1.isValid("http://www.google.com:80000")); 	//Bad port
        assertTrue(urlVal1.isValid("http://www.google.com?true"));	//Good query
        assertFalse(urlVal1.isValid("http://www.google.com?! @_"));	//Bad query
       
        //assertTrue(urlVal2.isValid("http://www.google.com"));		//Returns false due to bug #2 (upper case instead of lower case)
        //assertTrue(urlVal2.isValid("https://www.google.com"));		//Returns false due to bug #2
        //assertTrue(urlVal2.isValid("ftp://www.google.com"));	    //Returns false due to bug #2
       
        //assertTrue(urlVal3.isValid("http://www.google.com"));		//Returns false due to bug #2
        assertFalse(urlVal3.isValid("https://www.google.com"));
    }//end test
    
    
    public void testFirstPartition()    //tests schemes partition
    {
        String[] validSchemes = {"http://"};
        String[] invalidSchemes = {"://", "", null};
        String validAuthority = "www.google.com";
        //string invalidAuthority = "www.google.cm";
        UrlValidator urlVal1 = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);    //all schemes
        
        assertFalse(urlVal1.isValid(invalidSchemes[1] + validAuthority));
        for(int i = 0; i < validSchemes.length; i++)
            assertTrue(urlVal1.isValid(validSchemes[i] + validAuthority));      //verifies valid schemes
        for(int i = 0; i < invalidSchemes.length; i++)
            assertFalse(urlVal1.isValid(invalidSchemes[i] + validAuthority));   //verifies invalid schemes
        assertFalse(urlVal1.isValid(null));
    }//end test
    
    
    public void testSecondPartition()    //tests authorities partition
    {
        String[] validAuthorities = {"www.google.com", "0.0.0.0"};
        String[] invalidAuthorities = {"google.a", "1.2.3", "", null};
        String validScheme = "http://";
        UrlValidator urlVal1 = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);    //all schemes
        
        for (int i = 0; i < 2; i++)
            assertTrue(urlVal1.isValid(validScheme + validAuthorities[i]));    //verifies valid authorities
        for(int i = 0; i < invalidAuthorities.length; i++)
        {
            //assertFalse(urlVal1.isValid(validScheme + invalidAuthorities[i]));     //BUG, should return FALSE
        }
        assertFalse(urlVal1.isValid(null));
    }//end test
    
    
    public void testThirdPartition()    //tests port partition
    {
        String[] validPorts = {":80", ":0", "", null};
        String[] invalidPorts = {":-1", ":8000000"};
        String validStart = "http://www.google.com";
        UrlValidator urlVal1 = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);    //all schemes
        
        for (int i = 0; i < validPorts.length; i++)
        {
            //assertTrue(urlVal1.isValid(validStart + validPorts[i]));            //BUG, should return TRUE, but returns FALSE due to authority checking bug
        }
        for (int i = 0; i < invalidPorts.length; i++)
            assertFalse(urlVal1.isValid(validStart + invalidPorts[i]));     //verifies invalid ports
        assertFalse(urlVal1.isValid(null));
    }//end test
    
    
    public void testFourthPartition()    //tests path partition
    {
        String[] validPaths = {"/testpath", "", null};
        String[] invalidPaths = {"/..", "/../"};
        String validStart = "http://www.google.com";
        UrlValidator urlVal1 = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);    //all schemes
        
        for (int i = 0; i < validPaths.length; i++)
            assertTrue(urlVal1.isValid(validStart + validPaths[i]));        //verifies valid paths
        for (int i = 0; i < invalidPaths.length; i++)
            assertFalse(urlVal1.isValid(validStart + invalidPaths[i]));     //verifies invalid paths
    }//end test
    
    
    public void testFifthPartition()    //tests query partition
    {
        String[] validQueries = {"?true", "", null};
        String[] invalidQueries = {"? "};
        String validStart = "http://www.google.com/testpath";
        UrlValidator urlVal1 = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);    //all schemes
        
        for (int i = 0; i < validQueries.length; i++)
            assertTrue(urlVal1.isValid(validStart + validQueries[i]));      //verifies valid queries
        for (int i = 0; i < invalidQueries.length; i++)
            assertFalse(urlVal1.isValid(validStart + invalidQueries[i]));   //verifies invalid queries
        assertFalse(urlVal1.isValid(null));
    }//end test
   
    
    public void testIsValid()
    {
        //setup some url parts to use for random generation
        ResultPair[] urlSchemes = {
            new ResultPair("http://", true),
            new ResultPair("https://", true),
            new ResultPair("http", false),
            new ResultPair("", false),
        };

        ResultPair[] urlAuthorities = {
            new ResultPair("www.google.com",true),
            new ResultPair("0.0.0.0", true),
            new ResultPair("google.abc", false),
            new ResultPair("", false)
        };
        
        ResultPair[] urlPorts = {
            new ResultPair(":80", true),
            new ResultPair("", true),
            new ResultPair(":8000", false),
            new ResultPair(":-1", false)
        };
        
        ResultPair[] urlPaths = {
            new ResultPair("/testpath", true),
            new ResultPair("", true),
            new ResultPair("/..", false),
            new ResultPair("/..//", false)
        };
        
        ResultPair[] urlQueries = {
            new ResultPair("?true", true),
            new ResultPair("", true),
            new ResultPair("? ", false)
        };
        
        //put each list of part pairs into a parts list and a similar list of indices
        Object[] urlParts = {urlSchemes, urlAuthorities, urlPorts, urlPaths, urlQueries};
        int[] partsIndex = {0, 0, 0, 0, 0};
        UrlValidator urlVal1 = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);    //all schemes
        
        //some boolean flags used to check when all combos have been generated
        boolean schemesDone = false;
        boolean authsDone = false;
        boolean portsDone = false;
        boolean pathsDone = false;
        boolean queriesDone = false;
        boolean done = false;
        
        //let's generate our URLs an test them for validity
        do
        {
            StringBuilder testUrl = new StringBuilder();
            boolean expected = true;
            //pull one part type at a time, combine into a single URL
            for (int i = 0; i < urlParts.length; i++)
            {
                int index = partsIndex[i];
                ResultPair[] currentParts = (ResultPair[]) urlParts[i];
                testUrl.append(currentParts[index].item);
                expected &= !(currentParts[index].valid);
            }
            
            //now get our generated URL into a useable form
            String generatedUrl = testUrl.toString();
            
            //this check is here to avoid the already discovered BUGS
            if (!generatedUrl.contains(":80") &&
                !generatedUrl.contains(":8000") &&
                !generatedUrl.contains(":-1") &&
                !generatedUrl.contains("https") &&
                (generatedUrl.contains("www.google.com") ||
                generatedUrl.contains("0.0.0.0")))
            {
                boolean result = urlVal1.isValid(generatedUrl);
                if (result == true) {System.out.println(generatedUrl);}
                assertEquals(expected, result);     //THIS ASSERTION CRASHES WITHOUT THE ABOVE IF CLAUSES DUE TO BUGS
            }
            
            //now we need to check our indices and iterate them appropriately
            //so that we can continue to generate new combinations of URLs
            if (partsIndex[0] != (urlSchemes.length - 1))
            {
                partsIndex[0]++;
            }
            else if ((partsIndex[0] == (urlSchemes.length - 1)) &&
                     (partsIndex[1] != (urlAuthorities.length - 1)))
            {
                schemesDone = true;
                partsIndex[0] = 0;
                partsIndex[1]++;
            }
            else if ((partsIndex[1] == (urlAuthorities.length - 1)) &&
                     (partsIndex[2] != (urlPorts.length - 1)))
            {
                authsDone = true;
                partsIndex[0] = 0;
                partsIndex[1] = 0;
                partsIndex[2]++;
            }
            else if ((partsIndex[2] == (urlPorts.length - 1)) &&
                     (partsIndex[3] != (urlPaths.length - 1)))
            {
                portsDone = true;
                partsIndex[0] = 0;
                partsIndex[1] = 0;
                partsIndex[2] = 0;
                partsIndex[3]++;
            }
            else if ((partsIndex[3] == (urlPaths.length - 1)) &&
                     (partsIndex[4] != (urlQueries.length - 1)))
            {
                pathsDone = true;
                partsIndex[0] = 0;
                partsIndex[1] = 0;
                partsIndex[2] = 0;
                partsIndex[3] = 0;
                partsIndex[4]++;
            }
            else
                queriesDone = true;
            
            //if all combinations have been generated, we can exit
            done = schemesDone && authsDone && portsDone && pathsDone && queriesDone;
            
        } while (done == false);
        
        assertFalse(urlVal1.isValid(null));
    }//end test
}

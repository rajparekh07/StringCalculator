package codes.rajp;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * String Calculator
 * A JUnit Class to test various aspects of codes.rajp.StringCalculator
 * @author rajparekh
 * @version 9
 * @since 04-05-2020
 */
public class StringCalculatorTest {

    /**
     * A method to test unknown amount of numbers
     * @throws Exception
     */
    @Test
    public void testAddNumberOfArguments() throws Exception {
        StringCalculator st = new StringCalculator();

        Assert.assertEquals(6, st.add("1,2,3"));
        Assert.assertEquals(3, st.add("1,2"));
        Assert.assertEquals(1, st.add("1,11232"));
        Assert.assertEquals(22, st.add("22"));
        Assert.assertEquals(2, st.add("2"));

        //Generate 100 random test cases of different number of arguments
        Random random = new Random();
        for (int t=0; t <= 100; t++) {
            int listLength = random.nextInt(10) + 5;
            List<Integer> testIntegerList = new ArrayList<>();
            for (int i=0; i<listLength; i++)
                testIntegerList.add(Math.abs(random.nextInt(2000)));

            Assert.assertEquals(testIntegerList.stream().filter(num->num<1000).mapToInt(Integer::intValue).sum(), st.add(testIntegerList.stream().map(String::valueOf).collect(Collectors.joining(","))));
        }
    }

    /**
     * A method to test the \n or new line as the delimiter between the numbers
     */
    @Test
    public void testNewLinesAsTheDelimiters() {
        StringCalculator st = new StringCalculator();

        Assert.assertEquals(6, st.add("1\n2,3"));
        Assert.assertEquals(6, st.add("1\\n2,3"));
        Assert.assertEquals(6, st.add("1\n2\n3"));
        Assert.assertEquals(1+2+3+3, st.add("1,2\n3,3"));
        Assert.assertEquals(1+2+3+3, st.add("1\n2\n3,3"));
        Assert.assertEquals(1+2+3+3, st.add("1\n2\n3\n3"));

    }

    /**
     * A method to test the custom delimiter mode
     */
    @Test
    public void testCustomDelimiters() {
        StringCalculator st = new StringCalculator();

        Assert.assertEquals(6, st.add("//[;]\n1;2;3"));
        Assert.assertEquals(6, st.add("//[;]\n1,2;3"));
        Assert.assertEquals(6, st.add("//[s]\n1,2s3"));
        Assert.assertEquals(6, st.add("//[;]\n1\n2\n3"));
        Assert.assertEquals(6, st.add("//[;]\\n1\n2\n3"));
        Assert.assertEquals(9, st.add("//[*]\n1*3,5"));

    }

    /**
     * A method to test illegal custom delimiter mode
     * @throws Exception
     */
    @Test(expected = RuntimeException.class)
    public void testIllegalCustomDelimiters() throws Exception {
        StringCalculator st = new StringCalculator();

        Assert.assertEquals(6, st.add("//[1]\n112;3"));
    }

    /**
     *  A method to test accepting of valid digits
     * @throws Exception
     */
    @Test(expected=RuntimeException.class)
    public void testIllegalDigits() throws Exception {
        StringCalculator st = new StringCalculator();

        st.add("1,2a");
    }

    /**
     *  A method to test validity of "," as the delimiter
     * @throws Exception
     */
    @Test(expected=RuntimeException.class)
    public void testDifferentDelimiter() throws Exception {
        StringCalculator st = new StringCalculator();

        st.add("1:2");
    }

    /**
     *  A method to test validity of delimiters
     * @throws Exception
     */
    @Test(expected=RuntimeException.class)
    public void testTrailingDelimiter() throws Exception {
        StringCalculator st = new StringCalculator();

        st.add("1\n2,");
    }

    /**
     *  A method to test the different forms of numbers
     * @throws Exception
     */
    @Test
    public void testDifferentNumbers() throws Exception {
        StringCalculator st = new StringCalculator();

        Assert.assertEquals(2, st.add("2,000000"));
        Assert.assertEquals(2, st.add("00002,000000"));
        Assert.assertEquals(2, st.add("2,+0"));
        Assert.assertEquals(2, st.add("+2,+0"));

    }

    /**
     * A method to test handling of negative numbers
     * @throws Exception
     */
    @Test(expected = RuntimeException.class)
    public void testNegativeNumbers() throws Exception {
        StringCalculator st = new StringCalculator();


        Assert.assertEquals(-1, st.add("1,-2"));
        Assert.assertEquals(1, st.add("-1,2"));
        Assert.assertEquals(-2, st.add("-2"));
    }


    /**
     * A method to test ignoring of values greater than 1000 to the sum
     */
    @Test
    public void testIgnoringNumbersMoreThan1000() throws Exception {
        StringCalculator st = new StringCalculator();

        Assert.assertEquals(2, st.add("2,1001"));
        Assert.assertEquals(1, st.add("1,2000"));
        Assert.assertEquals(0, st.add("1000"));
    }

    /**
     * A method to test multi-character custom delimiters
     * @throws Exception
     */
    @Test
    public void testMultiCharacterCustomDelimiter() throws Exception {
        StringCalculator st = new StringCalculator();

        Assert.assertEquals(9, st.add("//[****]\n1****3,5"));

        //Generate 100 random test cases of different length of delimiters
        Random random = new Random();
        for (int t=0; t <= 10; t++) {
            int listLength = random.nextInt(10) + 5;
            List<Integer> testIntegerList = new ArrayList<>();
            for (int i=0; i<listLength; i++)
                testIntegerList.add(Math.abs(random.nextInt(2000)));

            String customDelimiter =  this.getRandomString();
            Assert.assertEquals(testIntegerList.stream().filter(num->num<1000).mapToInt(Integer::intValue).sum(),
                    st.add("//["+customDelimiter+"]\n"+testIntegerList.stream().map(String::valueOf).collect(Collectors.joining(customDelimiter))));
        }
    }

    /**
     * A method to test multi-character multi custom delimiters
     * @throws Exception
     */
    @Test
    public void testMultiCharacterMultiCustomDelimiter() throws Exception {
        StringCalculator st = new StringCalculator();

        Assert.assertEquals(9, st.add("//[****]\n1****3,5"));

        //Generate 100 random test cases of different length of multiple delimiters
        Random random = new Random();
        for (int t=0; t <= 10; t++) {
            int listLength = random.nextInt(10) + 5;
            List<Integer> testIntegerList = new ArrayList<>();
            for (int i=0; i<listLength; i++)
                testIntegerList.add(Math.abs(random.nextInt(2000)));


            String customDelimiters[] = new String[2];
            for (int i=0; i < customDelimiters.length; i++) {
                customDelimiters[i] = this.getRandomString();
            }
            Assert.assertEquals(testIntegerList.stream().filter(num->num<1000).mapToInt(Integer::intValue).sum(),
                    st.add("//["+customDelimiters[0]+"]["+customDelimiters[1]+"]\n"+testIntegerList.stream().map(String::valueOf).collect(Collectors.joining(customDelimiters[Math.abs(random.nextInt())%2]))));
        }
    }

    private String getRandomString() {
        Random random = new Random();
        String dict = "!@#$%^&*()+_={}|:';\"?><,./`~";
        return String.join("",
                Collections.nCopies(
                        Math.abs(random.nextInt(9))+1,
                        ""+dict.charAt(random.nextInt(dict.length()-1))
                ));
    }
}
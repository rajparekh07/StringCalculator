package codes.rajp;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

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
        Assert.assertEquals(11233, st.add("1,11232"));
        Assert.assertEquals(22, st.add("22"));
        Assert.assertEquals(2, st.add("2"));

        //Generate 1000 random test cases of different number of arguments
        Random random = new Random();
        for (int t=0; t <= 100; t++) {
            int listLength = random.nextInt(100) + 50;
            List<Integer> testIntegerList = new ArrayList<>();
            for (int i=0; i<listLength; i++)
                testIntegerList.add(random.nextInt());

            Assert.assertEquals(testIntegerList.stream().mapToInt(Integer::intValue).sum(), st.add(testIntegerList.stream().map(String::valueOf)
                    .collect(Collectors.joining(","))));
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
     *  A method to test validity of "," as the delimiter
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

        Assert.assertEquals(-1, st.add("1,-2"));
        Assert.assertEquals(1, st.add("-1,2"));
        Assert.assertEquals(-2, st.add("-2"));
        Assert.assertEquals(2, st.add("2,000000"));
        Assert.assertEquals(2, st.add("00002,000000"));
        Assert.assertEquals(2, st.add("2,+0"));
        Assert.assertEquals(2, st.add("+2,+0"));

    }

}
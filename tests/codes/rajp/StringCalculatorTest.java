package codes.rajp;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringCalculatorTest {

    @Test
    public void testAddNumberOfArguments() throws Exception {
        StringCalculator st = new StringCalculator();

        Assert.assertEquals(3, st.add("1,2,3"));
        Assert.assertEquals(11233, st.add("1,11232"));
        Assert.assertEquals(22, st.add("22"));
        Assert.assertEquals(2, st.add("2"));
    }

    @Test(expected=RuntimeException.class)
    public void testIllegalDigits() throws Exception {
        StringCalculator st = new StringCalculator();

        st.add("1,2a");
    }

    @Test(expected=RuntimeException.class)
    public void testDifferentDelimiter() throws Exception {
        StringCalculator st = new StringCalculator();

        st.add("1:2");
    }

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
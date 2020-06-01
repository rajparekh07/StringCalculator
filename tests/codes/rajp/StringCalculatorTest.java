package codes.rajp;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringCalculatorTest {

    @Test
    public void testTwo() throws Exception {
        StringCalculator st = new StringCalculator();

        Assert.assertEquals(st.returnTwo(), 2);

    }


}
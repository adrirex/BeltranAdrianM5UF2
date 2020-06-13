package com.example.beltranadrianm5uf2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class precioConDescuentoTest {

    private MainActivity main;

    @Before
    public void setUp() throws Exception {
        main = new MainActivity();
    }

    @Test
    public void testPrecioConDescuento() throws Exception {
        Integer result = main.precionConDescuento("0", 1);
        Integer result2 = main.precionConDescuento("50", 10);
        Integer result3 = main.precionConDescuento("100", 20);

        assertEquals("30", Integer.toString(result));
        assertEquals("150", Integer.toString(result2));
        assertEquals("0", Integer.toString(result3));
    }
}

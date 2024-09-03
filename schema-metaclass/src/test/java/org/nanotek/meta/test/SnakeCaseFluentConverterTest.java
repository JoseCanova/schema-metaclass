package org.nanotek.meta.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.util.SnakeCaseFluentConverter;

public class SnakeCaseFluentConverterTest {

    @Test
    public void testSnakeCaseFluentConverter(){
        assertTrue(1==1);
        var snakeCaseSample = "snake_case_sample";
        String result = SnakeCaseFluentConverter.from(snakeCaseSample);
        assertTrue(result.equals("snakeCaseSample"));
    }
}

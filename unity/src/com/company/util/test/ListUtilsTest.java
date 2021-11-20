package com.company.util.test;

import org.junit.Test;
import java.util.List;

import static com.company.util.ListUtils.*;
import static org.junit.jupiter.api.Assertions.*;

public class ListUtilsTest {
    @Test
    public void shouldReturnFalseWhenListIsEmptyOrNull() {
        assertTrue(isNullOrEmpty(List.of()));
        assertTrue(isNullOrEmpty(null));
    }

    @Test
    public void shouldReturnTrueWhenListHasElements() {
        assertFalse(isNullOrEmpty(List.of("elem1")));
    }
}

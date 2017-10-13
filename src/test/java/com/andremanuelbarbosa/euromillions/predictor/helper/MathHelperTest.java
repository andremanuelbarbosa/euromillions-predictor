package com.andremanuelbarbosa.euromillions.predictor.helper;

import com.andremanuelbarbosa.euromillions.predictor.EuroMillionsPredictorTest;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MathHelperTest extends EuroMillionsPredictorTest {

    @Test
    public void shouldReturn3CombinationsOnGetCombinationsWhenElementsSizeIs3AndSizeIs2() {

        final List<List<String>> combinations = MathHelper.getCombinations(Lists.newArrayList("A", "B", "C"), 2);

        assertEquals(3, combinations.size());

        assertTrue(combinations.contains(Lists.newArrayList("A", "B")));
        assertTrue(combinations.contains(Lists.newArrayList("A", "C")));
        assertTrue(combinations.contains(Lists.newArrayList("B", "C")));
    }

    @Test
    public void shouldReturn6CombinationsOnGetCombinationsWhenElementsSizeIs4AndSizeIs2() {

        final List<List<String>> combinations = MathHelper.getCombinations(Lists.newArrayList("A", "B", "C", "D"), 2);

        assertEquals(6, combinations.size());

        assertTrue(combinations.contains(Lists.newArrayList("A", "B")));
        assertTrue(combinations.contains(Lists.newArrayList("A", "C")));
        assertTrue(combinations.contains(Lists.newArrayList("A", "D")));
        assertTrue(combinations.contains(Lists.newArrayList("B", "C")));
        assertTrue(combinations.contains(Lists.newArrayList("B", "D")));
        assertTrue(combinations.contains(Lists.newArrayList("C", "D")));
    }

    @Test
    public void shouldReturn4CombinationsOnGetCombinationsWhenElementsSizeIs4AndSizeIs3() {

        final List<List<String>> combinations = MathHelper.getCombinations(Lists.newArrayList("A", "B", "C", "D"), 3);

        assertEquals(4, combinations.size());

        assertTrue(combinations.contains(Lists.newArrayList("A", "B", "C")));
        assertTrue(combinations.contains(Lists.newArrayList("A", "B", "D")));
        assertTrue(combinations.contains(Lists.newArrayList("A", "C", "D")));
        assertTrue(combinations.contains(Lists.newArrayList("B", "C", "D")));
    }

    @Test
    public void shouldReturn5CombinationsOnGetCombinationsWhenElementsSizeIs5AndSizeIs4() {

        final List<List<String>> combinations = MathHelper.getCombinations(Lists.newArrayList("A", "B", "C", "D", "E"), 4);

        assertEquals(5, combinations.size());

        assertTrue(combinations.contains(Lists.newArrayList("A", "B", "C", "D")));
        assertTrue(combinations.contains(Lists.newArrayList("A", "B", "C", "E")));
        assertTrue(combinations.contains(Lists.newArrayList("A", "B", "D", "E")));
        assertTrue(combinations.contains(Lists.newArrayList("A", "C", "D", "E")));
        assertTrue(combinations.contains(Lists.newArrayList("B", "C", "D", "E")));
    }

    @Test
    public void shouldReturn435CombinationsOnGetCombinationsWhenElementsSizeIs30AndSizeIs2() {

        final String[] chars = new String[30];

        for (int i = 0; i < chars.length; i++) {

            chars[i] = "" + (char) (97 + i);
        }

        final List<List<String>> combinations = MathHelper.getCombinations(Lists.newArrayList(chars), 2);

        assertEquals(435, combinations.size());
    }

    @Test
    public void shouldReturn4060CombinationsOnGetCombinationsWhenElementsSizeIs30AndSizeIs3() {

        final String[] chars = new String[30];

        for (int i = 0; i < chars.length; i++) {

            chars[i] = "" + (char) (97 + i);
        }

        final List<List<String>> combinations = MathHelper.getCombinations(Lists.newArrayList(chars), 3);

        assertEquals(4060, combinations.size());
    }
}

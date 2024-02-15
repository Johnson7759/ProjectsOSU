import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;

public class GlossaryTest {

    @Test
    public void testNextWordOrSeparator() {
        int position = 0;
        String text = "mike,ike,like";
        Set<Character> separators = new Set1L<>();
        String newtext = Glossary.nextWordOrSeparator(text, position,
                separators);
        assertEquals(newtext, text);
    }

    @Test
    public void testGenerateElements() {
        final String sepStr = "\t, ";
        Set<Character> separators = new Set1L<>();
        Glossary.generateElements(sepStr, separators);
    }

    @Test
    public void testAlphabetize() {
        Set<String> terms = new Set1L<>();
        Glossary.alphabetize(terms);
    }
}

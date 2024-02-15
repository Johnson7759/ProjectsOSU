import components.map.Map;
import components.map.Map1L;
import components.sequence.Sequence;
import components.sequence.Sequence1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/* Simple Glossary program (clear of Checkstyle and FindBugs warnings).
*
* @Put your name here
*/
/**
 * @author Noel Johnson
 *
 */
public final class Glossary {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private Glossary() {
        // no code needed here
    }

    /**
     * Outputs the html.
     *
     * @param out
     *            output
     * @param terms
     *            sequence of terms
     * @param mappedTerms
     *            map of terms & definitions
     * @param termArray
     *            array of terms
     * @param fileOutput
     *            where file is output to
     */
    public static void outputIndex(SimpleWriter out, Sequence<String> terms,
            Map<String, String> mappedTerms, String[] termArray,
            String fileOutput) {
        String index = fileOutput + "/index.html";
        SimpleWriter indexPage = new SimpleWriter1L(index);

        //Header
        indexPage.println("<html>");
        indexPage.println("<head>");
        indexPage.println("<title>" + "Glossary" + "</title>");
        indexPage.println("</head>");
        indexPage.println("<body>");
        indexPage.println("<p style=\"font-size:35pt;\">Glossary</p>");
        indexPage.println();
        indexPage.println("<p style=\"font-size:15pt;\">Index</p>");
        indexPage.println("<ul>");

        while (terms.length() > 0) {
            String word = terms.remove(0);
            generateWordPages(word, out, mappedTerms, termArray, fileOutput);
            indexPage.println(
                    "<li><a href=\"" + word + ".html\">" + word + "</a></li>");
        }
        //Footer
        indexPage.println("</ul>");
        indexPage.println("</body>");
        indexPage.println("</html>");

        indexPage.close();

    }

    /**
     * Creates page for each word.
     *
     * @param word
     *            the word for the page
     * @param out
     *            output
     * @param mappedTerms
     *            map of terms & definition
     * @param termArray
     *            array of terms
     * @param outputFile
     *            where file is output to
     */
    public static void generateWordPages(String word, SimpleWriter out,
            Map<String, String> mappedTerms, String[] termArray,
            String outputFile) {

        //Create page for word
        String wordHtmlPage = outputFile + "/" + word + ".html";
        SimpleWriter htmlPage = new SimpleWriter1L(wordHtmlPage);

        //opening html tags
        htmlPage.println("<html>");
        htmlPage.println("<head>");
        htmlPage.println("<title>" + word + "</title>");
        htmlPage.println("</head>");
        htmlPage.println("<body>");
        htmlPage.println("<p><b><i>" + word + "</b></i></p>");
        htmlPage.println();

        //Uses generate elements method to output the definition of the word
        String definition = mappedTerms.value(word);
        Set<Character> separatorSet = new Set1L<>();
        String separators = " ,";
        generateElements(separators, separatorSet);

        String pageInput = "";

        int i = 0;
        while (i < definition.length()) {
            String token = nextWordOrSeparator(definition, i, separatorSet);
            if (separatorSet.contains(token.charAt(0))) {
                pageInput = pageInput + token;
            } else {

                int count = 0;
                for (int n = 0; n < termArray.length; n++) {
                    if (token.equals(termArray[n])) {
                        pageInput = pageInput + "<a href=\"" + termArray[n]
                                + ".html\">" + token + "</a>";
                        count++;
                    }
                }
                if (count == 0) {
                    pageInput = pageInput + token;
                }
            }
            i = i + token.length();
        }

        //closing html tags
        htmlPage.println("<p>" + pageInput + "</p>");
        htmlPage.println();
        htmlPage.println("Return to <a href=\"index.html\">index</a>");
        htmlPage.println("</body>");
        htmlPage.println("</html>");

        //closing page
        htmlPage.close();
    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position}.
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {

        String result = "" + text.charAt(position);
        boolean sepTest = separators.contains(text.charAt(position));
        int endString = position;

        endString++;
        while (sepTest && endString < text.length()
                && separators.contains(text.charAt(endString))
                || !sepTest && endString < text.length()
                        && !separators.contains(text.charAt(endString))
                        && endString != text.length()) {
            endString++;
        }

        result = text.substring(position, endString);

        return result;
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set}.
     *
     * @param str
     *            the given {@code String}
     * @param charSet
     *            the {@code Set} to be replaced
     * @replaces charSet
     * @ensures charSet = entries(str)
     */
    public static void generateElements(String str, Set<Character> charSet) {

        int i = str.length();

        while (i > 0) {
            char x = str.charAt(i - 1);
            if (!charSet.contains(x)) {
                charSet.add(x);
            }
            i--;
        }

    }

    /**
     * @param in
     *            input
     * @param mapTerms
     *            map of terms & definitions
     * @param terms
     *            the terms all in set form
     */
    public static void findTermsAndDefinitions(SimpleReader in,
            Map<String, String> mapTerms, Set<String> terms) {

        while (!in.atEOS()) {

            String next = in.nextLine();
            String definition = "";
            String term = "";
            boolean emptyLine = false;

            if (next.equals("")) {
                //empty line to stop loop
                emptyLine = true;
            } else {
                // assigns word to term
                term = next;
            }

            while (!emptyLine && !in.atEOS()) {

                //assigns words to definitions
                next = in.nextLine();
                if (!next.equals("")) {
                    definition = definition + " " + next;
                } else {
                    //empty line to stop loop
                    emptyLine = true;
                }
            }

            mapTerms.add(term, definition);
            terms.add(term);
        }

    }

    /**
     * @param terms
     * @return terms in alphabetical order
     */
    public static String alphabetize(Set<String> terms) {
        Set<String> terms2 = new Set1L<>();
        String result = "";

        while (terms.size() > 0 && result.equals("")) {
            int count = 0;
            String test = terms.removeAny();
            for (String word : terms) {
                if (word.compareTo(test) < 0) {
                    count++;
                }
            }
            if (count == 0) {
                result = test;
            } else {
                terms2.add(test);
            }
        }
        terms.add(terms2);
        return result;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();

        //Ask user for file name
        out.println("enter file");
        String fileName = in.nextLine();
        SimpleReader inFile = new SimpleReader1L(fileName);

        //Ask user for file output
        out.println("Enter file output");
        String fileOutput = in.nextLine();

        //matches terms and defs from input file
        Map<String, String> mapTerms = new Map1L<>();
        Set<String> terms = new Set1L<>();
        findTermsAndDefinitions(inFile, mapTerms, terms);

        Sequence<String> termsList = new Sequence1L<>();
        String[] termArray = new String[terms.size()];

        for (int i = 0; 0 < terms.size(); i++) {
            String nextTerm = alphabetize(terms);
            termsList.add(termsList.length(), nextTerm);
            termArray[i] = nextTerm;
        }

        //creates html
        outputIndex(out, termsList, mapTerms, termArray, fileOutput);

        out.close();
        in.close();
    }

}

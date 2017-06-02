package org.mcwhirter.cfr.exporter.asciidoc;

/**
 * Created by bob on 6/2/17.
 */
public abstract class ListMatcher {
    static RomanNumeralListMatcher ROMAN_NUMERAL = new RomanNumeralListMatcher(null);
    static ArabicNumeralListMatcher ARABIC_NUMERAL = new ArabicNumeralListMatcher(ROMAN_NUMERAL);
    static LowercaseAlphabetListMatcher LOWERCASE_ALPHABET = new LowercaseAlphabetListMatcher(ARABIC_NUMERAL);

    public abstract boolean isNext(int current, String str);

    public ListMatcher(ListMatcher nextDeeper) {
        this.nextDeeper = nextDeeper;
    }

    public ListMatcher getNextDeeper() {
        return this.nextDeeper;
    }

    public static ListMatcher forDepth(int depth) {
        if ( depth == 0 ) {
            return LOWERCASE_ALPHABET;
        }
        if ( depth == 1 ) {
            return ARABIC_NUMERAL;
        }
        if ( depth == 2 ) {
            return ROMAN_NUMERAL;
        }

        throw new RuntimeException("unexpected list depth: " + depth);
    }

    private final ListMatcher nextDeeper;
}

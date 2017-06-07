package org.mcwhirter.cfr.exporter.asciidoc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by bob on 6/2/17.
 */
public abstract class OrderedListType {
    static LowercaseAlphabetOrderedListType LOWERCASE_ALPHABET = new LowercaseAlphabetOrderedListType();
    static ArabicNumeralOrderedListType ARABIC_NUMERAL = new ArabicNumeralOrderedListType();
    static RomanNumeralOrderedListType ROMAN_NUMERAL = new RomanNumeralOrderedListType();
    static UppercaseAlphabetOrderedListType UPPERCASE_ALPHABET = new UppercaseAlphabetOrderedListType();

    static List<OrderedListType> ALL = new ArrayList<OrderedListType>() {{
        add( LOWERCASE_ALPHABET );
        add( ARABIC_NUMERAL );
        add( ROMAN_NUMERAL );
        add( UPPERCASE_ALPHABET );
    }};

    public abstract boolean isNext(int current, String str);

    public OrderedListType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    private String name;

    public static OrderedListType nextType(OrderedListType currentType, String text) {
        Optional<OrderedListType> found = ALL.stream()
                .filter(e -> e != currentType)
                .filter(e -> e.isNext(0, text))
                .findFirst();

        if ( found.isPresent() ) {
            return found.get();
        }

        return null;
    }

}

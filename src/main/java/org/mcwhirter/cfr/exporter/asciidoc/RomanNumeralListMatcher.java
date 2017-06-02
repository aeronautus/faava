package org.mcwhirter.cfr.exporter.asciidoc;

import java.util.TreeMap;

/**
 * Created by bob on 6/2/17.
 */
public class RomanNumeralListMatcher extends ListMatcher {

    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {

        map.put(100, "c");
        map.put(90, "xc");
        map.put(50, "l");
        map.put(40, "xl");
        map.put(10, "x");
        map.put(9, "ix");
        map.put(5, "v");
        map.put(4, "iv");
        map.put(1, "i");
    }

    public RomanNumeralListMatcher(ListMatcher nextDeeper) {
        super(nextDeeper);
    }

    private final static String toRoman(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }

    @Override
    public boolean isNext(int current, String str) {
        int next = current + 1;
        return str.startsWith("(" + toRoman(next) + ")");
    }
}

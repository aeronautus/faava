package org.mcwhirter.cfr.exporter.asciidoc;

/**
 * Created by bob on 6/2/17.
 */
public class UppercaseAlphabetOrderedListType extends OrderedListType {

    public UppercaseAlphabetOrderedListType() {
        super("upperalpha");
    }

    @Override
    public boolean isNext(int current, String str) {
        char a = 'A';
        char next = (char) (a + current);

        return str.startsWith("(" + next + ")");
    }
}

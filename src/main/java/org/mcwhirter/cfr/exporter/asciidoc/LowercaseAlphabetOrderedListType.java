package org.mcwhirter.cfr.exporter.asciidoc;

/**
 * Created by bob on 6/2/17.
 */
public class LowercaseAlphabetOrderedListType extends OrderedListType {

    public LowercaseAlphabetOrderedListType() {
        super("loweralpha");
    }

    @Override
    public boolean isNext(int current, String str) {
        char a = 'a';
        char next = (char) (a + current);

        return str.startsWith("(" + next + ")");
    }
}

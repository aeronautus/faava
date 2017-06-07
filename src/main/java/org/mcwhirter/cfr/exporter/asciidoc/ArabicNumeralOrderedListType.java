package org.mcwhirter.cfr.exporter.asciidoc;

/**
 * Created by bob on 6/2/17.
 */
public class ArabicNumeralOrderedListType extends OrderedListType {

    public ArabicNumeralOrderedListType() {
        super("arabic");
    }

    @Override
    public boolean isNext(int current, String str) {
        int next = current + 1;
        return str.startsWith("(" + next + ")");
    }
}

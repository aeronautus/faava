package org.mcwhirter.cfr.exporter.asciidoc;

import java.util.List;

/**
 * Created by bob on 6/2/17.
 */
public class ArabicNumeralListMatcher extends ListMatcher {

    public ArabicNumeralListMatcher(ListMatcher nextDeeper) {
        super(nextDeeper);
    }

    @Override
    public boolean isNext(int current, String str) {
        int next = current + 1;
        return str.startsWith("(" + next + ")");
    }
}

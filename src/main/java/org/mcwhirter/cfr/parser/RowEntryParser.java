package org.mcwhirter.cfr.parser;

import org.mcwhirter.cfr.model.RowEntry;

/**
 * Created by bob on 5/31/17.
 */
public class RowEntryParser extends Parser<RowEntry> {

    public RowEntryParser() {
        super(Tags.ENT, RowEntry.class);
        textContent(RowEntry::setContent);
    }

}

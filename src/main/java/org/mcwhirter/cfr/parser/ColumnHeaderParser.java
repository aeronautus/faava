package org.mcwhirter.cfr.parser;

import org.mcwhirter.cfr.model.ColumnHeader;

/**
 * Created by bob on 5/31/17.
 */
public class ColumnHeaderParser extends Parser<ColumnHeader> {

    public ColumnHeaderParser() {
        super(Tags.CHED, ColumnHeader.class);
        intAttribute("H", ColumnHeader::setLevel);
        textContent(ColumnHeader::setContent);
    }

}

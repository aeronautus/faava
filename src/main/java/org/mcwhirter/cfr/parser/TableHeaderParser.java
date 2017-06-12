package org.mcwhirter.cfr.parser;

import org.mcwhirter.cfr.model.TableHeader;

/**
 * Created by bob on 5/31/17.
 */
public class TableHeaderParser extends Parser<TableHeader> {

    public TableHeaderParser() {
        super(Tags.BOXHD, TableHeader.class);
        tag(new ColumnHeaderParser(), TableHeader::insertHeader);
    }

}

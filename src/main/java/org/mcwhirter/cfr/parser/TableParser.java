package org.mcwhirter.cfr.parser;

import org.mcwhirter.cfr.model.Table;

/**
 * Created by bob on 5/31/17.
 */
public class TableParser extends Parser<Table> {

    public TableParser() {
        super(Tags.GPOTABLE, Table.class);
        tag(new TableHeaderParser(), Table::setHeader);
        tag(new TableRowParser(), Table::addRow);
    }

}

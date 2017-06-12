package org.mcwhirter.cfr.parser;

import org.mcwhirter.cfr.model.TableRow;

/**
 * Created by bob on 5/31/17.
 */
public class TableRowParser extends Parser<TableRow> {

    public TableRowParser() {
        super(Tags.ROW, TableRow.class);
        tag(new RowEntryParser(), TableRow::addEntry);
    }

}

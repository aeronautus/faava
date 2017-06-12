package org.mcwhirter.cfr.model;

import java.util.ArrayList;
import java.util.List;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 6/7/17.
 */
public class Table implements BaseModel, Block {

    public Table() {
    }

    public void setHeader(TableHeader header) {
        this.header = header;
    }

    public TableHeader getHeader() {
        return this.header;
    }

    public void addRow(TableRow row) {
        this.rows.add( row );
    }

    public List<TableRow> getRows() {
        return this.rows;
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);

    }

    private TableHeader header;
    private List<TableRow> rows = new ArrayList<>();
}

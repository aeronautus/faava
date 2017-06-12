package org.mcwhirter.cfr.model;

import java.util.ArrayList;
import java.util.List;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 6/7/17.
 */
public class TableRow implements BaseModel {

    public TableRow() {

    }

    public void addEntry(RowEntry entry) {
        this.entries.add(entry);
    }

    public List<RowEntry> getEntries() {
        return this.entries;
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);
    }

    private List<RowEntry> entries = new ArrayList<>();
}

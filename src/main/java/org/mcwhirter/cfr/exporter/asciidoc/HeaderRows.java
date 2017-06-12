package org.mcwhirter.cfr.exporter.asciidoc;

import java.util.ArrayList;
import java.util.List;

import org.mcwhirter.cfr.model.ColumnHeader;
import org.mcwhirter.cfr.model.TableHeader;

/**
 * Created by bob on 6/7/17.
 */
public class HeaderRows {
    public HeaderRows(TableHeader header) {
        initRows(header);
        initColumns(header);
    }

    protected void initRows(ColumnHeader header) {
        if (header.getLevel() > this.rows.size() ) {
            this.rows.add( new Row() );
        }

        for (ColumnHeader e : header.getHeaders()) {
            initRows(e);
        }
    }

    protected void initColumns(TableHeader header) {
        for (ColumnHeader e : header.getHeaders()) {
            initColumn(e) ;
        }
    }

    protected void initColumn(ColumnHeader header) {
        row(header.getLevel()).add(header);

        for (ColumnHeader e : header.getHeaders()) {
            initColumn(e);
        }
    }

    public List<Row> getRows() {
        return this.rows;
    }

    private Row row(int level) {
        return this.rows.get(level-1);
    }

    private List<Row> rows = new ArrayList<>();

    public static class Row {

        public Row() {

        }

        public void add(ColumnHeader header) {
            this.headers.add( header );
        }

        public List<ColumnHeader> getHeaders() {
            return this.headers;
        }

        private List<ColumnHeader> headers = new ArrayList<>();
    }

}

package org.mcwhirter.cfr.model;

import java.util.ArrayList;
import java.util.List;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 6/7/17.
 */
public class ColumnHeader implements BaseModel {

    public ColumnHeader() {
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public int getWidth() {
        return this.headers.stream()
                .map(e -> e.getWidth())
                .reduce((sum, n) -> sum + n).orElse(1);
    }

    public int getHeight() {
        return this.headers.stream()
                .map(e -> e.getHeight())
                .max(Integer::compare).orElse(1);
    }

    public void setContent(Paragraph content) {
        this.content = content;
    }

    public Paragraph getContent() {
        return this.content;
    }

    public void addHeader(ColumnHeader header) {
        this.headers.add(header);
    }

    public List<ColumnHeader> getHeaders() {
        return this.headers;
    }

    public void insertHeader(ColumnHeader header) {
        if (header.getLevel() == this.level + 1) {
            this.headers.add(header);
        } else {
            this.headers.get(this.headers.size() - 1).insertHeader(header);
        }
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);
    }

    private int level;

    private Paragraph content;

    private List<ColumnHeader> headers = new ArrayList<>();
}

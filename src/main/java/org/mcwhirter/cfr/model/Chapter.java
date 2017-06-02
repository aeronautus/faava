package org.mcwhirter.cfr.model;

import java.util.ArrayList;
import java.util.List;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 5/31/17.
 */
public class Chapter implements BaseModel {

    public void addSubchapter(Subchapter subchapter) {
        this.subchapters.add(subchapter);
    }

    public List<Subchapter> getSubchapters() {
        return this.subchapters;
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);

    }

    private List<Subchapter> subchapters = new ArrayList<>();
}

package org.mcwhirter.cfr.model;

import java.util.ArrayList;
import java.util.List;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 5/31/17.
 */
public class CFR implements BaseModel {

    public void addTitle(Title title) {
        this.titles.add( title);
    }

    public List<Title> getTitles() {
        return this.titles;
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);
    }

    private List<Title> titles = new ArrayList<>();
}

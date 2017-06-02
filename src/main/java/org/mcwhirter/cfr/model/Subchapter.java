package org.mcwhirter.cfr.model;

import java.util.ArrayList;
import java.util.List;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 5/31/17.
 */
public class Subchapter implements BaseModel {

    public void addPart(Part part) {
        this.parts.add( part );
    }

    public List<Part> getParts() {
        return this.parts;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);
    }

    private String title;
    private List<Part> parts = new ArrayList<>();
}

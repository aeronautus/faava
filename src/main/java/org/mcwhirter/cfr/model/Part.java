package org.mcwhirter.cfr.model;

import java.util.ArrayList;
import java.util.List;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 5/31/17.
 */
public class Part implements BaseModel, Identified {

    public String id() {
        return this.id;
    }

    public void setTitle(String title) {
        int dashLoc = title.indexOf("—");
        this.id = title.substring(5,dashLoc).trim();
        this.title = title.substring(dashLoc+1).trim();
    }

    public String getTitle() {
        return this.title;
    }

    public void addSection(Section section) {
        this.sections.add( section );
    }

    public List<Section> getSections() {
        return this.sections;
    }

    public void addSubpart(Subpart subpart) {
        this.subparts.add( subpart);
    }

    public List<Subpart> getSubparts() {
        return this.subparts;
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);

    }

    private String id;
    private String title;

    private List<Section> sections = new ArrayList<>();
    private List<Subpart> subparts = new ArrayList<>();
}

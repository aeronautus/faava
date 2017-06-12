package org.mcwhirter.cfr.model;

import java.util.ArrayList;
import java.util.List;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 5/31/17.
 */
public class SubjectGroup implements BaseModel {

    public void setTitle(Paragraph title) {
        this.title = title.asSimpleString();
    }

    public void setTitle(String title) {
        this.title = title;
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

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);
    }

    private String title;

    private List<Section> sections = new ArrayList<>();
}

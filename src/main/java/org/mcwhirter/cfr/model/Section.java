package org.mcwhirter.cfr.model;

import java.util.ArrayList;
import java.util.List;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 5/31/17.
 */
public class Section implements BaseModel, Identified {

    public String id() {
        return this.id;
    }

    public void setSectionNumber(String sectionNumber) {
        this.id = sectionNumber.substring(2);
        this.sectionNumber = sectionNumber;
    }

    public String getSectionNumber() {
        return this.sectionNumber;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public boolean isReserved() {
        return this.reserved;
    }

    public void addParagraph(Paragraph paragraph) {
        this.paragraphs.add(paragraph);
    }

    public List<Paragraph> getParagraphs() {
        return this.paragraphs;
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);
    }

    private String id;

    private String sectionNumber;

    private String subject;

    private boolean reserved;

    private List<Paragraph> paragraphs = new ArrayList<>();
}

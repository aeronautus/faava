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

    public void setSubject(Paragraph subject) {
        this.subject = subject;
    }

    public Paragraph getSubject() {
        return this.subject;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public boolean isReserved() {
        return this.reserved;
    }

    public void addBlock(Block block) {
        this.blocks.add(block);
    }

    public List<Block> getBlocks() {
        return this.blocks;
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);
    }

    private String id;

    private String sectionNumber;

    private Paragraph subject;

    private boolean reserved;

    private List<Block> blocks = new ArrayList<>();
}

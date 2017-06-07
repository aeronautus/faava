package org.mcwhirter.cfr.visitor;

import org.mcwhirter.cfr.model.Block;
import org.mcwhirter.cfr.model.CFR;
import org.mcwhirter.cfr.model.Chapter;
import org.mcwhirter.cfr.model.Document;
import org.mcwhirter.cfr.model.EmphasizedText;
import org.mcwhirter.cfr.model.ListItem;
import org.mcwhirter.cfr.model.OrderedList;
import org.mcwhirter.cfr.model.Paragraph;
import org.mcwhirter.cfr.model.Part;
import org.mcwhirter.cfr.model.Section;
import org.mcwhirter.cfr.model.Subchapter;
import org.mcwhirter.cfr.model.Subpart;
import org.mcwhirter.cfr.model.Text;
import org.mcwhirter.cfr.model.Title;

/**
 * Created by bob on 6/1/17.
 */
public class DefaultVisitor implements Visitor {

    public void visit(Document doc) throws Exception {
        doc.getCFR().accept(this);
    }

    public void visit(CFR cfr) throws Exception {
        for (Title e : cfr.getTitles()) {
            e.accept(this);
        }
    }

    public void visit(Title title) throws Exception {
        for (Chapter e : title.getChapters()) {
            e.accept(this);
        }
    }

    public void visit(Chapter chapter) throws Exception {
        for (Subchapter e : chapter.getSubchapters()) {
            e.accept(this);
        }
    }

    public void visit(Subchapter subchapter) throws Exception {
        for (Part e : subchapter.getParts()) {
            e.accept(this);
        }
    }

    public void visit(Part part) throws Exception {
        for (Section e : part.getSections()) {
            e.accept(this);
        }
        for (Subpart subpart : part.getSubparts()) {
            subpart.accept(this);
        }
    }

    public void visit(Subpart subpart) throws Exception {
        for (Section e : subpart.getSections()) {
            e.accept(this);
        }
    }

    public void visit(Section section) throws Exception {
        for (Block e : section.getBlocks()) {
            e.accept(this);
        }
    }

    @Override
    public void visit(Paragraph paragraph) throws Exception {
        for (Text e : paragraph.getTexts()) {
            e.accept(this);
        }
    }

    @Override
    public void visit(OrderedList list) throws Exception {
        for (ListItem e : list.getItems()) {
            e.accept(this);
        }
    }

    @Override
    public void visit(ListItem item) throws Exception {
        item.getParagraph().accept(this);
        if ( item.getSublist() != null ) {
            item.getSublist().accept(this);
        }
    }

    @Override
    public void visit(Text text) throws Exception {

    }

    @Override
    public void visit(EmphasizedText text) throws Exception {

    }
}

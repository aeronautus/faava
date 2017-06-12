package org.mcwhirter.cfr.visitor;

import org.mcwhirter.cfr.model.Block;
import org.mcwhirter.cfr.model.CFR;
import org.mcwhirter.cfr.model.Chapter;
import org.mcwhirter.cfr.model.ColumnHeader;
import org.mcwhirter.cfr.model.Document;
import org.mcwhirter.cfr.model.EmphasizedText;
import org.mcwhirter.cfr.model.ListItem;
import org.mcwhirter.cfr.model.OrderedList;
import org.mcwhirter.cfr.model.Paragraph;
import org.mcwhirter.cfr.model.Part;
import org.mcwhirter.cfr.model.RowEntry;
import org.mcwhirter.cfr.model.Section;
import org.mcwhirter.cfr.model.Subchapter;
import org.mcwhirter.cfr.model.SubjectGroup;
import org.mcwhirter.cfr.model.Subpart;
import org.mcwhirter.cfr.model.Table;
import org.mcwhirter.cfr.model.TableHeader;
import org.mcwhirter.cfr.model.TableRow;
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
        for (SubjectGroup e : subpart.getSubjectGroups()) {
            e.accept(this);

        }
        for (Section e : subpart.getSections()) {
            e.accept(this);
        }
    }

    public void visit(SubjectGroup subjectGroup) throws Exception {
        for (Section e : subjectGroup.getSections()) {
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
        if (item.getSublist() != null) {
            item.getSublist().accept(this);
        }
    }

    @Override
    public void visit(Text text) throws Exception {

    }

    @Override
    public void visit(EmphasizedText text) throws Exception {

    }

    @Override
    public void visit(Table table) throws Exception {
        TableHeader header = table.getHeader();

        if ( header != null ) {
            header.accept(this);
        }

        for (TableRow e : table.getRows()) {
            e.accept(this);
        }
    }

    @Override
    public void visit(TableHeader tableHeader) throws Exception {
        for (ColumnHeader e : tableHeader.getHeaders()) {
            e.accept(this);
        }
    }

    @Override
    public void visit(ColumnHeader columnHeader) throws Exception {
        Paragraph content = columnHeader.getContent();
        if (content != null) {
            content.accept(this);
        }

        for (ColumnHeader e : columnHeader.getHeaders()) {
            e.accept(this);
        }
    }

    @Override
    public void visit(TableRow tableRow) throws Exception {
        for (RowEntry e : tableRow.getEntries()) {
            e.accept(this);
        }
    }

    @Override
    public void visit(RowEntry rowEntry) throws Exception {
        Paragraph content = rowEntry.getContent();
        if (content != null) {
            content.accept(this);
        }
    }
}

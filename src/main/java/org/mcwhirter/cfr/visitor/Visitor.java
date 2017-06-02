package org.mcwhirter.cfr.visitor;

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
public interface Visitor {

    void visit(Document doc) throws Exception;
    void visit(CFR cfr) throws Exception;
    void visit(Title title) throws Exception;
    void visit(Chapter chapter) throws Exception;
    void visit(Subchapter subchapter) throws Exception;
    void visit(Part part) throws Exception;
    void visit(Subpart part) throws Exception;
    void visit(Section section) throws Exception;
    void visit(Paragraph paragraph) throws Exception;
    void visit(OrderedList list) throws Exception;
    void visit(ListItem item) throws Exception;
    void visit(Text text) throws Exception;
    void visit(EmphasizedText text) throws Exception;
}

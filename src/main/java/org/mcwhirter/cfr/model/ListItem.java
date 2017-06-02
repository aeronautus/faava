package org.mcwhirter.cfr.model;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 6/2/17.
 */
public class ListItem implements BaseModel {

    public ListItem(Paragraph paragraph) {
        Text firstText = paragraph.getTexts().get(0);
        Text cleanText = new Text( firstText.toString().replaceAll( "^(.+?) ", ""));
        paragraph.getTexts().set(0, cleanText);
        this.paragraph = paragraph;
    }

    public Paragraph getParagraph() {
        return this.paragraph;
    }

    public void setSublist(OrderedList sublist) {
        this.sublist = sublist;
    }

    public OrderedList getSublist() {
        return this.sublist;
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);
    }

    private Paragraph paragraph;
    private OrderedList sublist;
}

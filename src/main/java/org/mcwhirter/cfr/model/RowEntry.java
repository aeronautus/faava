package org.mcwhirter.cfr.model;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 6/7/17.
 */
public class RowEntry implements BaseModel {

    public RowEntry() {

    }

    public void setContent(Paragraph content) {
        this.content = content;
    }

    public Paragraph getContent() {
        return this.content;
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit( this );
    }

    private Paragraph content;
}

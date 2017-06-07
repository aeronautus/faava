package org.mcwhirter.cfr.model;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 6/1/17.
 */
public class Text implements BaseModel {

    public Text(String text) {
        this.text = text;
    }

    public void append(String text) {
        this.text = this.text + text;
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit( this );
    }

    public String toString() {
        return this.text;
    }

    private String text;
}

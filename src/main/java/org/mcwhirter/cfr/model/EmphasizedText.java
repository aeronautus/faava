package org.mcwhirter.cfr.model;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 6/1/17.
 */
public class EmphasizedText extends Text {
    public EmphasizedText(String text) {
        super(text);
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);
    }
}

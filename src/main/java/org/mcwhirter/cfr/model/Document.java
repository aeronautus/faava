package org.mcwhirter.cfr.model;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 5/31/17.
 */
public class Document implements BaseModel {

    public void setCFR(CFR cfr) {
        this.cfr = cfr;
    }

    public CFR getCFR() {
        return this.cfr;
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);
    }

    private CFR cfr;
}

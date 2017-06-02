package org.mcwhirter.cfr.model;

import java.util.ArrayList;
import java.util.List;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 5/31/17.
 */
public class Paragraph implements BaseModel {

    public void addText(Text text) {
        this.texts.add( text );
    }

    public List<Text> getTexts() {
        return this.texts;
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);
    }

    private List<Text> texts = new ArrayList<>();
}

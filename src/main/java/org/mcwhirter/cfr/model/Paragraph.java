package org.mcwhirter.cfr.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 5/31/17.
 */
public class Paragraph implements BaseModel, Block {

    public void addText(Text text) {
        this.texts.add( text );
    }

    public List<Text> getTexts() {
        return this.texts;
    }

    public String asSimpleString() {
        return String.join( "", this.texts.stream().map(e-> e.toString()).collect(Collectors.toList()));
    }

    public String toString() {
        return asSimpleString();
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);
    }

    private List<Text> texts = new ArrayList<>();
}

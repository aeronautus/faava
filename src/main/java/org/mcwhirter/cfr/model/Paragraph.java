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
        if (this.texts.isEmpty()) {
            this.texts.add(text);
            return;
        }

        Text lastText = this.texts.get(this.texts.size() - 1);
        if (lastText.getClass() == text.getClass()) {
            lastText.append(text.toString());
        } else {
            this.texts.add(text);
        }
    }

    public List<Text> getTexts() {
        return this.texts;
    }

    public void setTexts(List<Text> texts) {
        this.texts.clear();
        this.texts.addAll(texts);
    }

    public String asSimpleString() {
        return String.join("", this.texts.stream().map(e -> e.toString()).collect(Collectors.toList())).trim();
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

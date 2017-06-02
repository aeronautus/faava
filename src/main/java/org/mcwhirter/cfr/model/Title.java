package org.mcwhirter.cfr.model;

import java.util.ArrayList;
import java.util.List;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 5/31/17.
 */
public class Title implements BaseModel {

    public void addChapter(Chapter chapter) {
        this.chapters.add(chapter);
    }

    public List<Chapter> getChapters() {
        return this.chapters;
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);
    }

    private List<Chapter> chapters = new ArrayList<>();
}

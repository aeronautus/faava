package org.mcwhirter.cfr.parser;

import org.mcwhirter.cfr.model.Chapter;

/**
 * Created by bob on 5/31/17.
 */
public class ChapterParser extends Parser<Chapter> {

    public ChapterParser() {
        super(Tags.CHAPTER, Chapter.class);
        tag(new SubChapterParser(), Chapter::addSubchapter);
    }

}

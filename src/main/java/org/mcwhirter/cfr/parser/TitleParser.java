package org.mcwhirter.cfr.parser;

import org.mcwhirter.cfr.model.Title;

/**
 * Created by bob on 5/31/17.
 */
public class TitleParser extends Parser<Title> {

    public TitleParser() {
        super(Tags.TITLE, Title.class);
        //register(new ChapterParser());
        register(new ChapterParser(), Title::addChapter);
    }

}

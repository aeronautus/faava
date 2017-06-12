package org.mcwhirter.cfr.parser;

import org.mcwhirter.cfr.model.Subchapter;

/**
 * Created by bob on 5/31/17.
 */
public class SubChapterParser extends Parser<Subchapter> {

    public SubChapterParser() {
        super(Tags.SUBCHAP, Subchapter.class);
        tag(new PartParser(), Subchapter::addPart);
        tag(Tags.HD, Subchapter::setTitle);
    }
}

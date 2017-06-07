package org.mcwhirter.cfr.parser;

import org.mcwhirter.cfr.model.Section;

/**
 * Created by bob on 5/31/17.
 */
public class SectionParser extends Parser<Section> {

    public SectionParser() {
        super(Tags.SECTION, Section.class);
        register(new TextParser(Tags.P), Section::addBlock);
        register(Tags.SECTNO, Section::setSectionNumber);
        register(new TextParser(Tags.SUBJECT), Section::setSubject);
        register(Tags.RESERVED, (section, txt) -> section.setReserved(true));
    }
}

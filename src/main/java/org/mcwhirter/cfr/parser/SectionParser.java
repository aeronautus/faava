package org.mcwhirter.cfr.parser;

import org.mcwhirter.cfr.model.Section;

/**
 * Created by bob on 5/31/17.
 */
public class SectionParser extends Parser<Section> {

    public SectionParser() {
        super(Tags.SECTION, Section.class);
        tag(new TextParser(Tags.P), Section::addBlock);
        tag(Tags.SECTNO, Section::setSectionNumber);
        tag(new TextParser(Tags.SUBJECT), Section::setSubject);
        tag(Tags.RESERVED, (section, txt) -> section.setReserved(true));
        tag(new TableParser(), Section::addBlock);
    }
}

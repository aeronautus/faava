package org.mcwhirter.cfr.parser;

import org.mcwhirter.cfr.model.Section;

/**
 * Created by bob on 5/31/17.
 */
public class SectionParser extends Parser<Section> {

    public SectionParser() {
        super(Tags.SECTION, Section.class);
        register(new ParagraphParser(), Section::addParagraph);
        register(Tags.SECTNO, Section::setSectionNumber);
        register(Tags.SUBJECT, Section::setSubject);
        register(Tags.RESERVED, (section, txt) -> section.setReserved(true));
    }
}

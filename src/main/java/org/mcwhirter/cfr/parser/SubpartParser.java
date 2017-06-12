package org.mcwhirter.cfr.parser;

import org.mcwhirter.cfr.model.Subpart;

/**
 * Created by bob on 5/31/17.
 */
public class SubpartParser extends Parser<Subpart> {

    public SubpartParser() {
        super(Tags.SUBPART, Subpart.class);
        tag(new SectionParser(), Subpart::addSection);
        tag(new SubjectGroupParser(), Subpart::addSubjectGroup);
        tag(Tags.HD, Subpart::setTitle);
    }

}

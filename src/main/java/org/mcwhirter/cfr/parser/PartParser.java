package org.mcwhirter.cfr.parser;

import org.mcwhirter.cfr.model.Part;

/**
 * Created by bob on 5/31/17.
 */
public class PartParser extends Parser<Part> {

    public PartParser() {
        super(Tags.PART, Part.class);
        register(new SectionParser(), Part::addSection);
        register(new SubpartParser(), Part::addSubpart);
        register(Tags.HD, Part::setTitle);
    }

}

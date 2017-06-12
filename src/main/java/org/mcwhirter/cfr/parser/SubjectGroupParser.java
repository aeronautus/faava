package org.mcwhirter.cfr.parser;

import org.mcwhirter.cfr.model.SubjectGroup;

/**
 * Created by bob on 5/31/17.
 */
public class SubjectGroupParser extends Parser<SubjectGroup> {

    public SubjectGroupParser() {
        super(Tags.SUBJGRP, SubjectGroup.class);
        tag(new SectionParser(), SubjectGroup::addSection);
        //tag(Tags.HD, SubjectGroup::setTitle);
        tag(new TextParser(Tags.HD), SubjectGroup::setTitle);
    }

}

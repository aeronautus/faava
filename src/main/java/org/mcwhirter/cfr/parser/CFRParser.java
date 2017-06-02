package org.mcwhirter.cfr.parser;

import org.mcwhirter.cfr.model.CFR;

/**
 * Created by bob on 5/31/17.
 */
public class CFRParser extends Parser<CFR> {

    public CFRParser() {
        super( Tags.CFRDOC, CFR.class);
        //register(new TitleParser() );
        register(new TitleParser(), CFR::addTitle);
    }

}

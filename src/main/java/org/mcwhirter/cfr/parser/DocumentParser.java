package org.mcwhirter.cfr.parser;

import org.mcwhirter.cfr.model.Document;

/**
 * Created by bob on 5/31/17.
 */
public class DocumentParser extends Parser<Document> {

    public DocumentParser() {
        super( Tags.CFRDOC, Document.class);
        //tag(new CFRParser(), (doc, cfr)->{
            //doc.setCFR( cfr );
        //});
        tag(new CFRParser(), Document::setCFR);
    }

}

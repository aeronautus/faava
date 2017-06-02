package org.mcwhirter.cfr.parser;

import org.mcwhirter.cfr.model.Document;

/**
 * Created by bob on 5/31/17.
 */
public class DocumentParser extends Parser<Document> {

    public DocumentParser() {
        super( Tags.CFRDOC, Document.class);
        //register(new CFRParser(), (doc, cfr)->{
            //doc.setCFR( cfr );
        //});
        register(new CFRParser(), Document::setCFR);
    }

}

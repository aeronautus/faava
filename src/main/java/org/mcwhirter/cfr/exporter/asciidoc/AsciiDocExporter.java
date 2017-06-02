package org.mcwhirter.cfr.exporter.asciidoc;

import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.mcwhirter.cfr.model.Document;

/**
 * Created by bob on 6/1/17.
 */
public class AsciiDocExporter {

    public AsciiDocExporter() {

    }

    public void export(Document doc) throws Exception {
        Path dir = Paths.get("output");
        AsciiDocVisitor visitor = new AsciiDocVisitor(dir);
        doc.accept(visitor);

        SummaryVisitor summary = new SummaryVisitor(dir);
        doc.accept(summary);

        try (FileOutputStream readme = new FileOutputStream( dir.resolve( "README.md" ).toFile() ) ) {
            // nothing
        }

        //System.err.println( visitor.output );
    }
}

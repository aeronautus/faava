package org.mcwhirter.cfr.exporter.gitbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.mcwhirter.cfr.exporter.asciidoc.AsciiDocVisitor;
import org.mcwhirter.cfr.exporter.asciidoc.RewritingVisitor;
import org.mcwhirter.cfr.model.Document;

/**
 * Created by bob on 6/1/17.
 */
public class GitBookExporter {

    public GitBookExporter() {

    }

    public void export(Document doc) throws Exception {
        Path dir = Paths.get("../cfr14");

        try (DirectoryStream<Path> contents = Files.newDirectoryStream(dir)) {
            for (Path path : contents) {
                if ( path.getFileName().toString().startsWith(".")) {
                    // skip;
                } else {
                    System.err.println( "delete: " + path );
                    //Files.delete( path );
                }
            }
        } catch (IOException ex) {}


        RewritingVisitor rewrite = new RewritingVisitor();
        doc.accept(rewrite);

        AsciiDocVisitor visitor = new AsciiDocVisitor(dir);
        doc.accept(visitor);

        SummaryVisitor summary = new SummaryVisitor(dir);
        doc.accept(summary);

        emit( dir, "README.md");
        emit( dir, "styles/website.css" );
        emit( dir, "book.json" );

    }

    protected void emit(Path dir, String path) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);

        Path file = dir.resolve(path);
        Files.createDirectories( file.getParent() );

        Files.copy(in, file );
    }
}

package org.mcwhirter.cfr.exporter.gitbook;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.mcwhirter.cfr.exporter.asciidoc.AsciiDocVisitor;
import org.mcwhirter.cfr.exporter.asciidoc.RewritingVisitor;
import org.mcwhirter.cfr.model.Chapter;
import org.mcwhirter.cfr.model.Document;

/**
 * Created by bob on 6/1/17.
 */
public class GitBookExporter {

    public GitBookExporter() {

    }

    public void export(Document doc) throws Exception {
        Path base = Paths.get("output");
        RewritingVisitor rewrite = new RewritingVisitor();

        doc.accept(rewrite);

        /*
        try (DirectoryStream<Path> contents = Files.newDirectoryStream(dir)) {
            for (Path path : contents) {
                if (Files.isDirectory(path) && path.getFileName().toString().startsWith(".")) {
                    // skip;
                } else {
                    delete(path);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        */


        AsciiDocVisitor visitor = new AsciiDocVisitor(base);
        doc.accept(visitor);

        //SummaryVisitor summary = new SummaryVisitor(dir);
        //doc.accept(summary);

        //emit(dir, "README.md");

        //emit(dir, "book.json");

        //emit(dir, "styles/website.css");

        //emit(dir, ".gitignore");

    }

    protected void delete(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> contents = Files.newDirectoryStream(path)) {
                for (Path each : contents) {
                    if (Files.isDirectory(each) && each.getFileName().toString().startsWith(".")) {
                        return;
                    } else {
                        delete(each);
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        Files.delete(path);
    }

    protected void emit(Path dir, String path) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);

        Path file = dir.resolve(path);
        Files.createDirectories(file.getParent());

        Files.copy(in, file);
    }
}

package org.mcwhirter.cfr.exporter;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Stack;

import org.mcwhirter.cfr.model.Identified;
import org.mcwhirter.cfr.visitor.DefaultVisitor;

/**
 * Created by bob on 6/2/17.
 */
public class BaseVisitor extends DefaultVisitor {

    protected BaseVisitor(Path dir) {
        this.dir = dir;
    }

    protected void context(Identified identified, Executable e) throws Exception {
        try {
            this.context.push(identified);
            e.run();
        } finally {
            this.context.pop();
        }
    }

    protected Path partDir() {
        return this.dir
                .resolve(this.context.get(0).id());
    }

    protected Path filename() {
        /*
        Path file = this.dir;
        for (Identified identified : this.context) {
            file = file.resolve( identified.id() );
        }

        return file.resolve( "index.adoc");
        */
        Path file = partDir().resolve(this.context.get(this.context.size() - 1).id() + ".adoc");
        return file;
    }

    protected String link() {
        return this.context.get(this.context.size() - 1).id() + ".adoc";
    }

    protected int depth() {
        return this.context.size();
    }

    protected void emit(Path dir, String path) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(path);

        Path file = dir.resolve(path);
        Files.createDirectories(file.getParent());

        Files.copy(in, file);
    }

    protected void delete(Path path, boolean deleteSelf) throws IOException {
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> contents = Files.newDirectoryStream(path)) {
                for (Path each : contents) {
                    if (Files.isDirectory(each) && each.getFileName().toString().startsWith(".")) {
                        return;
                    } else {
                        delete(each, true);
                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if ( deleteSelf ) {
            Files.delete(path);
        }
    }

    protected final Path dir;

    private Stack<Identified> context = new Stack<>();

}

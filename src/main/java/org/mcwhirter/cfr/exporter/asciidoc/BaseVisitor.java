package org.mcwhirter.cfr.exporter.asciidoc;

import java.nio.file.Path;
import java.util.Stack;

import org.mcwhirter.cfr.model.Identified;
import org.mcwhirter.cfr.visitor.DefaultVisitor;

/**
 * Created by bob on 6/2/17.
 */
public class BaseVisitor extends DefaultVisitor {

    BaseVisitor(Path dir) {
        this.dir = dir;
    }

    void context(Identified identified, Executable e) throws Exception {
        try {
            this.context.push( identified );
            e.run();
        } finally {
            this.context.pop();
        }
    }

    Path filename() {
        Path file = this.dir;
        for (Identified identified : this.context) {
            file = file.resolve( identified.id() );
        }

        return file.resolve( "index.adoc");
    }

    String link() {
        StringBuilder link = new StringBuilder();
        for (Identified identified : this.context) {
            link.append( "/" + identified.id() );
        }

        return link.append( "/index.adoc").toString();
    }

    protected final Path dir;

    private Stack<Identified> context = new Stack<>();

}

package org.mcwhirter.cfr.exporter.asciidoc;

import java.io.FileOutputStream;
import java.nio.file.Path;

import org.mcwhirter.cfr.model.Document;
import org.mcwhirter.cfr.model.Part;
import org.mcwhirter.cfr.model.Subpart;
import org.mcwhirter.cfr.visitor.DefaultVisitor;

/**
 * Created by bob on 6/1/17.
 */
public class SummaryVisitor extends BaseVisitor {

    StringBuilder output = new StringBuilder();

    private int headerDepth = 0;

    public SummaryVisitor(Path dir) {
        super(dir);
    }

    void print(String str) {
        output.append(str);
    }

    void println(String str) {
        output.append(str).append("\n");
    }

    void println() {
        output.append("\n");
    }


    @Override
    public void visit(Document doc) throws Exception {
        println("# Summary");
        println();
        super.visit(doc);
        Path summary = this.dir.resolve("SUMMARY.md");
        try (FileOutputStream out = new FileOutputStream(summary.toFile())) {
            out.write(this.output.toString().getBytes());
        }
    }

    @Override
    public void visit(Part part) throws Exception {
        if (part.getTitle() == null) {
            return;
        }
        context(part, () -> {
            if (part.getSubparts().isEmpty()) {
                println("* [" + part.id() + " " + part.getTitle() + "](" + link() + ")");
            } else {
                println();
                println("### " + part.id() + " " + part.getTitle() + "");
            }
            super.visit(part);
        });
    }

    public void visit(Subpart subpart) throws Exception {
        if (subpart.getTitle() == null) {
            return;
        }
        context(subpart, () -> {
            println("* [" + subpart.id() + " " + subpart.getTitle() + "](" + link() + ")");
        });
    }

}

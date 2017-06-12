package org.mcwhirter.cfr.exporter.gitbook;

import java.io.FileOutputStream;
import java.nio.file.Path;

import org.mcwhirter.cfr.exporter.BaseVisitor;
import org.mcwhirter.cfr.model.Document;
import org.mcwhirter.cfr.model.Part;
import org.mcwhirter.cfr.model.Section;
import org.mcwhirter.cfr.model.SubjectGroup;
import org.mcwhirter.cfr.model.Subpart;

/**
 * Created by bob on 6/1/17.
 */
public class SummaryVisitor extends BaseVisitor {

    StringBuilder output = new StringBuilder();

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
        super.visit(doc);
    }

    @Override
    public void visit(Part part) throws Exception {
        if (part.getTitle() == null) {
            return;
        }

        println("# Summary");
        println();

        context(part, () -> {
            //println("### " + part.id() + " " + part.getTitle() + "");
            super.visit(part);
            //println();
        });

        Path summary = this.dir.resolve("SUMMARY.md");
        try (FileOutputStream out = new FileOutputStream(summary.toFile())) {
            out.write(this.output.toString().getBytes());
        }
    }

    public void visit(Subpart subpart) throws Exception {
        if (subpart.getTitle() == null) {
            return;
        }
        context(subpart, () -> {
            println("### " + subpart.id() + " " + subpart.getTitle() + "");
            super.visit(subpart);
            println();
        });
    }

    @Override
    public void visit(SubjectGroup subjectGroup) throws Exception {
        println("#### " + subjectGroup.getTitle() );
        super.visit(subjectGroup);
        println();
    }

    @Override
    public void visit(Section section) throws Exception {
        context(section, () -> {
            if ( section.isReserved() ) {
                println("* [" + section.id() + " - Reserved](" + link() + ")");
            } else {
                println("* [" + section.id() + " - " + section.getSubject() + "](" + link() + ")");
            }
            super.visit(section);
        });
    }
}

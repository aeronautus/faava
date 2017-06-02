package org.mcwhirter.cfr.exporter.asciidoc;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import org.mcwhirter.cfr.model.EmphasizedText;
import org.mcwhirter.cfr.model.Paragraph;
import org.mcwhirter.cfr.model.Part;
import org.mcwhirter.cfr.model.Section;
import org.mcwhirter.cfr.model.Subchapter;
import org.mcwhirter.cfr.model.Subpart;
import org.mcwhirter.cfr.model.Text;

/**
 * Created by bob on 6/1/17.
 */
public class AsciiDocVisitor extends BaseVisitor {

    PrintWriter output;

    private int headerDepth = 0;

    public AsciiDocVisitor(Path dir) {
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

    void header(String text) {
        try {
            ++this.headerDepth;
            String line = "";
            for (int i = 0; i < this.headerDepth; ++i) {
                line += "#";
            }

            line += " ";
            println(line + text);
            if (this.headerDepth == 1) {
                println(":toc:");
            }
            println();
        } finally {
            --this.headerDepth;
        }
    }

    void file(Executable r) throws Exception {
        if (this.output == null) {
            Path file = filename();
            Files.createDirectories(file.getParent());
            try (PrintWriter o = new PrintWriter(new FileOutputStream(file.toFile()))) {
                this.output = o;
                this.headerDepth = 0;
                r.run();
            } finally {
                this.output = null;
            }
        } else {
            r.run();
        }
    }

    void headedBlock(String header, Executable r) throws Exception {
        try {
            header(header);
            ++this.headerDepth;
            r.run();
        } finally {
            --this.headerDepth;
        }
    }

    @Override
    public void visit(Subchapter subchapter) throws Exception {
        super.visit(subchapter);
    }

    @Override
    public void visit(Part part) throws Exception {
        if (part.getTitle() == null) {
            return;
        }
        context(part, () -> {
            if (part.getSubparts().isEmpty()) {
                file(() -> {
                    headedBlock(part.getTitle(), () -> {
                        super.visit(part);
                    });
                });
            } else {
                super.visit(part);
            }
        });
    }

    @Override
    public void visit(Subpart subpart) throws Exception {
        if (subpart.getTitle() == null) {
            return;
        }
        context(subpart, () -> {
            file(() -> {
                headedBlock(subpart.getTitle(), () -> {
                    super.visit(subpart);
                });
            });
        });
    }

    @Override
    public void visit(Section section) throws Exception {
        headedBlock(section.getSectionNumber(), () -> {
            if (section.isReserved()) {
                header("Reserved");
            } else {
                headedBlock(section.getSubject(), () -> {
                    for (Paragraph e : section.getParagraphs()) {
                        e.accept(this);
                    }
                });
            }
        });
        super.visit(section);
    }

    @Override
    public void visit(Paragraph paragraph) throws Exception {
        super.visit(paragraph);
        println();
        println();
    }

    @Override
    public void visit(Text text) throws Exception {
        print(text.toString());
        super.visit(text);
    }

    @Override
    public void visit(EmphasizedText text) throws Exception {
        print("*" + text + "*");
        super.visit(text);
    }
}

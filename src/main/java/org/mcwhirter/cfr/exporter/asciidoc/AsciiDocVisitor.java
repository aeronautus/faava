package org.mcwhirter.cfr.exporter.asciidoc;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import org.mcwhirter.cfr.exporter.BaseVisitor;
import org.mcwhirter.cfr.exporter.Executable;
import org.mcwhirter.cfr.exporter.gitbook.SummaryVisitor;
import org.mcwhirter.cfr.model.Block;
import org.mcwhirter.cfr.model.EmphasizedText;
import org.mcwhirter.cfr.model.ListItem;
import org.mcwhirter.cfr.model.OrderedList;
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
            delete(partDir(), false);
            if (!part.getSections().isEmpty()) {
                file(() -> {
                    headedBlock(part.getTitle(), () -> {
                        for (Section section : part.getSections()) {
                            section.accept(this);
                        }
                    });
                });
            }
            for (Subpart subpart : part.getSubparts()) {
                subpart.accept(this);
            }
            //super.visit(part);

            SummaryVisitor summary = new SummaryVisitor(partDir());
            part.accept(summary);

            emit(dir, "README.md");
            emit(dir, "book.json");
            emit(dir, "styles/website.css");
            emit(dir, ".gitignore");
        });
    }

    @Override
    public void visit(Subpart subpart) throws Exception {
        if (subpart.getTitle() == null) {
            return;
        }
        context(subpart, () -> {
            //file(() -> {
            //headedBlock(subpart.getTitle(), () -> {
            super.visit(subpart);
            //});
            //});
            //super.visit(subpart);
        });
    }

    String inline(Paragraph p) {
        StringBuilder str = new StringBuilder();
        for (Text text : p.getTexts()) {
            if (text instanceof EmphasizedText) {
                str.append("*").append(text.toString()).append("*");
            } else {
                str.append(text.toString());
            }
        }

        return str.toString();
    }

    @Override
    public void visit(Section section) throws Exception {
        context(section, () -> {
            file(() -> {
                if (section.isReserved()) {
                    headedBlock(section.getSectionNumber() + " - Reserved", () -> {
                        println();
                        println("Reserved");
                        println();
                    });
                } else {
                    headedBlock(section.getSectionNumber() + " - " + inline(section.getSubject()), () -> {
                        for (Block e : section.getBlocks()) {
                            e.accept(this);
                        }
                    });
                }
            });
        });
    }

    @Override
    public void visit(Paragraph paragraph) throws Exception {
        super.visit(paragraph);
        println();
        if (this.listDepth == 0) {
            println();
        }
    }

    private int listDepth = 0;

    @Override
    public void visit(OrderedList list) throws Exception {
        try {
            ++this.listDepth;
            println("[" + list.getType().getName() + "]");
            super.visit(list);
        } finally {
            --this.listDepth;
        }
        if (this.listDepth == 0) {
            println();
        }
    }

    @Override
    public void visit(ListItem item) throws Exception {
        for (int i = 0; i < this.listDepth; ++i) {
            print(".");
        }
        print(" ");
        super.visit(item);
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

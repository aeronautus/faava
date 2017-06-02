package org.mcwhirter.cfr.exporter.asciidoc;

import java.util.ArrayList;
import java.util.List;

import org.mcwhirter.cfr.model.Block;
import org.mcwhirter.cfr.model.ListItem;
import org.mcwhirter.cfr.model.OrderedList;
import org.mcwhirter.cfr.model.Paragraph;
import org.mcwhirter.cfr.model.Section;
import org.mcwhirter.cfr.visitor.DefaultVisitor;

/**
 * Created by bob on 6/2/17.
 */
public class RewritingVisitor extends DefaultVisitor {

    @Override
    public void visit(Section section) throws Exception {
        if (section.getBlocks().isEmpty()) {
            return;
        }

        List<Block> blocks = section.getBlocks();
        List<Block> rewritten = new ArrayList<>();

        OrderedList currentList = null;

        for (Block block : blocks) {
            if (block instanceof Paragraph) {
                if (currentList != null) {
                    OrderedList result = currentList.insert((Paragraph) block);
                    if (result == null) {
                        rewritten.add(block);
                    } else if (result != currentList) {
                        currentList = result;
                    }
                } else {
                    if (ListMatcher.LOWERCASE_ALPHABET.isNext(0, ((Paragraph) block).asSimpleString())) {
                        currentList = new OrderedList();
                        currentList.addItem(new ListItem((Paragraph) block));
                        rewritten.add(currentList);
                    } else {
                        rewritten.add(block);
                    }
                }
            } else {
                //System.err.println( "adding non-para block: " + block);
                rewritten.add(block);
            }
        }

        section.getBlocks().clear();
        section.getBlocks().addAll(rewritten);
    }

}

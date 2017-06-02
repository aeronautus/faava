package org.mcwhirter.cfr.model;

import java.util.ArrayList;
import java.util.List;

import org.mcwhirter.cfr.exporter.asciidoc.ListMatcher;
import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 6/2/17.
 */
public class OrderedList implements BaseModel, Block {

    public OrderedList() {
        this(null);
    }

    public OrderedList(OrderedList parent) {
        this.parent = parent;
        this.matcher = ListMatcher.forDepth(depth());
    }

    public OrderedList getParent() {
        return this.parent;
    }

    public int depth() {
        if (this.parent == null) {
            return 0;
        }
        return 1 + this.parent.depth();
    }

    public OrderedList insert(Paragraph para) {
        ListMatcher next = this.matcher.getNextDeeper();
        if (next != null && next.isNext(0, para.asSimpleString())) {
            OrderedList list = new OrderedList(this);
            list.addItem(new ListItem(para));
            this.items.get(this.items.size() - 1).setSublist(list);
            return list;
        }

        if (this.matcher.isNext(this.items.size(), para.asSimpleString())) {
            this.items.add(new ListItem(para));
            return this;
        }

        if (this.parent != null) {
            return this.parent.insert(para);
        }

        return null;
    }

    public void addItem(ListItem item) {
        this.items.add(item);
    }

    public List<ListItem> getItems() {
        return this.items;
    }

    @Override
    public void accept(Visitor visitor) throws Exception {
        visitor.visit(this);
    }

    private ListMatcher matcher;

    private OrderedList parent;

    private List<ListItem> items = new ArrayList<>();
}

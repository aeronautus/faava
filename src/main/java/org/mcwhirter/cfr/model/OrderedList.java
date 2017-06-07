package org.mcwhirter.cfr.model;

import java.util.ArrayList;
import java.util.List;

import org.mcwhirter.cfr.exporter.asciidoc.OrderedListType;
import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 6/2/17.
 */
public class OrderedList implements BaseModel, Block {

    public OrderedList(OrderedListType type) {
        this(type, null);
    }

    public OrderedList(OrderedListType type, OrderedList parent) {
        this.type = type;
        this.parent = parent;
    }

    public OrderedList getParent() {
        return this.parent;
    }

    public OrderedListType getType() {
        return this.type;
    }

    public String toString() {
        return "[ol: " + this.type.getClass().getSimpleName() + "]";
    }

    public OrderedList insert(Paragraph para) {

        if (this.type.isNext(this.items.size(), para.asSimpleString())) {
            Paragraph[] paras = burst(this.type, para);
            if (paras == null) {
                this.items.add(new ListItem(para));
                return this;
            } else {
                this.items.add(new ListItem(paras[0]));
                return insert(paras[1]);
            }
        }

        OrderedListType next = OrderedListType.nextType(this.type, para.asSimpleString());
        if (next != null) {
            OrderedList list = new OrderedList(next, this);
            Paragraph[] paras = burst(next, para);
            if (paras == null) {
                list.addItem(new ListItem(para));
                this.items.get(this.items.size() - 1).setSublist(list);
                return list;
            } else {
                list.addItem(new ListItem(paras[0]));
                this.items.get(this.items.size() - 1).setSublist(list);
                OrderedList result = list.insert(paras[1]);
                return result;
            }
        }

        if (this.parent != null) {
            return this.parent.insert(para);
        }

        return null;
    }

    Paragraph[] burst(OrderedListType type, Paragraph orig) {
        List<Text> texts = orig.getTexts();

        Paragraph[] paras = null;

        for (int i = 1; i < texts.size(); ++i) {
            if (OrderedListType.nextType(type, texts.get(i).toString().trim()) != null) {
                paras = new Paragraph[2];
                paras[0] = new Paragraph();
                paras[0].setTexts(texts.subList(0, i));
                paras[1] = new Paragraph();
                paras[1].setTexts(texts.subList(i, texts.size()));
                break;
            }
        }

        return paras;
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

    private OrderedListType type;

    private OrderedList parent;

    private List<ListItem> items = new ArrayList<>();
}

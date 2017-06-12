package org.mcwhirter.cfr.parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;

import org.mcwhirter.cfr.model.Paragraph;

/**
 * Created by bob on 5/31/17.
 */
public abstract class Parser<T> {


    public Parser(QName tag, Class<T> type) {
        this.tag = tag;
        this.type = type;
    }

    protected QName getTag() {
        return this.tag;
    }

    protected Class<T> getType() {
        return this.type;
    }

    protected <U> void tag(Parser<U> parser, BiConsumer<T, U> consumer) {
        this.tagHandlers.put(parser.getTag(), new ParserHandler(parser, consumer));
    }

    protected void tag(QName tag, BiConsumer<T, String> consumer) {
        this.tagHandlers.put(tag, new TextHandler<T>(consumer));
    }

    protected void attribute(String name, BiConsumer<T, String> consumer) {
        this.attrHandlers.put(name, consumer);
    }

    protected void intAttribute(String name, BiConsumer<T, Integer> consumer) {
        attribute(name, (obj, val) -> {
            Integer intVal = Integer.parseInt(val);
            consumer.accept(obj, intVal);
        });
    }

    protected void textContent(BiConsumer<T, Paragraph> consumer) {
        this.textContentHandler = consumer;
    }

    final public T parse(XMLEventReader reader) throws XMLStreamException {
        return parse( null, reader);
    }

    public T parse(XMLEvent trigger, XMLEventReader reader) throws XMLStreamException {
        T obj = createObject();

        if ( trigger != null && trigger.isStartElement() ) {
            Iterator attrIter = trigger.asStartElement().getAttributes();
            while ( attrIter.hasNext() ) {
                Attribute attr = (Attribute) attrIter.next();
                BiConsumer<T, String> consumer = this.attrHandlers.get(attr.getName().getLocalPart());
                if (consumer != null) {
                    consumer.accept(obj, attr.getValue());
                }
            }
        }

        if ( this.textContentHandler != null ) {
            Paragraph content = new TextParser(null).parse(trigger, reader);
            this.textContentHandler.accept(obj, content);
            return obj;
        }

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {

                QName name = event.asStartElement().getName();
                Handler<T> handler = this.tagHandlers.get(name);
                if (handler != null) {
                    handler.handle(obj, event, reader);
                } else {
                    skip(reader);
                }
            } else if (isComplete(event)) {
                break;
            }
        }

        return obj;
    }

    public T createObject() {
        try {
            return this.type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    protected boolean isComplete(XMLEvent event) {
        if (event.getEventType() == XMLEvent.END_ELEMENT) {
            return event.asEndElement().getName().equals(this.tag);
        }

        return false;
    }

    protected void skip(XMLEventReader reader) throws XMLStreamException {
        int depth = 1;

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                ++depth;
            } else if (event.isEndElement()) {
                --depth;
            }

            if (depth == 0) {
                return;
            }
        }
    }

    private final Class<T> type;

    private final QName tag;

    private final Map<QName, Handler<T>> tagHandlers = new HashMap<>();

    private final Map<String, BiConsumer<T, String>> attrHandlers = new HashMap<>();

    private BiConsumer<T, Paragraph> textContentHandler;
}

package org.mcwhirter.cfr.parser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

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


    protected <U> void register(Parser<U> parser, BiConsumer<T, U> consumer) {
        this.handlers.put(parser.getTag(), new ParserHandler(parser, consumer));
    }

    protected void register(QName tag, String methodName) {
        register(tag, (obj, text)->{
            try {
                Method method = this.type.getMethod( methodName, String.class );
                method.invoke( obj, text );
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }

    protected void register(QName tag, BiConsumer<T, String> consumer) {
        this.handlers.put(tag, new TextHandler<T>(consumer));
    }

    public T parse(XMLEventReader reader) throws XMLStreamException {
        T obj = createObject();

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            if (event.isStartElement()) {
                QName name = event.asStartElement().getName();
                Handler<T> handler = this.handlers.get(name);
                if (handler != null) {
                    handler.handle(obj, reader);
                } else {
                    //System.err.println("skip");
                    skip(reader);
                }
            } else if (isComplete(event)) {
                break;
            }
        }

        return obj;
    }

    /*
    protected <V> void handle(XMLEventReader reader, T object, ParserHandler<V,T> parserHandler) throws XMLStreamException {
        V result = parserHandler.parser.parse(reader);
        parserHandler.consumer.accept(object, result);
    }
    */

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

    private final Map<QName, Handler<T>> handlers = new HashMap<>();


}

package org.mcwhirter.cfr.parser;

import java.util.function.BiConsumer;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

/**
 * Created by bob on 5/31/17.
 */
class TextHandler<T> implements Handler<T> {
    TextHandler(BiConsumer<T, String> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void handle(T object, XMLEvent trigger, XMLEventReader reader) throws XMLStreamException {
        String text = reader.getElementText();
        this.consumer.accept(object, text);
    }

    BiConsumer<T, String> consumer;
}

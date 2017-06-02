package org.mcwhirter.cfr.parser;

import java.util.function.BiConsumer;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

/**
 * Created by bob on 5/31/17.
 */
class ParserHandler<U,T> implements Handler<T> {
    ParserHandler(Parser<U> parser, BiConsumer<T, U> consumer) {
        this.parser = parser;
        this.consumer = consumer;
    }

    @Override
    public void handle(T object, XMLEventReader reader) throws XMLStreamException {
        U result = this.parser.parse(reader);
        this.consumer.accept(object, result);
    }

    Parser<U> parser;

    BiConsumer<T, U> consumer;
}

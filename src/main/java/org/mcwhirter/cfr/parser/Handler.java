package org.mcwhirter.cfr.parser;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

/**
 * Created by bob on 5/31/17.
 */
public interface Handler<T> {

    void handle(T object, XMLEventReader reader) throws XMLStreamException;
}

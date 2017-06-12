package org.mcwhirter.cfr.parser;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

/**
 * Created by bob on 5/31/17.
 */
public interface Handler<T> {

    void handle(T object, XMLEvent trigger, XMLEventReader reader) throws XMLStreamException;
}

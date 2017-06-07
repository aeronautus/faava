package org.mcwhirter.cfr.parser;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import org.mcwhirter.cfr.model.EmphasizedText;
import org.mcwhirter.cfr.model.Paragraph;
import org.mcwhirter.cfr.model.Text;

/**
 * Created by bob on 5/31/17.
 */
public class TextParser extends Parser<Paragraph> {

    public TextParser(QName tag) {
        super(tag, Paragraph.class);
    }

    @Override
    public Paragraph parse(XMLEventReader reader) throws XMLStreamException {
        Paragraph para = createObject();

        int depth = 1;
        boolean first = true;
        boolean emphasized = false;
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isStartElement()) {
                if (event.asStartElement().getName().equals(Tags.E)) {
                    emphasized = true;
                }
                ++depth;
            } else if (event.isEndElement()) {
                if (event.asEndElement().getName().equals(Tags.E)) {
                    emphasized = false;
                }
                --depth;
            } else if (event.isCharacters()) {
                //para.addText(event.asCharacters().getData(), emphasized);
                String chars = event.asCharacters().getData();
                if (first) {
                    chars = stripFront(chars);
                }
                if ( chars == null ) {
                    continue;
                }
                first = false;
                if (emphasized) {
                    para.addText(new EmphasizedText(chars));
                } else {
                    para.addText(new Text(chars));
                }
            }

            if (depth == 0) {
                break;
            }
        }

        return para;
    }

    protected String stripFront(String str) {
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (Character.isWhitespace(c)) {
                continue;
            } else {
                return str.substring(i);
            }
        }

        return null;

    }
}

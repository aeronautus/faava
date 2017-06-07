package org.mcwhirter.cfr;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

import org.mcwhirter.cfr.exporter.gitbook.GitBookExporter;
import org.mcwhirter.cfr.model.Chapter;
import org.mcwhirter.cfr.model.Document;
import org.mcwhirter.cfr.model.Title;
import org.mcwhirter.cfr.parser.DocumentParser;

/**
 * Created by bob on 5/31/17.
 */
public class Main {

    public static void main(String... args) throws Exception {

        Document vol2 = parseVolume2();
        Document vol3 = parseVolume3();

        Title title = vol2.getCFR().getTitles().get(0);

        for (Chapter chapter : vol3.getCFR().getTitles().get(0).getChapters()) {
            title.addChapter( chapter );
        }

        GitBookExporter exporter = new GitBookExporter();
        exporter.export(vol2);
    }

    public static Document parseVolume2() throws XMLStreamException, FileNotFoundException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("CFR-2017-title14-vol2.xml"));

        DocumentParser parser = new DocumentParser();
        Document doc = parser.parse(eventReader);
        return doc;
    }

    public static Document parseVolume3() throws XMLStreamException, FileNotFoundException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(new FileReader("CFR-2017-title14-vol3.xml"));

        DocumentParser parser = new DocumentParser();
        Document doc = parser.parse(eventReader);
        return doc;
    }

}

package org.mcwhirter.cfr;

import java.io.FileReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;

import org.mcwhirter.cfr.exporter.gitbook.GitBookExporter;
import org.mcwhirter.cfr.model.Document;
import org.mcwhirter.cfr.parser.DocumentParser;

/**
 * Created by bob on 5/31/17.
 */
public class Main {

    public static void main(String... args) throws Exception {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader( new FileReader("CFR-2017-title14-vol3.xml"));

        DocumentParser parser = new DocumentParser();
        Document doc = parser.parse(eventReader);

        GitBookExporter exporter = new GitBookExporter();
        exporter.export(doc);
    }
}

package org.mcwhirter.cfr.parser;

import javax.xml.namespace.QName;

/**
 * Created by bob on 5/31/17.
 */
public interface Tags {
    QName CFRDOC = new QName("CFRDOC");
    QName TITLE = new QName("TITLE");
    QName CHAPTER = new QName("CHAPTER");
    QName SUBCHAP = new QName("SUBCHAP");
    QName PART = new QName("PART");
    QName SUBPART = new QName("SUBPART");
    QName SUBJGRP = new QName("SUBJGRP");

    QName SECTION = new QName("SECTION");
    QName SECTNO = new QName("SECTNO");
    QName SUBJECT = new QName("SUBJECT");

    QName P = new QName("P");
    QName E = new QName("E");

    QName HD = new QName("HD");

    QName RESERVED = new QName("RESERVED");

    QName GPOTABLE = new QName("GPOTABLE");
    QName BOXHD = new QName("BOXHD");
    QName CHED = new QName("CHED");
    QName ROW = new QName("ROW");
    QName ENT = new QName("ENT");

}


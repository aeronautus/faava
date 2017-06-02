package org.mcwhirter.cfr.model;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 6/1/17.
 */
public interface BaseModel {

    void accept(Visitor visitor) throws Exception;
}

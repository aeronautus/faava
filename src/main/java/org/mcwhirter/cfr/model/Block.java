package org.mcwhirter.cfr.model;

import org.mcwhirter.cfr.visitor.Visitor;

/**
 * Created by bob on 6/2/17.
 */
public interface Block {
    void accept(Visitor visitor) throws Exception;
}

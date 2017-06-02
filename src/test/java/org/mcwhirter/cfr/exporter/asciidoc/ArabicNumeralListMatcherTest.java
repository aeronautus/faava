package org.mcwhirter.cfr.exporter.asciidoc;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by bob on 6/2/17.
 */
public class ArabicNumeralListMatcherTest {

    @Test
    public void testAll() {
        ArabicNumeralListMatcher matcher = new ArabicNumeralListMatcher(null);

        assertThat( matcher.isNext( 0, "(1)")).isTrue();

        assertThat( matcher.isNext( 0, "(2)")).isFalse();

        assertThat( matcher.isNext( 1, "(2)")).isTrue();

    }
}

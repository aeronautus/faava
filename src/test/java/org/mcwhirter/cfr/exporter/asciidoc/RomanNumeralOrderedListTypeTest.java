package org.mcwhirter.cfr.exporter.asciidoc;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by bob on 6/2/17.
 */
public class RomanNumeralOrderedListTypeTest {

    @Test
    public void testAll() {
        RomanNumeralOrderedListType matcher = new RomanNumeralOrderedListType();

        assertThat( matcher.isNext( 0, "(i)")).isTrue();
        assertThat( matcher.isNext( 0, "(I)")).isFalse();

        assertThat( matcher.isNext( 0, "(ii)")).isFalse();
        assertThat( matcher.isNext( 0, "(II)")).isFalse();

        assertThat( matcher.isNext( 1, "(ii)")).isTrue();
        assertThat( matcher.isNext( 1, "(II)")).isFalse();

        assertThat( matcher.isNext( 3, "(iv)")).isTrue();
        assertThat( matcher.isNext( 3, "(IV)")).isFalse();

    }
}

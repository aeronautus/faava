package org.mcwhirter.cfr.exporter.asciidoc;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by bob on 6/2/17.
 */
public class LowercaseAlphabetOrderedListTypeTest {

    @Test
    public void testAll() {
        LowercaseAlphabetOrderedListType matcher = new LowercaseAlphabetOrderedListType();

        assertThat( matcher.isNext( 0, "(a)")).isTrue();
        assertThat( matcher.isNext( 0, "(A)")).isFalse();

        assertThat( matcher.isNext( 0, "(b)")).isFalse();
        assertThat( matcher.isNext( 0, "(B)")).isFalse();

        assertThat( matcher.isNext( 1, "(b)")).isTrue();
        assertThat( matcher.isNext( 1, "(B)")).isFalse();

    }
}

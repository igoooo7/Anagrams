package com.igor.leskiewicz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnagramFinderTest {

    @Test
    void getNumberOfAnagrams() {
        AnagramFinder af = new AnagramFinder("src/anagram.txt", "src/log.log");
        assertEquals(af.getNumberOfAnagrams(),324);
    }
}
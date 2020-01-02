package com.igor.leskiewicz;

public class Anagram {

    private static int count;
    private String firstWord;
    private String secondWord;

    public Anagram(String firstWord, String secondWord) {
        count++;
        this.firstWord = firstWord;
        this.secondWord = secondWord;
    }

    public String getFirstWord() {
        return firstWord;
    }

    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }

    public String getSecondWord() {
        return secondWord;
    }

    public void setSecondWord(String secondWord) {
        this.secondWord = secondWord;
    }

    public int getCount() {
        return count;
    }

    public String getAnagram() {
        return firstWord + " | " + secondWord;
    }
}

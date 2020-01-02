package com.igor.leskiewicz;

import java.io.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class AnagramFinder {

    private static Logger logger = Logger.getLogger("MyLogger");

    private String file;
    private String log;
    private int numberOfAnagrams = 0;

    public AnagramFinder(String file, String log) {
        this.file = file;
        this.log = log;
        process();
    }

    public int getNumberOfAnagrams() {
        return numberOfAnagrams;
    }

    private void process() {

        if (!log.isEmpty()) {
            try {
                FileHandler fileHandler = new FileHandler("src/Log");
                logger.addHandler(fileHandler);
            } catch (IOException e) {
                System.out.println("Problem with creating a logger!");
            }
        }

        TreeMap<Integer, String> mapWithWords = readFile(file);

        int numberOfWords = 0;
        for (Map.Entry<Integer, String> entry : mapWithWords.entrySet()) {
            int key = entry.getKey();
            System.out.println("Index: " + key + " " + "Word: " + entry.getValue());

            if (key > numberOfWords) {
                numberOfWords++;
            }
            numberOfWords++;
        }
        System.out.println("========================");
        System.out.println("Number of words: " + numberOfWords);

        Date dStart = new Date();
        List<Anagram> anagrams = getAnagrams(mapWithWords);
        Date dEnd = new Date();

        System.out.println();
        System.out.println("Anagrams list:");
        for (Anagram anagram : anagrams) {
            System.out.println(anagram.getAnagram());
        }
        System.out.println("========================");
        if (!anagrams.isEmpty()) {
            numberOfAnagrams = anagrams.get(0).getCount();
            System.out.println("Number of anagrams: " + numberOfAnagrams);
        }
        System.out.println("Time: " + (dEnd.getTime() - dStart.getTime()) / 1000);

    }

    private TreeMap<Integer, String> readFile(String fileName) {

        File file = new File(fileName);
        TreeMap<Integer, String> map = new TreeMap<>();

        System.out.println("Reading file..");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (line.length() > 0) {
                    map.put(i, line);
                    i++;
                }
            }
            System.out.println("Data read successfully");
            System.out.println("========================");

        } catch (FileNotFoundException e) {
            logger.warning("File not found");
            map = null;
        } catch (IOException e) {
            logger.warning("Problem with reading file");
            map = null;
        }
        return map;
    }

    private List<Anagram> getAnagrams(TreeMap<Integer, String> map) {

        List<String> wordList = new ArrayList<>();
        List<Anagram> anagramList = new ArrayList<>();
        String word1;
        String word2;

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            wordList.add(entry.getValue());
        }

        char[] charsOfWord1;
        char[] charsOfWord2;

        boolean isAnagram;

        for (int i = 0; i < wordList.size(); i++) {

            word1 = wordList.get(i);
            charsOfWord1 = word1.toCharArray();

            for (String s : wordList) {

                word2 = s;
                charsOfWord2 = word2.toCharArray();

                isAnagram = true;
                Arrays.sort(charsOfWord1);
                Arrays.sort(charsOfWord2);

                String sorted1 = new String(charsOfWord1);
                String sorted2 = new String(charsOfWord2);

                if ((!sorted1.equals(sorted2) || word1.equals(word2))) {
                    isAnagram = false;
                }

                if (isAnagram) {

                    boolean duplicate = false;

                    for (Anagram anagram : anagramList) {
                        if (anagram.getFirstWord().equals(word1) && anagram.getSecondWord().equals(word2) ||
                                (anagram.getFirstWord().equals(word2) && anagram.getSecondWord().equals(word1))) {
                            duplicate = true;
                            break;
                        }
                    }

                    if (!duplicate) {
                        Anagram newAnagram = new Anagram(word1, word2);
                        anagramList.add(newAnagram);
                    }
                }
            }
        }
        return anagramList;
    }
}

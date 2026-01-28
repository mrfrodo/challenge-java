package com.example.javachallenge;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * Service responsible for counting the occurrences of each word in a text file.
 *
 * The service:
 *  - Reads the content of a file
 *  - Normalizes the text (lowercase, remove punctuation)
 *  - Splits it into words
 *  - Counts how many times each word appears
 *
 * This class is marked as a Spring @Service so it can be injected into
 * controllers or other components.
 */
@Service
public class WordCountService {

    public Map<String, Integer> countWords(File file) {
        Map<String, Integer> wordCounts = new HashMap<>();

        if (file == null || !file.exists() || !file.isFile()) {
            return wordCounts;
        }

        try {
            String text = Files.readString(file.toPath());

            if (text.trim().isEmpty()) {
                return wordCounts;
            }

            // Normalize text: lowercase, remove punctuation
            String normalized = text
                    .toLowerCase()
                    .replaceAll("[^a-z0-9\\s]", "");

            String[] words = normalized.split("\\s+");

            for (String word : words) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + file.getAbsolutePath(), e);
        }

        return wordCounts;
    }
}

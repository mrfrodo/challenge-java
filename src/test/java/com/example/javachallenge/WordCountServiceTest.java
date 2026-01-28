package com.example.javachallenge;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WordCountServiceTest {

    private final WordCountService service = new WordCountService();
    private File tempFile;

    @AfterEach
    void cleanup() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    void countWords_shouldCountCorrectly() throws IOException {
        // given
        tempFile = File.createTempFile("test-", ".txt");
        Files.writeString(tempFile.toPath(),
                "Hello world! Hello Java. Java, Java.");

        // when
        Map<String, Integer> result = service.countWords(tempFile);

        // then
        assertEquals(2, result.get("hello"));
        assertEquals(1, result.get("world"));
        assertEquals(3, result.get("java"));
        assertEquals(3, result.size());
    }

    @Test
    void countWords_withEmptyFile_returnsEmptyMap() throws IOException {
        // given
        tempFile = File.createTempFile("test-", ".txt");
        Files.writeString(tempFile.toPath(), "   ");

        // when
        Map<String, Integer> result = service.countWords(tempFile);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void countWords_withInvalidFile_returnsEmptyMap() {
        // when
        Map<String, Integer> result = service.countWords(new File("does-not-exist.txt"));

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void countWords_fileWithInvalidUtf8_throwsRuntimeExceptionGREEN() throws IOException {
        // Create a temp file
        tempFile = File.createTempFile("test-", ".txt");
        System.out.println("File created at: " + tempFile.getAbsolutePath());

        // Write invalid UTF-8 bytes
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            // 0xC3 followed by 0x28 is an invalid UTF-8 sequence
            byte[] invalidUtf8 = {(byte)0xC3, (byte)0x28};
            fos.write(invalidUtf8);
        }

        // Expect RuntimeException caused by MalformedInputException
        assertThrows(RuntimeException.class, () -> {
            Map<String, Integer> result = service.countWords(tempFile);
        });
    }
}

package com.example.javachallenge;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * This controller do:
 * 1 counts words in a file
 * 2 measures cpu
 * 3 memory used
 * 4 time used for those 3 operations
 */
@RestController
public class WordStatsController {

    // * The service:
    // *  - Reads the content of a file
    // *  - Normalizes the text (lowercase, remove punctuation)
    // *  - Splits it into words
    // *  - Counts how many times each word appears
    private final WordCountService wordCountService;

    public WordStatsController(WordCountService wordCountService) {
        this.wordCountService = wordCountService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        Map<String, Integer> words = wordCountService.countWords(toFile(file));
        ResponseDto response = null;
        return ResponseEntity.ok(response);
    }

    private File toFile(MultipartFile multipartFile) throws IOException {
        File tempFile = File.createTempFile(
                "upload-",
                "-" + multipartFile.getOriginalFilename()
        );
        multipartFile.transferTo(tempFile);
        return tempFile;
    }
}

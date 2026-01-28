package com.example.javachallenge;

import java.util.Map;

/**
 * DTO for file word count response.
 * Contains top words, processing time, and CPU usage.
 */
public record ResponseDto(
        Map<String, Integer> topWords,
        long timeMs,
        double cpuUsed,
        long memoryUsedMb
) {

    public static ResponseDto empty() {
        return new ResponseDto(Map.of(), 0, 0, 0);
    }
}

/**
 * example
 *
 * {
 *   "topWords": {
 *     "java": 42,
 *     "spring": 18,
 *     "hello": 10,
 *     "world": 7
 *   },
 *   "timeMs": 123,
 *   "cpuUsed": 0.37
 * }
 */

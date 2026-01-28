## Java challenge


1. Implement a POST endpoint that accepts a file upload.
2. Read the entire contents of the file into memory (assume file size < 10MB).
3. Count the occurrences of each distinct word or string in the file.
4. Respond with a JSON object containing:
    - A `Map<String, Integer>` of the top N most frequent words/strings.
    - Metrics: time taken to process the file and approximate CPU usage.
5. The service must handle all cases safely and **never throw a NullPointerException**.
6. Use layered architecture 

Points score for clan code, least time used, least cpu used.

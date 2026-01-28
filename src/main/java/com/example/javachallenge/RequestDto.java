package com.example.javachallenge;

import org.springframework.web.multipart.MultipartFile;

public record RequestDto(MultipartFile file) {}

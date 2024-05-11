package com.demo.maids.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ErrorDetails {
    private final LocalDateTime time;
    private final String message;
    private final String description;
}

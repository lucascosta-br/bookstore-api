package br.com.sousinhacode.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp,
                                String message,
                                String details) {
}

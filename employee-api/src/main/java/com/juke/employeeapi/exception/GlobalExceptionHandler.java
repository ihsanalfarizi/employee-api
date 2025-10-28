package com.juke.employeeapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// Class ini buat handle semua error yang terjadi di aplikasi secara terpusat
@RestControllerAdvice  // Annotation biar Spring tau ini handler global untuk semua controller
public class GlobalExceptionHandler {
    
    // Handle error pas data gak ketemu (misal cari karyawan ID 999 tapi gak ada)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),  // Status code 404
                ex.getMessage(),               // Pesan errornya
                LocalDateTime.now()            // Waktu error terjadi
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    // Handle error pas ada parameter yang invalid (misal gaji minus)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),  // Status code 400
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    // Handle error validasi dari @Valid di controller
    // Misal: nama kosong, email format salah, dll
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();  // Bikin map buat simpen error per field
        
        // Loop semua error yang ada
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();      // Nama fieldnya (misal: "email")
            String errorMessage = error.getDefaultMessage();         // Pesannya (misal: "Email is required")
            errors.put(fieldName, errorMessage);                     // Masukin ke map
        });
        
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    
    // Handle semua error yang gak ketangkep sama handler lain
    // Ini kayak safety net terakhir
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),  // Status code 500
                "An unexpected error occurred: " + ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // Record ini buat struktur response error yang konsisten
    // Record itu kayak class tapi lebih simple (Java 14+)
    public record ErrorResponse(int status, String message, LocalDateTime timestamp) {}
}
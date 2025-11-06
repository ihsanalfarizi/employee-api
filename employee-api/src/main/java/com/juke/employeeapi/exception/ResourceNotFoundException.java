package com.juke.employeeapi.exception;

// Custom exception buat nandain kalau data yang dicari gak ketemu
public class ResourceNotFoundException extends RuntimeException {
    
    // Constructor yang nerima pesan error
    public ResourceNotFoundException(String message) {
        super(message);  // Kirim pesan ke class parent (RuntimeException)
    }
}
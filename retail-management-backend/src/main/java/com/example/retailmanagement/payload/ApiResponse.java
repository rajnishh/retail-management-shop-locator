package com.example.retailmanagement.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ApiResponse payload class.
 * @author raj
 *
 */
@Data
@AllArgsConstructor
public class ApiResponse {
    /**
     * success flag.
     */
    private Boolean success;
    /**
     * response message.
     */
    private String message;
}

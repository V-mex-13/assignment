package com.artisthub.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.artisthub.controller.web")
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String handleGlobalException(Exception ex, Model model) {
        logger.error("An unexpected error occurred: ", ex);
        model.addAttribute("error", "An unexpected system error occurred. Please try again later.");
        // By default returning to a known error page or index. Using index for safe redirect, or rendering an error.jsp
        return "index";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex, Model model) {
        logger.warn("A runtime exception occurred: {}", ex.getMessage());
        model.addAttribute("error", ex.getMessage());
        // For registration or login, controllers handle their own exceptions. 
        // This is a global fallback.
        return "index"; 
    }
}

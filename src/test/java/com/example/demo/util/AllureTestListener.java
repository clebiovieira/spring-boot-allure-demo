package com.example.demo.util;

import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.TestResult;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Custom Allure test listener to enhance test reports with additional information.
 */
@Component
public class AllureTestListener implements TestLifecycleListener {

    @Override
    public void beforeTestStart(TestResult result) {
        Allure.addAttachment("Test Start Time", 
                "text/plain", 
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        
        Allure.step("Test preparation started");
    }

    @Override
    public void afterTestStop(TestResult result) {
        Allure.addAttachment("Test End Time", 
                "text/plain", 
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        
        Allure.step("Test execution completed");
        
        // Add environment info to the report
        Allure.addAttachment("Environment Info", "text/plain", getEnvironmentInfo());
    }

    private String getEnvironmentInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Java Version: ").append(System.getProperty("java.version")).append("\n");
        info.append("OS: ").append(System.getProperty("os.name")).append(" ")
            .append(System.getProperty("os.version")).append("\n");
        info.append("User: ").append(System.getProperty("user.name")).append("\n");
        return info.toString();
    }
}
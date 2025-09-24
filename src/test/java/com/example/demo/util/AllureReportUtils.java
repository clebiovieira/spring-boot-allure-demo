package com.example.demo.util;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

/**
 * Utility class for Allure reporting with custom steps and attachments.
 */
public class AllureReportUtils {

    /**
     * Logs a step in the Allure report.
     *
     * @param stepName The name of the step
     */
    @Step("{stepName}")
    public static void logStep(String stepName) {
        // The @Step annotation automatically logs this method call as a step
    }

    /**
     * Logs a step with a description in the Allure report.
     *
     * @param stepName The name of the step
     * @param description The description of the step
     */
    @Step("{stepName}")
    public static void logStep(String stepName, String description) {
        Allure.addAttachment("Step Description", description);
    }

    /**
     * Attaches text content to the Allure report.
     *
     * @param name The name of the attachment
     * @param content The content to attach
     */
    public static void attachText(String name, String content) {
        Allure.addAttachment(name, "text/plain", content);
    }

    /**
     * Attaches JSON content to the Allure report.
     *
     * @param name The name of the attachment
     * @param jsonContent The JSON content to attach
     */
    public static void attachJson(String name, String jsonContent) {
        Allure.addAttachment(name, "application/json", jsonContent);
    }

    /**
     * Logs the start of a test case.
     *
     * @param testName The name of the test
     */
    @Step("Starting test: {testName}")
    public static void logTestStart(String testName) {
        // This method will be logged as a step in the Allure report
    }

    /**
     * Logs the end of a test case.
     *
     * @param testName The name of the test
     */
    @Step("Completed test: {testName}")
    public static void logTestEnd(String testName) {
        // This method will be logged as a step in the Allure report
    }
}
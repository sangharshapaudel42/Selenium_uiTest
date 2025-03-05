package org.RegistrationForm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;

public class registrationFormTest {

    private static WebDriver initializeDriver() {
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

    private static final By submitButton = By.xpath("//*[@id=\"validationForm\"]/button");
    private static final By usernameField = By.xpath("//*[@id=\"username\"]");
    private static final By passwordField = By.xpath("//*[@id=\"password\"]");
    private static final By creditCardField = By.xpath("//*[@id=\"creditCard\"]");
    private static final By phoneField = By.xpath("//*[@id=\"telephone\"]");

    private static void fillForm(WebDriver driver, String username, String password, String creditCard, String phone) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);

        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);

        driver.findElement(creditCardField).clear();
        driver.findElement(creditCardField).sendKeys(creditCard);

        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(phone);
    }


    public static void main(String[] args) {
        System.out.println("Running test1");
        test1();
        System.out.println("Running test2");
        test2();
        System.out.println("Running test3");
        test3();
        System.out.println("Running test4");
        test4();
        System.out.println("Running test5");
        test5();
    }

    private static void test1() {
        WebDriver driver = initializeDriver();
        try {
            driver.get("https://chulo-solutions.github.io/qa-internship/");
            Thread.sleep(1000);
            fillForm(driver, "ValidUser123", "Valid@1234", "4111111111111111", "(123) 456-7890");
            driver.findElement(submitButton).click();
            Thread.sleep(1000);
            verifyError(driver, "Form submitted successfully!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            quitDriver(driver);
        }
    }

    private static void test2() {
        WebDriver driver = initializeDriver();
        try {
            driver.get("https://chulo-solutions.github.io/qa-internship/");
            Thread.sleep(1000);
            fillForm(driver, "Usr!", "Valid@1234", "4111111111111111", "(123) 456-7890");
            driver.findElement(submitButton).click();
            Thread.sleep(1000);
            verifyError(driver, "Username must be alphanumeric and between 5 to 15 characters.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            quitDriver(driver);
        }
    }

    private static void test3() {
        WebDriver driver = initializeDriver();
        try {
            driver.get("https://chulo-solutions.github.io/qa-internship/");
            Thread.sleep(1000);
            fillForm(driver, "ValidUser123", "pass", "4111111111111111", "(123) 456-7890");
            driver.findElement(submitButton).click();
            Thread.sleep(1000);
            verifyError(driver, "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            quitDriver(driver);
        }
    }

    private static void test4() {
        WebDriver driver = initializeDriver();
        try {
            driver.get("https://chulo-solutions.github.io/qa-internship/");
            Thread.sleep(1000);
            fillForm(driver, "ValidUser123", "Valid@1234", "1234567890123456", "(123) 456-7890");
            driver.findElement(submitButton).click();
            Thread.sleep(1000);
            verifyError(driver, "Enter a valid credit card number.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            quitDriver(driver);
        }
    }

    private static void test5() {
        WebDriver driver = initializeDriver();
        try {
            driver.get("https://chulo-solutions.github.io/qa-internship/");
            Thread.sleep(1000);
            fillForm(driver, "ValidUser123", "Valid@1234", "4111111111111111", "1234567890");
            driver.findElement(submitButton).click();
            Thread.sleep(1000);
            verifyError(driver, "Telephone number must follow the format (XXX) XXX-XXXX.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            quitDriver(driver);
        }
    }


    private static void verifyError(WebDriver driver, String expectedErrorMessage) {
        try {
            Alert alert = driver.switchTo().alert();
            String actualErrorMessage = alert.getText();
            System.out.println("error message: " + actualErrorMessage);
            if (actualErrorMessage.equals(expectedErrorMessage)) {
                System.out.println("Error message verification passed!!" );
            } else {
                System.out.println("Error message verification failed. Expected: " + expectedErrorMessage + ", Actual: " + actualErrorMessage);
            }
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present.");
        }
    }
}
package com.example.labo_service;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SeleniumTest {
    private WebDriver driver;

    @BeforeAll
    public void setup(){
        WebDriverManager.chromedriver().setup(); // Setup WebDriverManager for ChromeDriver
        driver = new ChromeDriver(); // Initialize ChromeDriver
        driver.manage().window().maximize(); // Maximize the browser window
    }


    @Test
    public void testPageLoad() {
        String url = "http://localhost:4200"; // Replace with your actual Angular app URL
        driver.get(url); // Navigate to the Angular app

        // Wait for the page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Check for the main header
        WebElement mainHeader = wait.until(driver -> driver.findElement(By.tagName("h1")));
        assert(mainHeader.getText().equals("Bienvenue dans notre Système de Gestion des Laboratoires"));

    }

    @Test
    public void testLaboratoiresListLoaded() {
        String url = "http://localhost:4200/laboratoires"; // Navigate to the laboratoire list page
        driver.get(url);

        // Wait for the table to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // Increase timeout if needed
        WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".laboratoire-list .table.table-striped.table-hover")));

        // Verify that the table exists and contains rows
        assert(table.isDisplayed());
        assert(table.findElements(By.tagName("tr")).size() > 1); // Ensure there are more than 1 laboratoire row

        // Verify the first laboratoire details (for example, name and NRC)
        WebElement firstRow = table.findElement(By.cssSelector("tbody tr:first-child"));
        String nom = firstRow.findElement(By.cssSelector("td:nth-child(2)")).getText();
        String nrc = firstRow.findElement(By.cssSelector("td:nth-child(3)")).getText();

        assert(!nom.isEmpty());
        assert(!nrc.isEmpty());

        System.out.println("First laboratoire name: " + nom);
        System.out.println("First laboratoire NRC: " + nrc);
    }


    @AfterAll
    public void cleanup() {
        if (driver != null) {
            driver.quit(); // Close the browser
        }
    }
}

package com.example.labo_service;

import io.github.bonigarcia.wdm.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

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


//test add new labo
    @Test
    public void testAjouterLaboratoireForm() {
        String url = "http://localhost:4200/ajouter-laboratoire"; // URL to the AjouterLaboratoireComponent
        driver.get(url); // Navigate to the form page

        // Wait for form elements to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Verify the presence of form elements
        WebElement nomField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nom")));
        WebElement nrcField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nrc")));
        WebElement logoField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("logo")));
        WebElement activeCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("active")));
        WebElement dateActivationField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("dateActivation")));

        // Fill in the form fields
        nomField.sendKeys("Test Laboratoire");
        nrcField.sendKeys("12345");
        logoField.sendKeys("http://example.com/logo.png");
        activeCheckbox.click(); // Check the checkbox
        dateActivationField.sendKeys("2025-01-01");

        // Submit the form
        WebElement submitButton = driver.findElement(By.cssSelector(".btn-success"));
        submitButton.click();

        sleep(2000);


        // Log information for verification
        System.out.println("Nom: " + nomField.getAttribute("value"));
        System.out.println("NRC: " + nrcField.getAttribute("value"));
        System.out.println("Logo: " + logoField.getAttribute("value"));
        System.out.println("Active: " + activeCheckbox.isSelected());
        System.out.println("Date d'Activation: " + dateActivationField.getAttribute("value"));
    }
//test get all labo
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
        sleep(2000);

    }

    @Test
    public void testEditLaboratoire() {
        String url = "http://localhost:4200/laboratoires"; // Navigate to the laboratoire list page
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // Increase timeout if needed

        sleep(2000);

        // Find the edit button for a laboratoire entry (assuming first entry for example)
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("modifier")));
        editButton.click();


        // Wait for the dialog to open
        WebElement dialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".form-container")));


        // Fill the form
        WebElement nomInput = dialog.findElement(By.id("nom"));
        WebElement nrcInput = dialog.findElement(By.id("nrc"));
        WebElement logoInput = dialog.findElement(By.id("logo"));
        WebElement activeCheckbox = dialog.findElement(By.id("active"));
        WebElement dateActivationInput = dialog.findElement(By.id("dateActivation"));

        // Update the form with test data
        nomInput.clear();
        nomInput.sendKeys("Updated Nom");
        nrcInput.clear();
        nrcInput.sendKeys("Updated NRC");
        logoInput.clear();
        logoInput.sendKeys("Updated Logo URL");
        activeCheckbox.click();
        dateActivationInput.clear();
        dateActivationInput.sendKeys("20-01-01");

        sleep(2000);

        // Submit the form
        WebElement saveButton = dialog.findElement(By.cssSelector("button[type='submit']"));
        saveButton.click();

        // Wait for the success message

        // Verify the edited data appears in the table
        WebElement nomInTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), 'Updated Nom')]")));
        WebElement nrcInTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(), 'Updated NRC')]")));

    }

    @Test
    public void testNavigateToContacts() {
        String url = "http://localhost:4200/laboratoires"; // Navigate to the laboratoire list page
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Click on the button to navigate to contacts
        WebElement viewDetailsButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("showcontacts")));
        viewDetailsButton.click();

        // Wait for the contacts page to load
        wait.until(ExpectedConditions.urlContains("/contacts-laboratoire"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".contacts-container")));

        sleep(2000);

    }

    @Test
    public void testShowAnalysesAndAddAnalyse() throws InterruptedException {
        String url = "http://localhost:4200/laboratoires"; // Navigate to the laboratoire list page
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // Increase timeout if needed


        // Click on "Show Analyses" button
        WebElement showAnalysesButton = driver.findElement(By.id("showanalyses"));
        showAnalysesButton.click();


        // Wait for the table to load and check if it is displayed
        Thread.sleep(2000); // Adjust to explicit wait in real scenarios
        WebElement analysesTable = driver.findElement(By.cssSelector(".analyses-table"));

        // Click "Add New Analyse" button
        WebElement addAnalyseButton = driver.findElement(By.id("addanalyse"));
        addAnalyseButton.click();

        // Wait for the dialog to open
        Thread.sleep(2000); // Adjust to explicit wait in real scenarios
        WebElement dialog = driver.findElement(By.tagName("mat-dialog-container"));

        // Fill out the form
        WebElement nameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nom")));
        WebElement descriptionInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("description")));

        nameInput.sendKeys("Test Analyse");
        descriptionInput.sendKeys("This is a test description.");

        // Click the "Add" button
        WebElement addButton = driver.findElement(By.id("add"));
        addButton.click();


    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @AfterAll
    public void cleanup() {
        if (driver != null) {
            driver.quit(); // Close the browser
        }
    }
}
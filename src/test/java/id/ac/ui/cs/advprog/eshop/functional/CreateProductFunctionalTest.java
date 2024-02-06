package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each terst run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort);
    }

    @Test
    void createProduct(ChromeDriver driver) throws Exception{
        //Exercise
        driver.get(baseUrl);
        driver.findElement(By.id("createBtn")).click();

        String productName = "Pensil";
        WebElement nameInput = driver.findElement(By.id("nameInput"));
        nameInput.clear();
        nameInput.sendKeys(productName);

        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        quantityInput.clear();
        quantityInput.sendKeys("10");

        WebElement submitButton = driver.findElement(By.id("submitButton"));
        submitButton.click();

        WebElement columnProductName = driver.findElement(By.className("productName"));
        WebElement columnProductQuantity = driver.findElement(By.className("productQuantity"));

        //Verify
        assertEquals(productName, columnProductName.getText());
        assertEquals("10", columnProductQuantity.getText());
    }

}
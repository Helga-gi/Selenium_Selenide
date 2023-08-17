package ru.netology.selenium_selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CallTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void beforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void afterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestV1() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Василий Васильев");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+780000000");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.className("button.button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();

        assertEquals("Ваша заявка успешно отправлена: Наш менеджер свяжется с вами в ближайшее время.", text);
    }

    }

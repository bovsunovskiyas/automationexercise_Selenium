package com.automationexercise;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserFactory {

    public WebDriver getWebDriverInstance(){

        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); // Запуск без графічного інтерфейсу
        options.addArguments("--no-sandbox"); // Необхідно для запуску в Docker/Linux контейнерах
        options.addArguments("--disable-dev-shm-usage"); // Уникаємо проблем з пам'яттю в контейнерах
        options.addArguments("--window-size=1920,1080"); // Встановлюємо розмір вікна
        options.addArguments("--remote-allow-origins=*"); // Дозволяємо віддалені підключення

        return new ChromeDriver(options);
    }

}

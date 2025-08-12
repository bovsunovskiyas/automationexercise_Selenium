package com.automationexercise;

import com.automationexercise.helpers.Browser;
import com.automationexercise.helpers.LoggingListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;


public class BrowserFactory {

    public WebDriver getWebDriverInstance(Browser browser){

        return switch (browser){
            case CHROME -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless"); // Запуск без графічного інтерфейсу
                options.addArguments("--no-sandbox"); // Необхідно для запуску в Docker/Linux контейнерах
                options.addArguments("--disable-dev-shm-usage"); // Уникаємо проблем з пам'яттю в контейнерах
                options.addArguments("--window-size=1920,1080"); // Встановлюємо розмір вікна
                options.addArguments("--remote-allow-origins=*"); // Дозволяємо віддалені підключення
                options.addArguments("--disable-gpu"); // Рекомендовано для headless режиму

                ChromeDriver chromeDriver = new ChromeDriver(options);
                LoggingListener loggingListener = new LoggingListener();
                WebDriver decoratedWebdriver = new EventFiringDecorator<>(loggingListener).decorate(chromeDriver);
                yield  decoratedWebdriver;
//                new ChromeDriver();
            }
            case FIREFOX -> new FirefoxDriver();
            case SAFARI -> new SafariDriver();
//            default -> new ChromeDriver();
        };
    }

}

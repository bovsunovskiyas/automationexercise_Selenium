package com.automationexercise;

import com.automationexercise.helpers.Browser;
import com.automationexercise.helpers.LoggingListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class BrowserFactory {

    public WebDriver getWebDriverInstance(Browser browser) throws MalformedURLException {


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
            case REMOTE -> {
                ChromeOptions options = new ChromeOptions();
                options.setCapability("browserVersion", "138.0.7204.183-8");
                options.setCapability("moon:options", new HashMap<String, Object>() {{
                    put("name", "Test badge...");
                    put("sessionTimeout", "15m");
                    put("env", new ArrayList<String>() {{
                        add("TZ=UTC");
                    }});
                    put("labels", new HashMap<String, Object>() {{
                        put("manual", "true");
                    }});
                    put("enableVideo", false);
                }});
                options.addArguments("--headless");

                RemoteWebDriver remote = new RemoteWebDriver(
                        new URL("http://moon.aerokube.local/wd/hub"),
                        options
                );

                LoggingListener loggingListener = new LoggingListener();
                WebDriver decoratedWebdriver = new EventFiringDecorator<>(loggingListener).decorate(remote);
                yield decoratedWebdriver;
            }
//            default -> new ChromeDriver();
        };
    }
}

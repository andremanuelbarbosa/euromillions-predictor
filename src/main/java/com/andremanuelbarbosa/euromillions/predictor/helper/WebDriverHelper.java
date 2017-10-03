package com.andremanuelbarbosa.euromillions.predictor.helper;

import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

@Singleton
public class WebDriverHelper {

    private static final String OS_NAME = System.getProperty("os.name").toLowerCase();
    private static final String OS_BITS = OS_NAME.equals("linux") && System.getProperty("os.arch").endsWith("64") ? "64" : "32";

    private static final String CHROME_DRIVER_VERSION = System.getProperty("chrome.driver.version") != null ? System.getProperty("chrome.driver.version") : "2.32";

    private static final String WEB_DRIVER_CHROME_DRIVER = "src/test/resources/selenium/drivers/chrome/" + CHROME_DRIVER_VERSION + "/" + OS_NAME + "/" + OS_BITS + "/chromedriver";

    static {

        if (!new File(WEB_DRIVER_CHROME_DRIVER).canExecute()) {

            throw new IllegalStateException("The specified Chrome Driver [" + WEB_DRIVER_CHROME_DRIVER + "] cannot be found or cannot be executed.");
        }

        System.setProperty("webdriver.chrome.driver", WEB_DRIVER_CHROME_DRIVER);
    }

    public WebDriver createChromeDriver() {

        final WebDriver webDriver = new ChromeDriver();

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {

                if (webDriver != null) {

                    webDriver.quit();
                }
            }
        });

        return webDriver;
    }
}

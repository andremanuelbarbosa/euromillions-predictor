package com.andremanuelbarbosa.euromillions.predictor.helper;

import com.google.inject.Singleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

@Singleton
public class WebDriverHelper {

    private static final String OS_NAME_SYS_PROP_NAME = "os.name";
    private static final String OS_ARCH_SYS_PROP_NAME = "os.arch";

    private static final String OS_NAME = System.getProperty(OS_NAME_SYS_PROP_NAME).toLowerCase().replaceAll(" ", "");
    private static final String OS_ARCH = System.getProperty(OS_ARCH_SYS_PROP_NAME);
    private static final String OS_BITS = OS_ARCH.endsWith("64") ? "64" : "32";

    private static final String CHROME_DRIVER_VERSION = "2.32";
    private static final String CHROME_DRIVER_LOCATION = "src/test/resources/selenium/drivers/chrome/" + CHROME_DRIVER_VERSION + "/" + OS_NAME + "/" + OS_BITS + "/chromedriver";

    private static final String WEBDRIVER_CHROME_DRIVER_SYS_PROP_NAME = "webdriver.chrome.driver";

    static {

        if (!new File(CHROME_DRIVER_LOCATION).canExecute()) {

            throw new IllegalStateException("The specified Chrome Driver [" + CHROME_DRIVER_LOCATION + "] cannot be found or cannot be executed.");
        }

        System.setProperty(WEBDRIVER_CHROME_DRIVER_SYS_PROP_NAME, CHROME_DRIVER_LOCATION);
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

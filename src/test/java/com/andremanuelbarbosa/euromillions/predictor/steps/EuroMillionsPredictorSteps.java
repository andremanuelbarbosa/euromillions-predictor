package com.andremanuelbarbosa.euromillions.predictor.steps;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.When;

public class EuroMillionsPredictorSteps {

  private static final String HOST = "http://localhost:8080";
  private static final String CHROME_DRIVER_VERSION = "2.10";

  static final WebDriver WEB_DRIVER = createChromeDriver();

  static {

    Runtime.getRuntime().addShutdownHook(new Thread() {

      @Override
      public void run() {

        if (WEB_DRIVER != null) {

          WEB_DRIVER.quit();
        }
      }
    });
  }

  private static WebDriver createChromeDriver() {

    String osName = System.getProperty("os.name").toLowerCase();
    String osArch = System.getProperty("os.arch");
    String osBits = osName.equals("linux") && osArch.endsWith("64") ? "64" : "32";

    String chromeDriverVersion = System.getProperty("chrome.driver.version") != null ? System
        .getProperty("chrome.driver.version") : CHROME_DRIVER_VERSION;

    String webDriverChromeDriver = "src/test/resources/selenium/drivers/chrome/"
        + chromeDriverVersion + "/" + osName + "/" + osBits + "/chromedriver";

    if (!new File(webDriverChromeDriver).canExecute()) {

      throw new IllegalStateException(
          "The specified Chrome Driver cannot be found or cannot be executed : "
              + webDriverChromeDriver);
    }

    System.setProperty("webdriver.chrome.driver", webDriverChromeDriver);

    return new ChromeDriver();
  }

  @When("^the client goes to \"(.*?)\"$")
  public void the_client_goes_to(String url) throws Throwable {

    WEB_DRIVER.get(HOST + url);
  }
}

package com.andremanuelbarbosa.euromillions.predictor.steps;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.When;

public class EuroMillionsPredictorSteps {

  private static final String BASE_URL = "http://localhost:9090/euromillions-predictor";
  private static final String CHROME_DRIVER_VERSION = "2.10";

  static Scenario thisScenario;
  static WebDriver webDriver = createChromeDriver();

  static {

    Runtime.getRuntime().addShutdownHook(new Thread() {

      @Override
      public void run() {

        if (webDriver != null) {

          webDriver.quit();
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

    String webDriverChromeDriver = "src/test/resources/selenium/drivers/chrome/" + chromeDriverVersion + "/" + osName
        + "/" + osBits + "/chromedriver";

    if (!new File(webDriverChromeDriver).canExecute()) {

      throw new IllegalStateException("The specified Chrome Driver cannot be found or cannot be executed : "
          + webDriverChromeDriver);
    }

    System.setProperty("webdriver.chrome.driver", webDriverChromeDriver);

    return new ChromeDriver();
  }

  void takeScreenshot() {

    thisScenario.write(webDriver.getCurrentUrl());
    thisScenario.embed(((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES), "image/png");
  }

  void waitAndTakeScreenshot(int millis) throws InterruptedException {

    Thread.sleep(millis);

    takeScreenshot();
  }

  @Before
  public void setUp(Scenario scenario) {

    thisScenario = scenario;
  }

  @When("^the client navigates to \"(.*?)\"$")
  public void the_client_navigates_to(String url) throws Throwable {

    webDriver.get(BASE_URL + url);

    waitAndTakeScreenshot(10000);
  }
}

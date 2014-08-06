package com.andremanuelbarbosa.euromillions.predictor;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/cucumber/features", format = "html:target/cucumber", strict = true)
public class EuroMillionsPredictorAcceptanceTest {

}

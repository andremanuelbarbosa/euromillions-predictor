Feature: EuroMillions Predictor

  Scenario: EuroMillions Predictor Home should return 200
    When the client goes to "/"
    Then the client should get a "200" response

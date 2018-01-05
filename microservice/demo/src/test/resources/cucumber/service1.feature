Feature: the service can be called
  Scenario: client makes call to GET /service
    When the client calls /service
    Then response status code is 200 and it says "Boo!"

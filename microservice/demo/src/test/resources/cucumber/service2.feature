Feature: the service can be called
  Scenario: client makes call to GET /service1
    When the client calls /service1
    Then response failed with status code 404 and message "Not Found"

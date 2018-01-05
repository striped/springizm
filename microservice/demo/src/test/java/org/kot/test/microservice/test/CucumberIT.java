package org.kot.test.microservice.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Cucumber test runner.
 * @author <a href=mailto:striped@gmail.com>striped</a>
 * @created 02/01/2018 22:14
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		tags = {"not @ignore"},
		features = {"classpath:cucumber"},
		format = {"pretty", "html:target/site/cucumber/cucumber", "json:target/failsafe-reports/cucumber.json"},
		strict = true)
public class CucumberIT {}

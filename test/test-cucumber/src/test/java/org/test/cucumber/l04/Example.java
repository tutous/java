package org.test.cucumber.l04;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/java/org/test/cucumber/l04" }, plugin = { "pretty", "html:target/cucumber" })
public class Example {

}

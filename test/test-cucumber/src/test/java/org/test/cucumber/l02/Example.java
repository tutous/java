package org.test.cucumber.l02;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/java/org/test/cucumber/l02" }, plugin = { "pretty", "html:target/cucumber" })
public class Example {

}

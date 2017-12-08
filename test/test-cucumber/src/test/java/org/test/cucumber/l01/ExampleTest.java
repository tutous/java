package org.test.cucumber.l01;

import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ExampleTest {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ExampleTest.class);

	@Given("^a \\$(\\d+) microwave was sold on (\\d+)-(\\d+)-(\\d+)$")
	public void a_$_microwave_was_sold_on(int amount,int year, int month, int day) throws Throwable {
		logger.info("a_$_microwave_was_sold_on {}-{}-{}", year, month, day);
	}

	@Given("^today is (\\d+)-(\\d+)-(\\d+)$")
	public void today_is(int year, int month, int day) throws Throwable {
		logger.info("today_is {}-{}-{}",year,month,day);
	}

	@Given("^Jeff has bought a microwave for \\$(\\d+)$")
	public void jeff_has_bought_a_microwave_for_$(int amount) throws Throwable {
		logger.info("jeff_has_bought_a_microwave_for_${}", amount);
	}

	@Given("^he has a receipt$")
	public void he_has_a_receipt() throws Throwable {
		logger.info("he_has_a_receipt");
	}

	@When("^he returns the microwave$")
	public void he_returns_the_microwave() throws Throwable {
		logger.info("he_returns_the_microwave");
	}

	@When("^spoke with seller$")
	public void spoke_with_seller() throws Throwable {
		logger.info("spoke_with_seller");
	}

	@Then("^Jeff should be refunded \\$(\\d+)$")
	public void jeff_should_be_refunded_$(int amount) throws Throwable {
		logger.info("jeff_should_be_refunded_${}", amount);
	}

	@Then("^get back the money from \"([^\"]*)\"$")
	public void get_back_the_money_from(String name) throws Throwable {
		logger.info("get_back_the_money_from {}", name);
	}

}

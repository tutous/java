package org.test.cucumber.l02;

import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ExampleTest {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ExampleTest.class);

	@Given("^the cow weighs (\\d+) kg$")
	public void the_cow_weighs_kg(int weight) throws Throwable {
		logger.info("the_cow_weighs_kg {}", weight);
	}

	@When("^we calculate the feeding requirements$")
	public void we_calculate_the_feeding_requirements() throws Throwable {
		logger.info("we_calculate_the_feeding_requirements");
	}

	@Then("^the energy should be (\\d+) MJ$")
	public void the_energy_should_be_MJ(int energy) throws Throwable {
		logger.info("the_energy_should_be_MJ {}", energy);
	}

	@Then("^the protein should be (\\d+) kg$")
	public void the_protein_should_be_kg(int protein) throws Throwable {
		logger.info("the_protein_should_be_kg {}", protein);
	}

}

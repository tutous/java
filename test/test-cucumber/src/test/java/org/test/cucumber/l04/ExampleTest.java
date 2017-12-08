package org.test.cucumber.l04;

import java.util.List;

import org.slf4j.LoggerFactory;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ExampleTest {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ExampleTest.class);
	private int count = 0;
	private List<List<String>> users;

	@Given("^the following users$")
	public void the_following_users(DataTable users) throws Throwable {
		logger.info("the following users {}", users);
		this.users = users.asLists(String.class);
	}

	@When("^we count the list$")
	public void we_count_the_list() throws Throwable {
		logger.info("we count the list");
		for (List<String> user : users) {
			logger.info("  user {}", user);
			count++;
		}
	}

	@Then("^the size is (\\d+)$")
	public void the_size_is(int size) throws Throwable {
		logger.info("the size is {}", size);
	}

}

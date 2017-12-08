package org.test.cucumber.l03;

import org.slf4j.LoggerFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ExampleTest {

	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ExampleTest.class);

	@Given("^a blog post named \"([^\"]*)\" with Markdown body$")
	public void a_blog_post_named_with_Markdown_body(String name, String text) throws Throwable {
		logger.info("a_blog_post_named_with_Markdown_body name={} \n{}", name, text);
	}

}

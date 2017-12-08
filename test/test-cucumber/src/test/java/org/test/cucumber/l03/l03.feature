Feature: Doc Strings are handy for passing a larger piece of text

  Scenario: Doc Strings
    Given a blog post named "Random" with Markdown body
      """
       Some Title, Eh?
       ===============
       Here is the first paragraph of my blog post. Lorem ipsum dolor sit amet,
       consectetur adipiscing elit.
      """
    

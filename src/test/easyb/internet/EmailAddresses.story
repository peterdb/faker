package internet

import faker.Internet;

description """
In order to generate fake data
As a developer
I want to be able to create fake email addresses
"""

description "random email: random domain name and tld"

scenario "generate random email without a name", {
    when "email is generated", {
        emails = (1..10).collect { Internet.email() }
    }

    then "a random name is used, and a random domain", {
        emails.each { email ->
            assert email ==~ /.+@.+\.\w+/
        }
    }
}

scenario "generate random email with a name", {
    when "email is generated with a name", {
        emails = (1..10).collect { Internet.email("John Doe") }
    }

    then "the given name is used, and a random domain", {
        emails.each { email ->
            assert email ==~ /(john(_|.)doe|doe(_|.)john)@.+\.\w+/
        }
    }
}

scenario "generate free email without parameters",{
    when "free email is generated",{
        emails = (1..10).collect { Internet.freeEmail() }
    }

    then "a random name must be used, and one of the free email providers (gmail, hotmail or yahoo)", {
        emails.each { email ->
            assert email ==~ /.+@(gmail|hotmail|yahoo)\.com/
        }
    }
}

scenario "generate free email with a name",{
    when "free email address is generated",{
        emails = (1..10).collect { Internet.freeEmail("John Doe") }
    }

    then "the name must be used, and one of the free email providers (gmail, hotmail or yahoo)", {
        emails.each { email ->
            assert email ==~ /(john(_|.)doe|doe(_|.)john)@(gmail|hotmail|yahoo)\.com/
        }
    }
}

scenario "generate safe email without parameters",{
    when "free email is generated",{
        emails = (1..10).collect { Internet.safeEmail() }
    }

    then "a random name must be used, and one of the free email providers (example.com, example.net, example.org)", {
        emails.each { email ->
            assert email ==~ /.+@example.(com|net|org)/
        }
    }
}

scenario "generate safe email with a name",{
    when "free email address is generated",{
        emails = (1..10).collect { Internet.safeEmail("John Doe") }
    }

    then "the name must be used, and one of the free email providers (example.com, example.net, example.org)", {
        emails.each { email ->
            assert email ==~ /(john(_|.)doe|doe(_|.)john)@example.(com|net|org)/
        }
    }
}

package internet

import faker.Internet;

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

package internet

import faker.Internet;

description "safe email: example.com, example.net or example.org"

scenario "generate safe email without parameters",{
	when "free email is generated",{
		emails = (1..10).collect { Internet.safeEmail() }
	}

	then "a random name must be used, and one of the free email providers", {
		emails.each { email -> 
			assert email ==~ /.+@example.(com|net|org)/ 
		}
	}
}

scenario "generate safe email with a name",{
	when "free email address is generated",{
		emails = (1..10).collect { Internet.safeEmail("John Doe") }
	}

	then "the name must be used, and one of the free email providers", {
		emails.each { email -> 
			assert email ==~ /(john(_|.)doe|doe(_|.)john)@example.(com|net|org)/ 
		}
	}
}
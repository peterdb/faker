package internet

import faker.Internet;

description "free email: one of the free providers: gmail, hotmail, yahoo"


scenario "generate free email without parameters",{
    when "free email is generated",{
        emails = (1..10).collect { Internet.freeEmail() }
    }

    then "a random name must be used, and one of the free email providers", {
        emails.each { email -> 
            assert email ==~ /.+@(gmail|hotmail|yahoo)\.com/ 
        }
    }
}

scenario "generate free email with a name",{
    when "free email address is generated",{
        emails = (1..10).collect { Internet.freeEmail("John Doe") }
    }

    then "the name must be used, and one of the free email providers", {
        emails.each { email -> 
            assert email ==~ /(john(_|.)doe|doe(_|.)john)@(gmail|hotmail|yahoo)\.com/ 
        }
    }
}

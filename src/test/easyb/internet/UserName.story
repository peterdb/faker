package internet

import faker.Internet;

shared_stories "../shared/stories.shared"


narrative 'domain names', {
    as_a 'developer'
    i_want 'to be able to create fake user names'
}

scenario "user name with no name",{
    when "a username is generated without a name",{
        userName = Internet.userName()
    }
    
    then "a random name is be generated, converted to lowercase, and glued with _ or .", {
        assert userName ==~ /[a-z]+((_|\.)[a-z]+)?/
    }
}

scenario "user name with a name",{
    when "a username is generated with a first name",{
        userName = Internet.userName("John")
    }
    
    then "the name is converted to lowercase", {
        assert userName == "john"
    }
}

scenario "user name with a full name",{
    when "a username is generated with a full name",{
        userName = Internet.userName("John Doe")
    }
    
    then "the name is converted to lowercase, and glued with _ or .", {
        assert userName ==~ /(john(_|\.)doe|doe(_|\.)john)/
    }
}

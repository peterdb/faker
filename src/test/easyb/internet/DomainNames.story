package internet

import faker.Internet;

shared_stories "../shared/stories.shared"

narrative 'domain names', {
    as_a 'developer'
    i_want 'to be able to create fake domain names'
}

scenario "domain name", {
    when "I call Internet.domainName()", {
        domainName = Internet.domainName()
    }
    
    then "the output should be a valid domain name", {
        assert domainName ==~ /\w+\.\w+/
    }
}

scenario "domain word", {
    given "I call Internet.domainWord()", {
        domainWord = Internet.domainWord()
    }
    
    then "the output should be a valid word", {
        assert domainWord ==~ /^\w+$/
    }
}

scenario "domain suffix",{
    when "I call Internet.domainSuffix()", {
        domainSuffix = Internet.domainSuffix()
    }
    
    then "it is a valid domain suffix", {
        assert domainSuffix ==~ /^\w+(\.\w+)?/
    }
}
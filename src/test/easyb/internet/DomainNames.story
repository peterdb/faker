package internet

import faker.Internet;

shared_stories "../shared/stories.shared"

narrative 'domain names', {
    as_a 'developer'
    i_want 'to be able to create fake domain names'
}

scenario "domain name",{
    given "a generated domain name", {
        domainName = Internet.domainName()
    }
    
    then "it is valid", {
        assert domainName ==~ /\w+\.\w+/
    }
}

scenario "domain word",{
    given "a generated domain word", {
        domainWord = Internet.domainWord()
    }
    
    then "it is valid", {
        assert domainWord ==~ /^\w+$/
    }
}

scenario "domain suffix",{
    given "a generated domain suffix", {
        domainSuffix = Internet.domainSuffix()
    }
    
    then "it is valid", {
        assert domainSuffix ==~ /^\w+(\.\w+)?/
    }
}
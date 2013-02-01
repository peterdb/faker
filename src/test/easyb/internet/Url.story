package internet

import faker.Internet;

scenario "domain name",{
    given "a generated domain name", {
        url = Internet.url()
    }
    
    then "it is valid", {
        assert url ==~ /http:\/\/\w+\.\w+\/\w+(-|_\.)\w+/
    }
}

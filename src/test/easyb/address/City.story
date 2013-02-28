package address


import faker.Address

import faker.Faker;
import faker.i18n.MapBasedResourceBundle
import faker.internal.groovy.I18nMethods;


shared_stories "../shared/stories.shared"

narrative 'geographic coordinates', {
    as_a 'developer'
    i_want 'to be able to create city names'
}

scenario "default city formats", {
    when "I call Address.city()", {
        cities = (1..100).collect { Address.city() }
    }
    
    then "the output should be a valid city name", {
        cities.each { city -> 
            assert city ==~ /.+/
        }
    }
}
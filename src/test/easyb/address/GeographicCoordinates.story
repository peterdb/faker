package address


import faker.Address

import faker.Faker;
import faker.i18n.MapBasedResourceBundle
import faker.internal.groovy.I18nMethods;


shared_stories "../shared/stories.shared"


narrative 'geographic coordinates', {
    as_a 'developer'
    i_want 'to be able to create geographic coordinates'
}

scenario "latitude",{
    when "I call Address.latitude()", {
        lats = (1..1000).collect { Address.latitude() }
    }
    
    then "the output should be between -90 and +90", {
        lats.each { lat ->
            assert lat >= -90
            assert lat <= 90
        }
    }
}

scenario "longitude",{
    when "I call Address.longitude()", {
        longs = (1..1000).collect { Address.longitude() }
    }
    
    then "the output should be between -180 and +180", {
        longs.each { l ->
            assert l >= -180
            assert l <= 180
        }
    }
}
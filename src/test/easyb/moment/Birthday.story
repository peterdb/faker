package moment

import faker.Moment;
import groovy.time.TimeCategory;

narrative 'birthdays', {
    as_a 'developer'
    i_want 'to be able to generate birthdays'
}

scenario "birthday, with specific age",{
    when "I call Moment.birthday(25)", {
        dates = (1..1000).collect { Moment.birthday(25) }
    }
    
    then "it should return a date between 01/01/1970 and today", {
        def today = new Date()
        
        use(TimeCategory) {
            dates.each { birthday ->
                def age = ((today - birthday).days - 1) / 365 as int
                
                if(age != 25) {
                    
                }
                
                println birthday
                age.shouldBe 25
            }
        }
    }
}

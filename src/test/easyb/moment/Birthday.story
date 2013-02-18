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
    
    then "it should return a birthday for someone who is 25 years old", {
        def today = new Date()
        
        use(TimeCategory) {
            dates.each { day ->
                def fake = new GregorianCalendar(today[Calendar.YEAR], day[Calendar.MONTH], day[Calendar.DAY_OF_MONTH]).time
                def age = today[Calendar.YEAR] - day[Calendar.YEAR] - (fake > today ? 1 : 0)
                                
                age.shouldBe 25
            }
        }
    }
}

scenario "birthday, with age range",{
    when "I call Moment.birthday(25..50)", {
        dates = (1..1000).collect { Moment.birthday(25..50) }
    }
    
    then "it should return a birthday for someone who is between 25 and 50 years old", {
        def today = new Date()
        
        use(TimeCategory) {
            dates.each { day ->
                def fake = new GregorianCalendar(today[Calendar.YEAR], day[Calendar.MONTH], day[Calendar.DAY_OF_MONTH]).time
                def age = today[Calendar.YEAR] - day[Calendar.YEAR] - (fake > today ? 1 : 0)
                                
                assert age in 25..50, "age $age not in 25..50"
            }
        }
    }
}

scenario "birthday, with no parameters",{
    when "I call Moment.birthday()", {
        dates = (1..1000).collect { Moment.birthday() }
    }
    
    then "it should return a birthday for someone who is between 18 and 65 years old", {
        def today = new Date()
        
        use(TimeCategory) {
            dates.each { day ->
                def fake = new GregorianCalendar(today[Calendar.YEAR], day[Calendar.MONTH], day[Calendar.DAY_OF_MONTH]).time
                def age = today[Calendar.YEAR] - day[Calendar.YEAR] - (fake > today ? 1 : 0)
                                
                assert age in 18..65, "age $age not in 18..65"
            }
        }
    }
}

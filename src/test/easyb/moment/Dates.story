package moment

import faker.Moment;
import groovy.time.TimeCategory;

narrative 'dates and times', {
    as_a 'developer'
    i_want 'to be able to generate dates and times'
}

scenario "date, no parameters",{
    when "I call Moment.date()", {
        dates = (1..10000).collect { Moment.date() }
    }
    
    then "it should return a date between 01/01/1970 and today", {
        def start = new Date(0L).clearTime()
        def today = new Date().clearTime()
        
        dates.each { date ->
            assert start <= date
            assert date <= today
        }
    }
}

scenario "date, with start", {
    given "start date, one month ago", {
        use(TimeCategory) {
            start = 1.month.ago
        }
    }
    
    when "I call Moment.date(1.month.ago)", {
        dates = (1..1000).collect { Moment.date(start) }
    }
    
    then "it should return a date between one month ago and today", {
        def today = new Date().clearTime()
        
        dates.each { date ->
            assert start <= date
            assert date <= today
        }
    }
}

scenario "date, with start and end", {
    given "start date, one month ago", {
        use(TimeCategory) {
            start = 1.month.ago
        }
    }
    
    given "end date, one month from now", {
        use(TimeCategory) {
            end = 1.month.from.now
        }
    }
    
    when "I call Moment.date(1.month.ago)", {
        dates = (1..1000).collect { Moment.date(start, end) }
    }
    
    then "it should return a date between one month ago and one month from now", {
        dates.each { date ->
            assert start <= date
            assert date <= end
        }
    }
}
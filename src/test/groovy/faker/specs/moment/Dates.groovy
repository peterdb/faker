package faker.specs.moment

import spock.lang.Specification
import faker.Moment
import groovy.time.TimeCategory

class Dates extends Specification {

    static beginningOfTime
    static today
    static oneMonthAgo
    static oneMonthFromNow
    
    def setupSpec() {
        use(TimeCategory) {
            beginningOfTime = new Date(0L).clearTime()
            today = new Date().clearTime()
            oneMonthAgo = 1.month.ago
            oneMonthFromNow = 1.month.from.now
        }
    }
    
    def "date #date, no parameters"(def date) {
        expect: "it should return a date between 01/01/1970 and today"
        assert beginningOfTime <= date
        assert date <= today

        where: "the date is generated using Moment.date()"
        date << (1..1000).collect { Moment.date() }
    }

    def "date, with start"(def date) {
        expect: "the date date is between one month ago and today"
        assert oneMonthAgo <= date
        assert date <= today

        where: "the date is generated using Moment.date(1.month.ago)"
        date << (1..1000).collect { Moment.date(oneMonthAgo) }
    }

    def "date, with start and end"(def date) {
        expect: "the date is between one month ago and one month from now"
        assert oneMonthAgo <= date
        assert date <= oneMonthFromNow

        where: "the date is generated using Moment.date(1.month.ago)"
        date << (1..1000).collect { Moment.date(oneMonthAgo, oneMonthFromNow) }
    }
}
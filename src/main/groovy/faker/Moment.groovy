package faker

import java.util.Date;
import java.util.List;

import faker.internal.FakerSupport;
import groovy.time.Duration;
import groovy.time.TimeCategory;
import groovy.time.TimeDuration;

/**
 * Faker for dates and times.
 * 
 * @author peter
 */
class Moment {
    private static FakerSupport support = new FakerSupport(Moment)

    // after is arbitrarily set to 1/1/1970 if not explicitly given
    // before is set to today if not set
    // both are inclusive
    /**
     * 
     * @param after
     * @param before
     * 
     * @return a {@link Date} between <code>after</code> and <code>before</code>
     */
    public static Date date(Date after = new Date(0L), Date before = new Date()) {
        assert after <= before
        
        after.clearTime()
        before.clearTime()
        
        use(TimeCategory) {
            return after + Moment.support.rnd.nextInt((before - after).days + 1)
        }
    }

    // before and after are the number of seconds into the day and both are inclusive
    // ondate is set to today if not explicitly stated
    public static Date time(Date onDate, Duration after, Duration before) {
        use(TimeCategory) {
            Duration interval = before - after

            return onDate.clearTime() + after + Moment.support.rnd.nextInt((int)(interval.toMilliseconds()/1000) + 1).seconds
        }
    }
    
    public static Date time(Date onDate = new Date(), Integer after = 0, Integer before = 60 * 60 * 24) {
        use(TimeCategory) {
            def interval = before - after

            return onDate.clearTime() + after.seconds + Moment.support.rnd.nextInt(interval + 1).seconds
        }
    }
    
    public static Date time(int after, int before) {
        use(TimeCategory) {
            return time(after.seconds, before.seconds)
        }
    }
    
    public static Date time(Duration after, Duration before) {
        return time(new Date(), after, before)
    }
    
    public static Date birthday(int age) {
        assert age >= 0
        
        use(TimeCategory) {
            return date((age + 1).years.ago + 1.day, age.years.ago)
        }
    }

    public static Date birthday(List age = 18..65) {
        assert age
        
        return birthday(age.sample())
    }
}

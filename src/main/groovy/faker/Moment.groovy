package faker

import java.util.Date;

import groovy.time.Duration;
import groovy.time.TimeCategory;
import groovy.time.TimeDuration;

/**
 * Faker for dates and times.
 * 
 * @author peterdb
 */
class Moment {
    private static Faker.Base delegate = new Faker.Base(Moment)

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
    public static Date date(Date after = null, Date before = null) {
        if(!after) {
            after = new Date(0L)
        }

        if(!before) {
            before = new Date()
        }
        
        assert after <= before
        
        after.clearTime()
        before.clearTime()

        use(TimeCategory) {
            return after + Moment.delegate.rnd.nextInt((before - after).days + 1)
        }
    }

    // before and after are the number of seconds into the day and both are inclusive
    // ondate is set to today if not explicitly stated
    public static Date time(Date onDate, Duration after, Duration before) {
        use(TimeCategory) {
            Duration interval = before - after

            return toDay(onDate) + after + Moment.delegate.rnd.nextInt((int)(interval.toMilliseconds()/1000) + 1).seconds
        }
    }
    
    public static Date time(Date onDate = null, Integer after = null, Integer before = null) {
        use(TimeCategory) {
            if(!onDate) {
                onDate = new Date()
            }

            if(after == null) {
                after = 0
            }

            if(before == null) {
                before = 60 * 60 * 24
            }

            def interval = before - after

            return toDay(onDate) + after.seconds + Moment.delegate.rnd.nextInt(interval + 1).seconds
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

    private static Date d(int year, int month, int day) {
        Calendar cal = Calendar.getInstance()
        cal.clear()
        cal.set(year, month, day)

        return cal.getTime()
    }
    
    private static Date toDay(Date date) {
        assert date
        
        Calendar cal = Calendar.getInstance()
        cal.setTime(date)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        
        return cal.getTime()
    }
}

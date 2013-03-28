package faker

import java.util.Date;

import faker.internal.FakerSupport;
import groovy.time.Duration;
import groovy.time.TimeCategory;
import groovy.time.TimeDuration;

/**
 * Faker for numbers.
 * 
 * @author peter
 */
class Numbers {
    private static FakerSupport support = new FakerSupport(Lorem)

    public static Double d(Number min = 0D, Number max) {
        return support.rnd.nextDouble() * (max - min) + min
    }

    public static BigDecimal bigDecimal(Number min = 0D, Number max) {
        return BigDecimal.valueOf(d(min, max))
    }
    
    public static Integer i(Number min = 0, Number max) {
        return support.rnd.nextInt() * (max - min) + min
    }
    
    public static Long l(Number min = 0L, Number max) {
        return support.rnd.nextLong() * (max - min) + min
    }
}

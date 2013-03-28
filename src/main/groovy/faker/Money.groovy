package faker

import java.math.RoundingMode;
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
class Money {
    private static FakerSupport support = new FakerSupport(Lorem)

    public static BigDecimal money(Number min = 0D, Number max) {
        return money(Currency.getInstance(Locale.default), min, max)
    }
    
    public static BigDecimal money(Currency currency, Number min = 0D, Number max) {
        assert currency
        
        BigDecimal value = Numbers.bigDecimal(min, max)
        return value.setScale(currency.defaultFractionDigits, RoundingMode.HALF_EVEN)
    }
}

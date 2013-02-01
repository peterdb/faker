package faker

import groovy.time.TimeCategory

import java.text.SimpleDateFormat

class CreditCard {
    
    // TODO make the numbers pass the luhn test
    
    private static Faker.Base delegate = new Faker.Base(CreditCard)
    
    public static void main(String[] args) {
        100.times {
            println CreditCard.number()
            println CreditCard.expirationDate()
        }
    }
    
    public static String number() {
        return delegate.parse("credit_card.number")
    }
    
    public static String visa() {
        return delegate.numerify(delegate.fetch("credit_card.visa"))
    }
    
    public static String mastercard() {
        return delegate.numerify(delegate.fetch("credit_card.mastercard"))
    }
    
    public static String amex() {
        return delegate.numerify(delegate.fetch("credit_card.amex"))
    }
    
    public static String expirationDate(Date after = null, int maxNumberOfMonths = 48) {
        use(TimeCategory) {
            if(!after) {
                after = new Date()
            }
            
            def monthsToAdvance = (1..maxNumberOfMonths).sample()
        
            def exp = after + monthsToAdvance.months
            
            return new SimpleDateFormat("MM/yy").format(exp)
        }
    }

    public static String expirationDate(int maxNumberOfMonths) {
        return expirationDate(new Date(), maxNumberOfMonths)
    }
}
package creditcard

import support.DateUtils
import faker.CreditCard
import groovy.time.TimeCategory

description """
In order to generate fake data
As a developer
I want to be able to create fake expiration dates
"""

scenario "Expiration date, no options",{
    when "I call CreditCard.expirationDate()", {
        exp = CreditCard.expirationDate()
    }

    then "the output should be a valid expiration date", {
        def parts = exp.split('/')
        month = parts[0] as int
        year = parts[1] as int

        assert month in 1..12
        assert year in 0..99
    }

    and "the date should be at most 48 months from now", {
        use(TimeCategory, DateUtils) {
            def expirationDate = [2000 + year, month, 1] as Date
        
            assert expirationDate - new Date() < 48.months
        }
    }
}
    
scenario "Expiration date, max num of months = 12",{
    when "I call CreditCard.expirationDate(12)", {
        exp = CreditCard.expirationDate(12)
    }

    then "the output should be a valid expiration date", {
        def parts = exp.split('/')
        month = parts[0] as int
        year = parts[1] as int

        assert month in 1..12
        assert year in 0..99
    }

    and "the date should be at most 12 months from now", {
        use(TimeCategory, DateUtils) {
            def expirationDate = [2000 + year, month, 1] as Date
            
            assert expirationDate - new Date() < 12.months
        }
    }
}
    
scenario "Expiration date, start date = 01/01/2000, max num of months = 24",{
    given "startdate = 01/01/2000", {
        date = [2000, 1, 1] as Date
    }
    
    when "I call CreditCard.expirationDate(01/01/2000, 24)", {
        exp = CreditCard.expirationDate(date, 24)
    }

    then "the output should be a valid expiration date", {
        def parts = exp.split('/')
        month = parts[0] as int
        year = parts[1] as int

        assert month in 1..12
        assert year in 0..99
    }

    and "the date should be at most 24 months from now", {
        use(TimeCategory, DateUtils) {
            def expirationDate = [2000 + year, month, 1]
            
            assert expirationDate - date < 24.months
        }
    }
}

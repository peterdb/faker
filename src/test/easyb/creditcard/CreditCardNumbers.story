package creditcard

import faker.CreditCard

description """
In order to generate fake data
As a developer
I want to be able to create fake credit card numbers
"""

before "", {
    AMEX = /^3(4|7)\d{2} \d{6} \d{5}$/
    VISA = /^4\d{3} \d{4} \d{4} \d{4}$/
    MASTERCARD = /^5\d{3} \d{4} \d{4} \d{4}$/
}

scenario "Amex",{
    when "I call CreditCard.amex()", {
        amex = CreditCard.amex()
    }
    
    then "the output should be an amex card number", {
        assert amex ==~ AMEX
    }
}

scenario "Visa",{
    when "I call CreditCard.visa()", {
        visa = CreditCard.visa()
    }
    
    then "the output should be a visa card number", {
        assert visa ==~ VISA
    }
}

scenario "Mastercard",{
    when "I call CreditCard.mastercard()", {
        mastercard = CreditCard.mastercard()
    }
    
    then "the output should be a mastercard card number", {
        assert mastercard ==~ MASTERCARD
    }
}

scenario "Random number",{
    when "I call CreditCard.number()", {
        cc = CreditCard.number()
    }
    
    then "the output should be a either an amex, visa or mastercard card number", {
        assert cc ==~ AMEX || cc ==~ VISA || cc ==~ MASTERCARD
    }
}
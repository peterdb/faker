package faker.specs.creditcard

import spock.lang.Shared;
import spock.lang.Specification
import spock.util.mop.Use;
import faker.CreditCard
import groovy.time.TimeCategory

@Use(TimeCategory)
class ExpirationDateTest extends Specification {
	@Shared
	def now = new Date()
	@Shared
	def ref_date = [2001, 1, 1] as Date
	
	def "expiration date must have correct format"(def exp) {
		expect: "the expiration date should have the correct format"
		assert exp ==~ /[0-1][0-9]\/[0-9]{2}/

		and: "the output"
		def parts = exp.split('/')
		def month = parts[0] as int
		def year = parts[1] as int

		assert month in 1..12
		assert year in 0..99

		where:
		exp << (1..1000).collect { CreditCard.expirationDate() }
	}


	def "expiration date should have correct month and year"(def exp, def month, def year) {
		expect: "month must be in 1..12"
		assert month in 1..12

		and: "year must be in 0..99"
		assert year in 0..99

		where:
		exp << (1..1000).collect { CreditCard.expirationDate() }
		month = (exp[0..1] as int)
		year = (exp[3..4] as int)
	}

	def "expiration date, no options"() {
		expect: "the date must be at most 48 months from now"
		assert date - now <= 48.months

		where:
		exp << (1..1000).collect { CreditCard.expirationDate() }
		month = (exp[0..1] as int)
		year = (exp[3..4] as int)
		date = [2000 + year, month, 1] as Date
	}
	
	def "expiration date, max num of months = 12"() {
		expect: "the date must be at most 12 months from now"
		assert date - now <= 12.months

		where:
		exp << (1..1000).collect { CreditCard.expirationDate(12) }
		month = (exp[0..1] as int)
		year = (exp[3..4] as int)
		date = [2000 + year, month, 1] as Date
	}
	
	def "expiration date, start date = 01/01/2000, max num of months = 24"() {
		expect: "the date must be at most 24 months from ref date"
		assert date - ref_date <= 24.months

		where:
		exp << (1..1000).collect { CreditCard.expirationDate(ref_date, 24) }
		month = (exp[0..1] as int)
		year = (exp[3..4] as int)
		date = [2000 + year, month, 1] as Date
	}
}

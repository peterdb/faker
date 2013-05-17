package faker.specs.creditcard

import org.spockframework.runtime.MasterRunListener;

import spock.lang.Specification;
import faker.CreditCard

class CardNumberTest extends Specification {
	
	def AMEX = /^3(4|7)\d{2} \d{6} \d{5}$/
	def VISA = /^4\d{3} \d{4} \d{4} \d{4}$/
	def MASTERCARD = /^5\d{3} \d{4} \d{4} \d{4}$/
	
	def AmericanExpress(def cc) {
		expect: "the output should be an amex number"
		assert cc ==~ AMEX
		
		where: "card number is generated using CreditCard.amex()"
		cc << (1..1000).collect { CreditCard.amex() }
	}
	
	def Visa(def cc) {
		expect: "the output should be a visa number"
		assert cc ==~ VISA
		
		where: "card number is generated using CreditCard.visa()"
		cc << (1..1000).collect { CreditCard.visa() }
	}
	
	def Mastercards(def cc) {
		expect: "the output should be a mastercard number"
		assert cc ==~ MASTERCARD
		
		where: "card number is generated using CreditCard.mastercard()"
		cc << (1..1000).collect { CreditCard.mastercard() }
	}
	
	def "random cc number"(def cc) {
		expect: "the output should be a either an amex, visa or mastercard card number"
		assert cc ==~ AMEX || cc ==~ VISA || cc ==~ MASTERCARD
		
		where: "card number is generated using CreditCard.number()"
		cc << (1..1000).collect { CreditCard.number() }
	}
}

package faker

import faker.internal.FakerSupport;
import groovy.time.TimeCategory

import java.text.SimpleDateFormat

class CreditCard {

	// TODO make the numbers pass the luhn test

	private static FakerSupport support = new FakerSupport(CreditCard)

	public static String number() {
		return support.getString("credit_card.number")
	}

	public static String visa() {
		return support.getString("credit_card.visa")
	}

	public static String mastercard() {
		return support.getString("credit_card.mastercard")
	}

	public static String amex() {
		return support.getString("credit_card.amex")
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
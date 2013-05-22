package faker.specs.support

import spock.lang.Specification;

@Localized("en_US_bork")
class LocalizedTest extends Specification {

	def "Localized annotation"() {
		expect:
		assert l == new Locale("en", "US", "bork")
		
		where:
		l = Locale.default
	}
	
}

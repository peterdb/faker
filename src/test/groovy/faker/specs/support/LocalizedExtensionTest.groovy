package faker.specs.support;

import static org.junit.Assert.*;

import org.junit.Test;

class LocalizedExtensionTest {

	@Test
	public void toLocale() {
		assert new Locale("en") == LocalizedExtension.toLocale("en")
		assert new Locale("en", "US") == LocalizedExtension.toLocale("en_US")
		assert new Locale("en", "US", "bork") == LocalizedExtension.toLocale("en_US_bork")
	}
}

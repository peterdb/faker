package faker.i18n

class I18n {

	/**
	 * Method for easier working with locales
	 */
	static withLocale(Locale locale, Closure closure) {
		assert locale, "locale cannot be null"
		assert closure, "closure cannot be null"
		
		Locale original = Locale.getDefault()
		
		Locale.setDefault(locale)
		
		try {
			return closure()
		} finally {
			Locale.setDefault(original)
		}
	}
	
}

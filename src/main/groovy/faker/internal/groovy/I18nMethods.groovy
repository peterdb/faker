package faker.internal.groovy

class I18nMethods {

    /**
     * Method for easier working with locales
     */
    static localized(Object selfType, Locale locale, Closure closure) {
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

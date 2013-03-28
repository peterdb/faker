package faker

import faker.i18n.YamlResourceBundleControl

/**
 * Faker configuration.
 * 
 * @author peter
 */
class Faker {

    def static Locale locale
    def static ResourceBundle bundle

    static {
        setLocale(Locale.default)
    }
    
    public static void setLocale(Locale l) {
        locale = l
        bundle = ResourceBundle.getBundle("faker", locale, Faker.class.getClassLoader(), new YamlResourceBundleControl())
    }
}

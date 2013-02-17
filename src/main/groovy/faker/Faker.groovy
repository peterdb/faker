package faker

import java.util.regex.Pattern

import faker.internal.Bundles
import faker.i18n.YamlResourceBundleControl

/**
 * Faker configuration.
 * 
 * @author peter
 */
class Faker {

    def static Locale locale
    def static Bundles bundles = new Bundles()

    static {
        setLocale(Locale.default)
    }
    
    public static void setLocale(Locale l) {
        locale = l
        
        bundles = new Bundles()
        bundles << ResourceBundle.getBundle("faker", locale, Faker.class.getClassLoader(), new YamlResourceBundleControl())
    }
}

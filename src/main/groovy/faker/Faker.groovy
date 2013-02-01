package faker

import java.util.ResourceBundle.BundleReference;
import java.util.regex.Pattern;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import faker.i18n.YamlResourceBundleControl

class Faker {

    private static ResourceBundleHolder bundleHolder = new ResourceBundleHolder()
    public static final Config config = new Config()
    
    private static class ResourceBundleHolder {
        def ResourceBundle bundle
        
        public ResourceBundle getBundle() {
            if(bundle == null) {
                bundle = ResourceBundle.getBundle("faker", config.locale, Faker.class.getClassLoader(), new YamlResourceBundleControl());
            }
            
            return bundle
        }
    }

    public static void reload() {
        bundleHolder.bundle = null
    }
    
    public static class Base {
        private static final List Numbers = 0..9
        private static final List ULetters = 'A'..'Z'
        private static final List Letters = ULetters + ('a'..'z')
        
        protected static Random rnd = new Random()
        
        final Class fakerClass
        
        public Base(Class fakerClass) {
            assert fakerClass != null
            
            this.fakerClass = fakerClass
            
            // example: add girls_name: [...] to a yml file, then you can call Name.girlsName or Name.girls_name
            fakerClass.metaClass.static.methodMissing = { method, ignoredArgs ->
                try {
                    fetch([fakerClass.simpleName.toLowerCase(), method].join("."))
                } catch (MissingResourceException e) {
                    try {
                        fetch([fakerClass.simpleName.toLowerCase(), unCamelCaseify(method)].join("."))
                    } catch (MissingResourceException e2) {
                        throw new MissingMethodException(method, fakerClass, ignoredArgs, true)
                    }
                }
            }
        }
        
        /** 
         * Helper method for the common approach of a translation
         * with a list of values and selecting one of them
         */
        protected String fetch(String key) {
            def fetched = translate(key)
            
            if(fetched instanceof List) {
                // TODO fetch random item
                fetched = fetched.sample()
            }
            if(false /*fetched ==~ */) {
                // TODO regexify
            } else {
                return fetched
            }
        }
        
        protected Object translate(String key) {
            assert key, "key cannot be null or empty"
            
            return Faker.bundleHolder.bundle.getObject("faker.$key")
        }
        
        protected String parse(String key) {
            return fetch(key).scan(~/#\{([A-Za-z]+\.)?([^\}]+)\}([^#]+)?/).collect { matcher ->
                evaluate(*matcher[1..-1])
            }.join()
        }
        
        private String evaluate(String klass, String method, String etc) {
            // If the token had a class Prefix (e.g., Name.first_name) grab the constant, otherwise use self
            Class target = klass ? Class.forName("faker.${klass.chop()}") : this.fakerClass
            
            def text
            
            // If the class has the method, call it, otherwise
            // fetch the translation (i.e., faker.name.first_name)
            def javaMethod = camelCaseify(method)
            if(target.metaClass.respondsTo(target, javaMethod)) {
                text = target.metaClass.invokeMethod(target, javaMethod, null)
            } else {
                text = fetch(target.simpleName.toLowerCase()+"."+method)
            }
            
            return text + (etc ?: '')
        }
        
        private static String camelCaseify(String s) {
            def first = true
            
            s.split('_').collect { String it ->
                def result = first ? it : it.capitalize()
                first = false
                return result
            }.join()
        }
        
        private static String unCamelCaseify(String s) {
            return s.split("(?<!^)(?=[A-Z])").collect { it.toLowerCase() }.join('_')
        }
        
//        # Given a regular expression, attempt to generate a string
//        # that would match it.  This is a rather simple implementation,
//        # so don't be shocked if it blows up on you in a spectacular fashion.
//        #
//        # It does not handle ., *, unbounded ranges such as {1,},
//        # extensions such as (?=), character classes, some abbreviations
//        # for character classes, and nested parentheses.
//        #
//        # I told you it was simple. :) It's also probably dog-slow,
//        # so you shouldn't use it.
//        #
//        # It will take a regex like this:
//        #
//        # /^[A-PR-UWYZ0-9][A-HK-Y0-9][AEHMNPRTVXY0-9]?[ABEHMNPRVWXY0-9]? {1,2}[0-9][ABD-HJLN-UW-Z]{2}$/
//        #
//        # and generate a string like this:
//        #
//        # "U3V  3TP"
//        #
        public String regexify(String re) {
            return re.
                replaceAll(~/^\/?\^?/, '').replaceAll(~/\$?\/?$/, '').                                                                  // Ditch the anchors
                replaceAll(~/\{(\d+)\}/, '{\1,\1}').replaceAll(~/\?/, '{0,1}').                                                             // All {2} become {2,2} and ? become {0,1}
                replaceAll(~/(\[[^\]]+\])\{(\d+),(\d+)\}/) { m -> m[1] * (((m[2] as Integer)..(m[3] as Integer)).sample()) }.                // [12]{1,2} becomes [12] or [12][12]
                replaceAll(~/(\([^\)]+\))\{(\d+),(\d+)\}/) { m -> m[1] * (((m[2] as Integer)..(m[3] as Integer)).sample()) }.                // (12|34){1,2} becomes (12|34) or (12|34)(12|34)
                replaceAll(~/(\\?.)\{(\d+),(\d+)\}/) { m -> m[1] * (((m[2] as Integer)..(m[3] as Integer)).sample()) }.                      // A{1,2} becomes A or AA or \d{3} becomes \d\d\d
                replaceAll(~/\((.*?)\)/) { m -> (m[0].replaceAll(~/[\(\)]/, '').split('|') as List).sample() }.                                      // (this|that) becomes 'this' or 'that'
                replaceAll(~/\[([^\]]+)\]/) { m -> m[0].replaceAll(~/(\w)\-(\w)/) { range -> (range[1]..range[2]).sample() } }. // All A-Z inside of [] become C (or X, or whatever)
                replaceAll(~/\[([^\]]+)\]/) { m -> (m[1].split('') as List).sample() }.                                                          // All [ABC] become B (or A or C)
                replaceAll(~/\\d/) { Numbers.sample() }.
                replaceAll(~/\\w/) { Letters.sample() }
        }
        
        public String regexify(Pattern pattern) {
            return regexify(pattern.toString())
        }
        
        protected String numerify(String numberString) {
            // make sure numerify results doesn’t start with a zero
            numberString.replaceFirst(~/#/) { rnd.nextInt(9) + 1 }.replaceAll(~/#/) { rnd.nextInt(10) }
        }
        
        protected String letterify(String letterString) {
            return letterString.replaceAll(~/\?/) { ULetters.sample() }
        }

        protected String bothify(String string) {
            return letterify(numerify(string))
        }
    }
    
}

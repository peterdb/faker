package faker.internal

import java.util.List;
import java.util.Random;

import faker.Faker
import faker.Strings

class FakerSupport {
    private static final List UPPERCASE = 'A'..'Z'
    private Random rnd = new Random()

    final Class fakerClass

    public FakerSupport(Class fakerClass) {
        assert fakerClass != null

        this.fakerClass = fakerClass

        // example: add girls_name: [...] to a yml file, then you can call Name.girlsName or Name.girls_name
        fakerClass.metaClass.static.methodMissing = { method, ignoredArgs ->
            try {
                getString([
                    fakerClass.simpleName.toLowerCase(),
                    method
                ].join("."))
            } catch (MissingResourceException e) {
                try {
                    getString([
                        fakerClass.simpleName.toLowerCase(),
                        unCamelCaseify(method)
                    ].join("."))
                } catch (MissingResourceException e2) {
                    throw new MissingMethodException(method, fakerClass, ignoredArgs, true)
                }
            }
        }
    }

    protected getObject(String key) {
        assert key
        
        return Faker.bundle["faker.$key"]
    }
    
    /** 
     * Helper method for the common approach of a translation
     * with a list of values and selecting one of them
     */
    protected String getString(String key) {
        def raw = getObject(key)

        if(raw instanceof List) {
            raw = raw.sample()
        }

        // process the fetched string
        if(raw.matches(/^\/.*\/$/)) {
            // regular expression, so use Strings.xeger
            return Strings.xeger(raw)
        } else if(raw.matches(/.*#\{.*}.*/)) {
            println raw
        
            // object expression
            return raw.scan(~/#\{([A-Za-z]+\.)?([^\}]+)\}([^#]+)?/).collect { matcher ->
                evaluate(*matcher[1..-1])
            }.join()
        } else {
            return processPattern(raw)
        }
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
            text = getString(target.simpleName.toLowerCase()+"."+method)
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
        return s.split("(?<!^)(?=[AZ])").collect { it.toLowerCase() }.join('_')
    }

    private String processPattern(String raw) {
        // make sure numerify results doesn’t start with a zero
        raw = raw.replaceFirst(~/#/) { rnd.nextInt(9) + 1 }.replaceAll(~/#/) { rnd.nextInt(10) }

        raw = raw.replaceAll(~/\?/) { UPPERCASE.sample() }
        
        return raw
    }
}

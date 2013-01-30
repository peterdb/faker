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

	static {
		[Address, Company, Internet, Lorem, Name].each { Class fakerClass ->
			// make all no-arg methods into properties
			fakerClass.getDeclaredMethods().each { Method m ->
				if(Modifier.isStatic(m.getModifiers()) && Modifier.isPublic(m.getModifiers()) && m.getParameterTypes().length == 0) {
					String getter = ["get", m.name.capitalize()].join()
					
					fakerClass.metaClass.static."$getter" = { m.invoke(null) }
				}
			}
		}
	}
	
	public static class Base {
		private static final List Numbers = 0..9
		private static final List ULetters = 'A'..'Z'
		private static final List Letters = ULetters + ('a'..'z')
		
		protected static Random rnd = new Random()
		
		/** 
		 * Helper method for the common approach of a translation
		 * with a list of values and selecting one of them
		 */
		protected static String fetch(String key) {
			def fetched = translate(key)
			
			if(fetched instanceof List) {
				// TODO fetch random item
				fetched = fetched[rnd.nextInt(fetched.size)]
			}
			if(false /*fetched ==~ */) {
				// TODO regexify
			} else {
				return fetched
			}
		}
		
		protected static Object translate(String key) {
			assert key, "key cannot be null or empty"
			
			try {
				return Faker.bundleHolder.bundle.getObject("faker.$key")
			} catch (MissingResourceException e) {
				return ""
			}
		}
		
		protected static String parse(Class caller, String key) {
			Binding b = new Binding()
			b.setVariable("delegate", caller)
			GroovyShell sh = new GroovyShell(b)
			
			def expression = fetch(key)
			
			println expression
			return sh.evaluate("import faker.*; delegate.with { ignore -> return \"$expression\"}")
		}
		
//		# Given a regular expression, attempt to generate a string
//		# that would match it.  This is a rather simple implementation,
//		# so don't be shocked if it blows up on you in a spectacular fashion.
//		#
//		# It does not handle ., *, unbounded ranges such as {1,},
//		# extensions such as (?=), character classes, some abbreviations
//		# for character classes, and nested parentheses.
//		#
//		# I told you it was simple. :) It's also probably dog-slow,
//		# so you shouldn't use it.
//		#
//		# It will take a regex like this:
//		#
//		# /^[A-PR-UWYZ0-9][A-HK-Y0-9][AEHMNPRTVXY0-9]?[ABEHMNPRVWXY0-9]? {1,2}[0-9][ABD-HJLN-UW-Z]{2}$/
//		#
//		# and generate a string like this:
//		#
//		# "U3V  3TP"
//		#
		public static String regexify(String re) {
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
		
		public static String regexify(Pattern pattern) {
			return regexify(pattern.toString())
		}
		
		protected static String numerify(String numberString) {
			// make sure numerify results doesn’t start with a zero
			numberString.replaceFirst(~/#/) { rnd.nextInt(9) + 1 }.replaceAll(~/#/) { rnd.nextInt(10) }
		}
		
		protected static String letterify(String letterString) {
	    	return letterString.replaceAll(~/\?/) { ULetters.sample() }
		}

		protected static String bothify(String string) {
        	return letterify(numerify(string))
		}
		
	}
	
}

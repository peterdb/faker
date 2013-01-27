package faker

import faker.i18n.YamlResourceBundleControl

class Faker {

	private static ResourceBundle BUNDLE
	
	static {
		BUNDLE = ResourceBundle.getBundle("faker", new Locale("nl"), Name.class.getClassLoader(), new YamlResourceBundleControl());
	}
	
	static class Name extends Base {
		public static String getName() {
			return parse("name.name")
		}
		
		public static String getFirstName() {
			return fetch("name.first_name")
		}
		
		public static String getLastName() {
			return fetch("name.last_name")
		}
		
		public static String getPrefix() {
			return fetch("name.prefix")
		}

		public static String getSuffix() {
			return fetch("name.suffix")
		}

		public static String getTitle() {
			return fetch('name.title.descriptor') + ' ' + fetch('name.title.level') + ' ' + fetch('name.title.job')
		}
	}
	
	private static class Base {
		private static Random random = new Random()
		
		/** 
		 * Helper method for the common approach of a translation
		 * with a list of values and selecting one of them
		 */
		protected static String fetch(String key) {
			def fetched = Faker.BUNDLE.getObject("faker.$key")
			
			if(fetched instanceof List) {
				// TODO fetch random item
				fetched = fetched[random.nextInt(fetched.size)]
			}
			if(false /*fetched ==~ */) {
				// TODO regexify
			} else {
				return fetched
			}
		}
		
		protected static String parse(String key) {
			String fetched = fetch(key)
			
			fetched = fetched.replaceAll("#{")
			
			Eval.me
		}
		
//		# Load formatted strings from the locale, "parsing" them
//		# into method calls that can be used to generate a
//		# formatted translation: e.g., "#{first_name} #{last_name}".
//		def parse(key)
//		  fetch(key).scan(/#\{([A-Za-z]+\.)?([^\}]+)\}([^#]+)?/).map {|kls, meth, etc|
//			# If the token had a class Prefix (e.g., Name.first_name)
//			# grab the constant, otherwise use self
//			cls = kls ? Faker.const_get(kls.chop) : self
//  
//			# If the class has the method, call it, otherwise
//			# fetch the transation (i.e., faker.name.first_name)
//			text = cls.respond_to?(meth) ? cls.send(meth) : fetch("#{(kls || self).to_s.split('::').last.downcase}.#{meth.downcase}")
//  
//			# And tack on spaces, commas, etc. left over in the string
//			text += etc.to_s
//		  }.join
//		end
	}
	
}

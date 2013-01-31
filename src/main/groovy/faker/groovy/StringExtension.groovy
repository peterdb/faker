package faker.groovy

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Category(String)
class StringExtension {
	// TODO test this
	
	List<String> scan(Pattern p, Closure c = null) {
		Matcher m = p.matcher(this)
		
		return m.collect() { match ->
			return c ? c(match) : match
		}
	}
	
	String chop() {
		this[0..-2]
	}
	
}

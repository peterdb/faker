package faker.util

class RubyYml2GroovyYml {

	public static void main(String[] args) {
		def yml = "faker/original/fr.yml"
		
		def loader = RubyYml2GroovyYml.class.getClassLoader()
		def stream = loader.getResourceAsStream(yml)
		
		def text = stream.getText("utf-8")
		
		println text.replaceAll(~/#\{([^\}]*)}/) { ignore, content ->
			def cc = toCamelCase(content)
			
			"\${$cc}"
		}
	}
	
	private static String toCamelCase(String s) {
		def first = true
		
		s.split('_').collect { String it -> 
			def result = first ? it : it.capitalize()
			first = false
			return result
		}.join()
	}
	
}

package faker.smoke;

import static org.junit.Assert.*

import java.lang.reflect.Modifier

import spock.lang.Specification
import spock.lang.Unroll
import faker.Address
import faker.Faker
import faker.Strings;

class SmokeTest extends Specification {

	@Unroll("load locale #locale")
	def "locale can be loaded"(def locale) {
		setup:
		Faker.locale = locale

		expect:
		assert locale == Faker.locale

		where:
		locale << locales()
	}

	@Unroll("faker method call #locale - #test")
	def "faker method call"(def test, def locale) {
		setup:
		Faker.locale = locale

		expect:
		100.times { test.run() }

		where:
		[test, locale]<< [tests(), locales()].combinations()
	}

	// the locales
	static locales() {
		def fakerDir = "src/main/resources/faker" as File

		return fakerDir.list().collect { f ->
			def name = f.split(/\./)[0]

			def localeParts = name.split(/-/)

			new Locale(*localeParts)
		}
	}

	// the tests to run
	static tests() {
		def fakerDir = "src/main/groovy/faker" as File
		assert fakerDir.exists()
		
		def classes = fakerDir.listFiles({ f -> f.isFile() } as FileFilter).collect { f ->
			Class.forName("faker."+f.name.split(/\./)[0])
		}

		classes.collectMany { clazz ->
			clazz.declaredMethods.grep { m ->
				// TODO also test methods that accept parameters (generate parameters using fakers, for example String -> Strings.al)
				Modifier.isPublic(m.modifiers) && Modifier.isStatic(m.modifiers) && m.returnType != Void.TYPE && !m.parameterTypes
			}.collect { m ->
				new FakerMethodCall(m.toString(), { m.invoke(null) })
			}
		}
	}

	static class FakerMethodCall {
		def name
		def test

		public FakerMethodCall(def name, def test) {
			this.name = name
			this.test = test
		}

		def run() {
			test()
		}

		public String toString() {
			name
		}
	}


}

package address


import faker.Address
import faker.Faker;
import faker.i18n.I18n
import faker.i18n.MapBasedResourceBundle

scenario "default city formats", {
	given "minimal resource bundle for testing", {
		xx = [
			faker: [
				name: [
					first_name : ['alice'], 
					last_name: ['smith']],
				address: [
					city_prefix: ['west'],
					city_suffix: ['burg']]
			]]
		
		def bundle = new MapBasedResourceBundle(xx)
		bundle.parent = Faker.bundleHolder.bundle
		Faker.bundleHolder.bundle = bundle
	}
	
	when "cities are created", {
		I18n.withLocale(new Locale("xx")) {
			cities = (1..100).collect { Address.city() }
		}
	}
	
	then "consisting", {
		cities.each { city -> 
			def expected = ["west alice", "west smith", "west aliceburg", "west smithburg", "aliceburg", "smithburg"]
			assert expected.contains(city), "Expected <${expected.join(' / ')}>, but got ${city}"
		}
	}
}

scenario "city formats are flexible", {
	given "minimal resource bundle for testing", {
		xy = [
			faker: [
				address: [
					city_prefix: ['big'],
					city_root: ['rock'],
					city_root_suffix: ['ing'],
					city_suffix: ['town'],
					city: ['#{city_prefix} #{city_root}#{city_root_suffix} #{city_suffix}']
					]]]
		
		def bundle = new MapBasedResourceBundle(xy)
		bundle.parent = Faker.bundleHolder.bundle
		Faker.bundleHolder.bundle = bundle
	}
	
	when "cities are created", {
		city = Address.city()
	}
	
	then "consisting", {
		assert "big rocking town" == city
	}
}
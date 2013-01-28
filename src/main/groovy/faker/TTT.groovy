package faker

import faker.groovy.ListExtension;

class TTT {
	public static void main(String[] args) {
//		println "een twee".scan(~/\w+/) { match -> match.toUpperCase() }
		
//		println (['com', 'org', 'net'].shuffle())
		
//		3.times {
//			println (['com', 'org', 'net'].sample())
//		}

		println faker.Internet.userName("Veerle Naudts")
		
		println faker.Internet.IPv4Address()
		3.times {
			println faker.Internet.url()
		}
		
		5.times {
			println faker.Internet.userName()
			println faker.Internet.email()
			println faker.Internet.freeEmail()
			println faker.Internet.safeEmail()
		}
		
		5.times {
			println faker.Company.name
			println faker.Company.catchPhrase()
			println faker.Company.bs()
			println "----"
		}

//		10.times {
//			println faker.Name.firstName()
//			println faker.Name.title()
//		}
	}
}

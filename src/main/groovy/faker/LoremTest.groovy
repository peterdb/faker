package faker

class LoremTest {
	public static void main(String[] args) {
		
//		println faker.Lorem.characters()
//		
//		println faker.Lorem.characters(5)
//		println faker.Lorem.characters(25)
//		println faker.Lorem.characters(128)
		
		10.times {
			println faker.Lorem.characters(1..10)
		}
		
		3.times {
			println faker.Lorem.sentence()
		}
		
//		15.times {
//			println faker.Lorem.word()
//		}
//		
//		println faker.Lorem.words(5)
//		5.times {
//			println faker.Lorem.words((1..10))
//		}
		
		def paragraphs = faker.Lorem.paragraphs(5)
		println paragraphs.size()
		println faker.Lorem.paragraph()
	}
}

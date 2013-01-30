package faker

class Lorem extends Faker.Base {
	public static String word() {
		return translate('lorem.words').sample()
	}

	public static List words(int wordCount = 3, boolean supplemental = false) {
		return (translate('lorem.words') + (supplemental ? translate('lorem.supplemental') : [])).shuffle()[0..<wordCount]
	}
	
	public static List words(Range wordCount, boolean supplemental = false) {
		return words(wordCount.sample(), supplemental)
	}

	public static String characters(int charCount = 255) {
		def alphabet = ('a'..'z') + ('A'..'Z') + (0..9)
		return alphabet.sample(charCount).join()		
	}
	
	public static String characters(Range charCount) {
		return characters(charCount.sample())
	}

	public static String sentence(int wordCount = 4, boolean supplemental = false) {
		return words(wordCount + rnd.nextInt(6), supplemental).join(' ').capitalize() + '.'
	}
	
	public static String sentence(Range wordCount, boolean supplemental = false) {
		return sentence(wordCount.sample(), supplemental)
	}

	public static List sentences(int sentenceCount = 3, supplemental = false) {
		return (1..sentenceCount).collect { sentence(4, supplemental) }
	}
	
	public static List sentences(Range sentenceCount, supplemental = false) {
		return sentences(sentenceCount.sample())
	}

	public static String paragraph(int sentenceCount = 3, supplemental = false) {
		return sentences(sentenceCount + rnd.nextInt(3), supplemental).join(' ')
	}

	public static String paragraph(Range sentenceCount, supplemental = false) {
		return sentences(sentenceCount.sample())
	}

	public static List paragraphs(int paragraphCount = 3, supplemental = false) {
		return (1..paragraphCount).collect { paragraph(3, supplemental) }
	}	

    public static List paragraphs(Range paragraphCount, supplemental = false) {
	  return paragraphs(paragraphCount.sample(), supplemental)
	}
}

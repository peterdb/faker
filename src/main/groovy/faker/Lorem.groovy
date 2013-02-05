package faker

class Lorem {
    private static Faker.Base delegate = new Faker.Base(Lorem)

    public static String word() {
        return delegate.translate('lorem.words').sample()
    }
    
    public static List words(boolean supplemental = false) {
        return words(3, supplemental)
    }

    public static List words(int wordCount, boolean supplemental = false) {
        return (delegate.translate('lorem.words') + (supplemental ? delegate.translate('lorem.supplemental') : [])).sample(wordCount)
    }
    
    public static List words(List wordCount, boolean supplemental = false) {
        return words(wordCount.sample(), supplemental)
    }

    public static String characters(int charCount = 255) {
        def alphabet = ('a'..'z') + ('A'..'Z') + (0..9)
        return alphabet.sample(charCount).join()        
    }
    
    public static String characters(List charCount) {
        return characters(charCount.sample())
    }
    
    public static String sentence(boolean supplemental = false) {
        return sentence(4..10, supplemental)
    }

    public static String sentence(int wordCount, boolean supplemental = false) {
        return words(wordCount, supplemental).join(' ').capitalize() + '.'
    }
    
    public static String sentence(List wordCount, boolean supplemental = false) {
        return sentence(wordCount.sample(), supplemental)
    }

    public static List sentences(boolean supplemental = false) {
        return sentences(3, supplemental)
    }
    
    public static List sentences(int sentenceCount, boolean supplemental = false) {
        return (1..sentenceCount).collect { sentence(4, supplemental) }
    }
    
    public static List sentences(List sentenceCount, boolean supplemental = false) {
        return sentences(sentenceCount.sample())
    }

    public static String paragraph(boolean supplemental = false) {
        return paragraph(3..6, supplemental)
    }
    
    public static String paragraph(List sentenceCount, boolean supplemental = false) {
        return paragraph(sentenceCount.sample())
    }
    
    public static String paragraph(int sentenceCount, boolean supplemental = false) {
        return sentences(sentenceCount, supplemental).join(' ')
    }

    public static List paragraphs(boolean supplemental = false) {
        return paragraphs(3, supplemental)
    }

    public static List paragraphs(int paragraphCount, boolean supplemental = false) {
        return (1..paragraphCount).collect { paragraph(3, supplemental) }
    }    

    public static List paragraphs(List paragraphCount, boolean supplemental = false) {
      return paragraphs(paragraphCount.sample(), supplemental)
    }
}

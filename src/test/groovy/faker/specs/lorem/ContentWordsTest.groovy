package faker.specs.lorem

import spock.lang.Specification
import faker.Faker
import faker.Lorem
import faker.specs.support.Localized


@Localized
class ContentWordsTest extends Specification {

    def static standardWordList
    def static completeWordList

    def setupSpec() {
        standardWordList = Faker.bundle["faker.lorem.words"]
        completeWordList = standardWordList + Faker.bundle["faker.lorem.supplemental"]
    }

    def "words should come from standard word list"(def word) {
        expect: "all the words should be from the standard word list"
        assert word in standardWordList

        where: "the words are generated using Lorem.words(1000)"
        word << Lorem.words(1000)
    }

    def "words requested from the supplemental list"(def word) {
        expect: "all the words should be from the standard + the supplemental list"
        assert word in completeWordList

        where: "the words are generated using Lorem.words(1000, true)"
        word << Lorem.words(1000, true)
    }

    def "word, should return random word from standard wordlist"(def word) {
        expect: "the word comes from the standard wordlist"
        assert word in standardWordList

        where: "the word is generated using Lorem.word()"
        word << (1..1000).collect { Lorem.word() }
    }
}
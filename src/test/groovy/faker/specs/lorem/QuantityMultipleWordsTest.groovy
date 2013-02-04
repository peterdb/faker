package faker.specs.lorem

import spock.lang.Specification
import faker.Lorem
import faker.specs.support.Localized

@Localized
class QuantityMultipleWordsTest extends Specification {

    def "words, no number of words"(def words) {
        expect: "the resulting list should be 3 words"
        assert words.size() == 3

        where: "the words are generated by Lorem.words()"
        words << (1..100).collect { Lorem.words() }
    }

    def "words, exact number of words"(def words) {
        expect: "the resulting list should be 5 words"
        assert words.size() == 5

        where: "the words are generated by Lorem.words(5)"
        words << (1..100).collect { Lorem.words(5) }
    }

    def "words, range number of words"(def words) {
        expect: "the resulting list should be 3..6 words"
        assert words.size() in 3..6

        where: "the words are generated by Lorem.words(3..6)"
        words << (1..100).collect { Lorem.words(3..6) }
    }

    def "words, list number of words"(def words) {
        expect: "the resulting list should be 1 or 4 words"
        assert words.size() in [1, 4]

        where: "the words are generated by Lorem.words([1, 4])"
        words << (1..100).collect { Lorem.words([1, 4]) }
    }
}
package faker.specs.lorem

import spock.lang.Specification
import faker.Lorem
import faker.specs.support.Localized

@Localized
class QuantitySingleSentenceTest extends Specification {
    def "sentence, no number of words"(def sentence) {
        expect: "the sentences should have 4-10 words"
        assert sentence.split(' ').size() in 4..10

        where: "the sentences are generated using Lorem.sentence()"
        sentence << (1..100).collect { Lorem.sentence() }
    }

    def "sentence, exact number of words"(def sentence) {
        expect: "the sentences should exactly 5 words"
        assert sentence.split(' ').size() == 5

        where: "the sentences are generated using Lorem.sentence(5)"
        sentence << (1..100).collect { Lorem.sentence(5) }
    }

    def "sentence, range number of words"(def sentence) {
        expect: "the sentences should have 3..8 words"
        assert sentence.split(' ').size() in 3..8

        where: "the sentences are generated using Lorem.sentence(3..8)"
        sentence << (1..100).collect { Lorem.sentence(3..8) }
    }

    def "sentence, list number of words"(def sentence) {
        expect: "the sentences should have 3, 5 or 7 words"
        assert sentence.split(' ').size() in [3, 5, 7]

        where: "the sentences are generated using Lorem.sentence([3, 5, 7])"
        sentence << (1..100).collect { Lorem.sentence([3, 5, 7]) }
    }
}
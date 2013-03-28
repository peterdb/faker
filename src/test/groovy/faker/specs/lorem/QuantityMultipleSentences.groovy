package faker.specs.lorem

import spock.lang.Specification
import faker.Lorem
import faker.specs.support.Localized

@Localized
class QuantityMultipleSentences extends Specification {

    def "sentences, no number of sentences"(def sentences) {
        expect: "the resulting list should be 3 sentences"
        assert sentences.size() == 3

        where: "I call Lorem.sentences()"
        sentences <<  (1..100).collect { Lorem.sentences() }
    }
    def "sentences, exact number of sentences"(def sentences) {
        expect: "the resulting list should be 5 sentences"
        assert sentences.size() == 5

        where: "I call Lorem.sentences(5)"
        sentences <<  (1..100).collect { Lorem.sentences(5) }
    }

    def "sentences, range number of sentences"(def sentences) {
        expect: "the resulting list should be 3..6 sentences"
        assert sentences.size() in 3..6

        where: "I call Lorem.sentences(3..6)"
        sentences <<  (1..100).collect { Lorem.sentences(3..6) }
    }

    def "sentences, list number of sentences"(def sentences) {
        expect: "the resulting list should be 1 or 4 sentences"
        assert sentences.size() in [1, 4]

        where: "I call Lorem.sentences([1, 4])"
        sentences <<  (1..100).collect { Lorem.sentences([1, 4]) }
    }
}
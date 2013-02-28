package faker.specs.lorem

import spock.lang.Specification;
import faker.Faker;
import faker.Lorem;
import faker.specs.support.Localized;

@Localized
class QuantitySingleWord extends Specification {

    def "word, no number of sentences"(def word) {
        expect: "the words should be valid words"
        assert word ==~ /\w+/

        where: "the word is generated by Lorem.word()"
        word << (1..100).collect { Lorem.word() }
    }
}

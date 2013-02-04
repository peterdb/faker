package faker.specs.strings

import spock.lang.Specification
import faker.Strings

class AlphanumericTest extends Specification {

    def "alphanumeric with fixed length"(def string) {
        expect: "the generated string is an alphanumeric string"
        assert string ==~ /[0-9a-zA-Z]+/

        and: "the generated string has length 5"
        assert string.size() == 5

        where: "the string is generated by Strings.alphanumeric(5)"
        string << (1..1000).collect { Strings.alphanumeric(5) }
    }

    def "alphanumeric with range length"(def string) {
        expect: "the generated string is an alphanumeric string"
        assert string ==~ /[0-9a-zA-Z]+/

        and: "the generated string has length 4..8"
        assert string.size() in 4..8

        where: "the string is generated by Strings.alphanumeric(4..8)"
        string << (1..1000).collect { Strings.alphanumeric(4..8) }
    }
}
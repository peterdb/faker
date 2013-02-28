package faker.specs.internet

import spock.lang.Specification;
import faker.Internet;
import faker.specs.support.Localized;

@Localized
class UserName extends Specification {

    def "user name with no name"(def userName) {
        expect: "a random name is be generated, converted to lowercase, and glued with _ or ."
        assert userName ==~ /[a-z]+((_|\.)[a-z]+)?/

        where:
        userName << (1..100).collect { Internet.userName()}
    }

    def "user name with a name"(def userName) {
        expect: "the name is converted to lowercase"
        assert userName == "john"

        where:
        userName << (1..100).collect { Internet.userName("John") }
    }

    def  "user name with a full name"(def userName) {
        expect: "the name is converted to lowercase, and glued with _ or ."
        assert userName ==~ /(john(_|\.)doe|doe(_|\.)john)/

        where:
        userName << (1..100).collect { Internet.userName("John Doe") }
    }
}
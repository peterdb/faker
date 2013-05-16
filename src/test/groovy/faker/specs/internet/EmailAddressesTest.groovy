package faker.specs.internet;

import static org.junit.Assert.*
import spock.lang.Specification
import faker.Internet
import faker.specs.support.Localized

@Localized
class EmailAddressesTest extends Specification {

    def "email without parameters"(def email) {
        expect: "email addresses with a random name and random domain"
        email ==~ /.+@.+\.\w+/

        where:
        email << (1..100).collect { Internet.email() }
    }

    def "email with a name"(def email) {
        expect: "email addresses with given name and random domain"
        email ==~ /(john(_|.)doe|doe(_|.)john)@.+\.\w+/

        where:
        email << (1..100).collect { Internet.email("John Doe") }
    }

    def "free email without parameters"(def email) {
        expect: "a random name must be used, and one of the free email providers (gmail, hotmail or yahoo)"
        email ==~ /.+@(gmail|hotmail|yahoo)\.com/

        where:
        email << (1..100).collect { Internet.freeEmail() }
    }

    def "free email with a name"(def email) {
        expect: "the name must be used, and one of the free email providers (gmail, hotmail or yahoo)"
        email ==~ /(john(_|.)doe|doe(_|.)john)@(gmail|hotmail|yahoo)\.com/

        where:
        email << (1..100).collect { Internet.freeEmail("John Doe") }
    }


    def "safe email without parameters"(def email) {
        expect: "a random name must be used, and one of the free email providers (example.com, example.net, example.org)"
        email ==~ /.+@example.(com|net|org)/

        where:
        email << (1..100).collect { Internet.safeEmail() }
    }

    def "safe email with a name"(def email) {
        expect: "the name must be used, and one of the free email providers (example.com, example.net, example.org)"
        email ==~ /(john(_|.)doe|doe(_|.)john)@example.(com|net|org)/

        where:
        email << (1..100).collect { Internet.safeEmail("John Doe") }
    }
}

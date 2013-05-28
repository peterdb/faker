package faker.specs.name

import spock.lang.Specification
import faker.Internet
import faker.Name;
import faker.specs.support.Localized

@Localized
class NameTest extends Specification {

    def "name"(def name) {
        expect: "the output should be a name"
        assert name

        where:
        name << (1..1000).collect { Name.name() }
    }
}
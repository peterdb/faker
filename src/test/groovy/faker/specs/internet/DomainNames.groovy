package faker.specs.internet

import spock.lang.Specification;
import faker.Internet;
import faker.specs.support.Localized;

@Localized
class DomainNames extends Specification {

    def "domain name"(def domainName) {
        expect: "the output should be a valid domain name"
        assert domainName ==~ /\w+\.\w+/

        where:
        domainName << (1..100).collect { Internet.domainName() }
    }

    def "domain word"(def domainWord) {
        expect: "the output should be a valid domain word"
        assert domainWord ==~ /^\w+$/

        where:
        domainWord << (1..100).collect { Internet.domainWord() }
    }
    
    def "domain suffix"(def domainSuffix) {
        expect: "it is a valid domain suffix"
        assert domainSuffix ==~ /^\w+(\.\w+)?/

        where:
        domainSuffix << (1..100).collect {Internet.domainSuffix()}
    }
}
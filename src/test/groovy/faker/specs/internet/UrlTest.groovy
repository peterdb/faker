package faker.specs.internet

import spock.lang.Specification
import faker.Internet
import faker.specs.support.Localized

@Localized
class UrlTest extends Specification {

    def "url"(def url) {
        expect: "the output must be an url with a random domain name, and a random slug attached"
        assert url ==~ /http:\/\/\w+\.\w+\/\w+(-|_|\.)\w+/

        where: "I call Internet.url()"
        url << (1..100).collect { Internet.url() }
    }

    def "slug with no words or glue"(def slug) {
        expect: "the output must consist of 2 random words, separated by _, . or -"
        assert slug ==~ /^[a-z]+(_|\.|\-)[a-z]+$/

        where: "I call Internet.slug()"
        slug << (1..100).collect { Internet.slug() }
    }

    def "slug with custom glue"(def slug) {
        expect: "the output must consist of 2 random words, separated by given glue"
        assert slug ==~ /^[a-z]+\+[a-z]+$/

        where: "I call Internet.slug(null, '+')"
        slug << (1..100).collect { Internet.slug(null, '+') }
    }

    def "slug with words, no glue"(def slug) {
        expect: "the ouput must consist of the given words, glued with _, . or -, and lowercase"
        assert slug ==~ /^foo(_|\.|\-)bar(_|\.|\-)baz$/

        where: "I call Internet.slug('Foo bAr baZ')"
        slug << (1..100).collect { Internet.slug("Foo bAr baZ") }
    }

    def "slug with words and glue"(def slug) {
        expect: "the output must consist of the given words, glued with given glue, and lowercase"
        assert slug ==~ /^foo\+bar\+baz$/

        where: "I call Internet.slug('Foo bAr baZ', '+')"
        slug << (1..100).collect { Internet.slug("Foo bAr baZ", '+') }
    }
}

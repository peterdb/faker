package strings

import javax.security.auth.login.FailedLoginException;

import faker.Strings;

narrative 'alphabetic strings', {
    as_a 'developer'
    i_want 'to be able to generate alphabetic strings'
}

scenario "alphabetic with fixed length", {
    when "I call Strings.alphabetic(5)", {
        strings = (1..100).collect { Strings.alphabetic(5) }
    }

    then "the generated string is an alphabetic string", {
        strings.each { string ->
            assert string ==~ /[a-zA-Z]+/
        }
    }

    then "the generated string has length 5", {
        strings.each { string ->
            string.size().shouldBe 5
        }
    }
}

scenario "alphabetic with range length", {
    when "I call Strings.alphabetic(4..8)", {
        strings = (1..100).collect { Strings.alphabetic(4..8) }
    }

    then "the generated string is an alphabetic string", {
        strings.each { string ->
            assert string ==~ /[a-zA-Z]+/
        }
    }

    then "the generated string has length 4..8", {
        strings.each { string ->
            assert string.size() in 4..8
        }
    }
}
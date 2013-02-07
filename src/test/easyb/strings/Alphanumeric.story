package strings

import javax.security.auth.login.FailedLoginException;

import faker.Strings;

narrative 'alphanumeric strings', {
    as_a 'developer'
    i_want 'to be able to generate alphanumeric strings'
}

scenario "alphanumeric with fixed length", {
    when "I call Strings.alphanumeric(5)", {
        strings = (1..100).collect { Strings.alphanumeric(5) }
    }

    then "the generated string is an alphanumeric string", {
        strings.each { string ->
            assert string ==~ /[0-9a-zA-Z]+/
        }
    }

    then "the generated string has length 5", {
        strings.each { string ->
            string.size().shouldBe 5
        }
    }
}

scenario "alphanumeric with range length", {
    when "I call Strings.alphanumeric(4..8)", {
        strings = (1..100).collect { Strings.alphanumeric(4..8) }
    }

    then "the generated string is an alphanumeric string", {
        strings.each { string ->
            assert string ==~ /[0-9a-zA-Z]+/
        }
    }

    then "the generated string has length 4..8", {
        strings.each { string ->
            assert string.size() in 4..8
        }
    }
}
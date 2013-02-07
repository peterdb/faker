package strings

import javax.security.auth.login.FailedLoginException;

import faker.Strings;

narrative 'numeric strings', {
    as_a 'developer'
    i_want 'to be able to generate numeric strings'
}

scenario "numeric with fixed length", {
    when "I call Strings.numeric(5)", {
        numbers = (1..100).collect { Strings.numeric(5) }
    }

    then "the generated number is a valid number", {
        numbers.each { number ->
            Integer.parseInt(number)
        }
    }

    then "the generated number has length 5", {
        numbers.each { number ->
            number.size().shouldBe 5
        }
    }
}

scenario "numeric with range length", {
    when "I call Strings.numeric(4..8)", {
        numbers = (1..100).collect { Strings.numeric(4..8) }
    }

    then "the generated number is a valid number", {
        numbers.each { number ->
            Integer.parseInt(number)
        }
    }

    then "the generated number has length 4..8", {
        numbers.each { number ->
            assert number.size() in 4..8
        }
    }
}
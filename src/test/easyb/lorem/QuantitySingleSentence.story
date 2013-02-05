package lorem

import faker.Faker;
import faker.Lorem;


shared_stories "../shared/stories.shared"

narrative 'single sentence', {
    as_a 'developer'
    i_want 'to be able to generate a single sentence'
}

scenario "sentence, no number of words", {
    when "I call Lorem.sentence()", {
        sentences = (1..100).collect { Lorem.sentence() }
    }
    
    then "the sentences should have 4-10 words", {
        sentences.each { sentence ->
            assert sentence.split(' ').size() in 4..10
        }
    }
}

scenario "sentence, exact number of words", {
    when "I call Lorem.sentence(5)", {
        sentences = (1..100).collect { Lorem.sentence(5) }
    }
    
    then "the sentences should exactly 5 words", {
        sentences.each { sentence ->
            assert sentence.split(' ').size() == 5
        }
    }
}

scenario "sentence, range number of words", {
    when "I call Lorem.sentence(3..8)", {
        sentences = (1..100).collect { Lorem.sentence(3..8) }
    }
    
    then "the sentences should have 3..8 words", {
        sentences.each { sentence ->
            assert sentence.split(' ').size() in 3..8
        }
    }
}

scenario "sentence, list number of words", {
    when "I call Lorem.sentence([3, 5, 7])", {
        sentences = (1..100).collect { Lorem.sentence([3, 5, 7]) }
    }
    
    then "the sentences should have 3, 5 or 7 words", {
        sentences.each { sentence ->
            assert sentence.split(' ').size() in [3, 5, 7]
        }
    }
}
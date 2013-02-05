package lorem

import faker.Faker;
import faker.Lorem;


shared_stories "../shared/stories.shared"

narrative 'multiple words', {
    as_a 'developer'
    i_want 'to be able to generate a list of words'
}

scenario "words, no number of words", {
    when "I call Lorem.words()", {
        wordLists = (1..100).collect { Lorem.words() }
    }
    
    then "the resulting list should be 3 words", {
        wordLists.each { words ->
            assert words.size() == 3
        }
    }
}

scenario "words, exact number of words", {
    when "I call Lorem.words(5)", {
        wordLists = (1..100).collect { Lorem.words(5) }
    }
    
    then "the resulting list should be 5 words", {
        wordLists.each { words ->
            assert words.size() == 5
        }
    }
}

scenario "words, range number of words", {
    when "I call Lorem.words(3..6)", {
        wordLists = (1..100).collect { Lorem.words(3..6) }
    }
    
    then "the resulting list should be 3..6 words", {
        wordLists.each { words ->
            assert words.size() in 3..6
        }
    }
}

scenario "words, list number of words", {
    when "I call Lorem.words([1, 4])", {
        wordLists = (1..100).collect { Lorem.words([1, 4]) }
    }
    
    then "the resulting list should be 1 or 4 words", {
        wordLists.each { words ->
            assert words.size() in [1, 4]
        }
    }
}


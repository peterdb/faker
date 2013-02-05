package lorem

import faker.Faker;
import faker.Lorem;


shared_stories "../shared/stories.shared"

narrative 'single word', {
    as_a 'developer'
    i_want 'to be able to generate a single word'
}

scenario "word, no number of sentences", {
    when "I call Lorem.word()", {
        words = (1..100).collect { Lorem.word() }
    }
    
    then "the words should be valid words", {
        words.each { word ->
            assert word ==~ /\w+/
        }
    }
}

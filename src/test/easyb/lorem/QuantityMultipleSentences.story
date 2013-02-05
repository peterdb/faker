package lorem

import faker.Faker;
import faker.Lorem;


shared_stories "../shared/stories.shared"

narrative 'multiple sentences', {
    as_a 'developer'
    i_want 'to be able to generate a list of sentences'
}

scenario "sentences, no number of sentences", {
    when "I call Lorem.sentences()", {
        sentenceLists = (1..100).collect { Lorem.sentences() }
    }
    
    then "the resulting list should be 3 sentences", {
        sentenceLists.each { sentences ->
            assert sentences.size() == 3
        }
    }
}

scenario "sentences, exact number of sentences", {
    when "I call Lorem.sentences(5)", {
        sentenceLists = (1..100).collect { Lorem.sentences(5) }
    }
    
    then "the resulting list should be 5 sentences", {
        sentenceLists.each { sentences ->
            assert sentences.size() == 5
        }
    }
}

scenario "sentences, range number of sentences", {
    when "I call Lorem.sentences(3..6)", {
        sentenceLists = (1..100).collect { Lorem.sentences(3..6) }
    }
    
    then "the resulting list should be 3..6 sentences", {
        sentenceLists.each { sentences ->
            assert sentences.size() in 3..6
        }
    }
}

scenario "sentences, list number of sentences", {
    when "I call Lorem.sentences([1, 4])", {
        sentenceLists = (1..100).collect { Lorem.sentences([1, 4]) }
    }
    
    then "the resulting list should be 1 or 4 sentences", {
        sentenceLists.each { sentences ->
            assert sentences.size() in [1, 4]
        }
    }
}


package lorem

import faker.Faker;
import faker.Lorem;


shared_stories "../shared/stories.shared"

narrative 'multiple paragraphs', {
    as_a 'developer'
    i_want 'to be able to generate a list of paragraphs'
}

scenario "paragraphs, no number of paragraphs", {
    when "I call Lorem.paragraphs()", {
        paragraphLists = (1..100).collect { Lorem.paragraphs() }
    }
    
    then "the resulting list should be 3 paragraphs", {
        paragraphLists.each { paragraphs ->
            assert paragraphs.size() == 3
        }
    }
}

scenario "paragraphs, exact number of paragraphs", {
    when "I call Lorem.paragraphs(5)", {
        paragraphLists = (1..100).collect { Lorem.paragraphs(5) }
    }
    
    then "the resulting list should be 5 paragraphs", {
        paragraphLists.each { paragraphs ->
            assert paragraphs.size() == 5
        }
    }
}

scenario "paragraphs, range number of paragraphs", {
    when "I call Lorem.paragraphs(3..6)", {
        paragraphLists = (1..100).collect { Lorem.paragraphs(3..6) }
    }
    
    then "the resulting list should be 3..6 paragraphs", {
        paragraphLists.each { paragraphs ->
            assert paragraphs.size() in 3..6
        }
    }
}

scenario "paragraphs, list number of paragraphs", {
    when "I call Lorem.paragraphs([1, 4])", {
        paragraphLists = (1..100).collect { Lorem.paragraphs([1, 4]) }
    }
    
    then "the resulting list should be 1 or 4 paragraphs", {
        paragraphLists.each { paragraphs ->
            assert paragraphs.size() in [1, 4]
        }
    }
}


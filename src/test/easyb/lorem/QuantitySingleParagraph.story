package lorem

import faker.Faker;
import faker.Lorem;


shared_stories "../shared/stories.shared"

narrative 'single paragraph', {
    as_a 'developer'
    i_want 'to be able to generate a single paragraph'
}

scenario "paragraph, no number of sentences", {
    when "I call Lorem.paragraph()", {
        paragraphs = (1..100).collect { Lorem.paragraph() }
    }
    
    then "the paragraphs should have 3-6 sentences", {
        paragraphs.each { p ->
            assert p.split('\\. ').size() in 3..6
        }
    }
}

scenario "paragraph, exact number of paragraphs", {
    when "I call Lorem.paragraph(5)", {
        paragraphs = (1..100).collect { Lorem.paragraph(5) }
    }
    
    then "the paragraphs should have exactly 5 sentences", {
        paragraphs.each { p ->
            assert p.split('\\. ').size() == 5
        }
    }
}

scenario "paragraph, range number of paragraphs", {
    when "I call Lorem.paragraph(1..3)", {
        paragraphs = (1..100).collect { Lorem.paragraph(1..3) }
    }
    
    then "the paragraphs should have 1..3 sentences", {
        paragraphs.each { p ->
            assert p.split('\\. ').size() in 1..3
        }
    }
}

scenario "paragraph, list number of paragraphs", {
    when "I call Lorem.paragraph([1, 4])", {
        paragraphs = (1..100).collect { Lorem.paragraph([1, 4]) }
    }
    
    then "the paragraphs should have 1 or 4 sentences", {
        paragraphs.each { p ->
            assert p.split('\\. ').size() in [1, 4]
        }
    }
}
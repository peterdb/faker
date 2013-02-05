package lorem

import faker.Faker;
import faker.Lorem;


shared_stories "../shared/stories.shared"

narrative 'paragraphs', {
    as_a 'developer'
    i_want 'to be able to generate paragraphs'
}

before "fetch word lists", {
    given "word lists", {
        standardWordList = Faker.bundleHolder.bundle.getObject("faker.lorem.words")
        completeWordList = standardWordList + Faker.bundleHolder.bundle.getObject("faker.lorem.supplemental")
    }
}

scenario "paragraph content, from standard word list", {
    when "I call Lorem.paragraph()", {
        paragraphs = (1..100).collect { Lorem.paragraph() }
    }
    
    then "the paragraph must consist of words from the standard word list", {
        paragraphs.each { paragraph ->
            paragraph.scan(~/\w+/).each { word ->
                assert word.toLowerCase() in standardWordList
            }
        }
    }
}

scenario "paragraph content, from complete word list",{
    when "I call Lorem.paragraph()", {
        paragraphs = (1..100).collect { Lorem.paragraph(true) }
    }
    
    then "the paragraph must consist of words from the standard word list", {
        paragraphs.each { paragraph ->
            paragraph.scan(~/\w+/).each { word ->
                assert word.toLowerCase() in completeWordList
            }
        }
    }
}

scenario "paragraph structure", {
    when "I call Lorem.paragraph()", {
        paragraphs = (1..100).collect { Lorem.paragraph() }
    }
    
    then "the paragraph should start with an uppercase character", {
        paragraphs.each { paragraph ->
            assert (paragraph[0] as Character).isUpperCase()
        }
    }
    
    and "the paragraph should end with a period", {
        paragraphs.each { paragraph ->
            assert paragraph.endsWith(".")
        }
    }
}
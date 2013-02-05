package lorem

import faker.Faker;
import faker.Lorem;


shared_stories "../shared/stories.shared"

narrative 'sentences', {
    as_a 'developer'
    i_want 'to be able to generate sentences'
}

before "fetch word lists", {
    given "word lists", {
        standardWordList = Faker.bundleHolder.bundle.getObject("faker.lorem.words")
        completeWordList = standardWordList + Faker.bundleHolder.bundle.getObject("faker.lorem.supplemental")
    }
}

scenario "sentence content, from standard word list",{
    when "I call Lorem.sentence()", {
        sentences = (1..100).collect { Lorem.sentence() }
    }
    
    then "the sentence must consist of words from the standard word list", {
        sentences.each { sentence ->
            sentence.chop().split(' ').each { word ->
                assert word.toLowerCase() in standardWordList
            }
        }
    }
}

scenario "sentence content, from complete word list",{
    when "I call Lorem.sentence()", {
        sentences = (1..100).collect { Lorem.sentence(true) }
    }
    
    then "the sentence must consist of words from the standard word list", {
        sentences.each { sentence ->
            sentence.scan(~/\w+/).each { word ->
                assert word.toLowerCase() in completeWordList
            }
        }
    }
}

scenario "sentence structure", {
    when "I call Lorem.sentence()", {
        sentences = (1..100).collect { Lorem.sentence() }
    }
    
    then "the sentence should start with an uppercase character", {
        sentences.each { sentence ->
            assert (sentence[0] as Character).isUpperCase()
        }
    }
    
    and "the sentence should end with a period", {
        sentences.each { sentence ->
            assert sentence.endsWith(".")
        }
    }
}
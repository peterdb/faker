package lorem

import faker.Faker;
import faker.Lorem;


shared_stories "../shared/stories.shared"

narrative 'words', {
    as_a 'developer'
    i_want 'to be able to generate words'
}

before "fetch word lists", {
    given "word lists", {
        standardWordList = Faker.bundleHolder.bundle.getObject("faker.lorem.words")
        completeWordList = standardWordList + Faker.bundleHolder.bundle.getObject("faker.lorem.supplemental")
    }
}

scenario "words should come from standard word list",{
    when "I call Lorem.words(1000)", {
        words = Lorem.words(1000)
    }
    
    then "all the words should be from the standard word list", {
        words.each { w ->
            assert w in standardWordList
        }
    }
}

scenario "words requested from the supplemental list",{
    when "I call Lorem.words(1000, true)", {
        words = Lorem.words(1000, true)
    }
    
    then "all the words should be from the standard + the supplemental list", {
        words.each { w ->
            assert w in completeWordList
        }
    }
}

scenario "word, should return random word from standard wordlist",{
    when "I call Lorem.word()", {
        words = (1..1000).collect { Lorem.word() }
    }
    
    then "the word comes from the standard wordlist", {
        words.each { w ->
            assert w in standardWordList
        }
    }
}
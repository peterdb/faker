package lorem

import faker.Lorem;


shared_stories "../shared/stories.shared"

narrative 'character sequences', {
    as_a 'developer'
    i_want 'to be able to generate character sequences'
}

scenario "characters, no parameter", {
    when "I call Lorem.characters()", {
        chars = Lorem.characters()
    }
    
    then "the output is 255 characters", {
        chars.size().shouldBe 255
    }
    
    and "the output should be alphanumerical", {
        chars.each { c ->
            assert c in (('a'..'z') + ('A'..'Z') + ('0'..'9'))
        }
    }
}

scenario "characters, with count", {
    when "I call Lorem.characters(500)", {
        chars = Lorem.characters(500)
    }
    
    then "the output is 500 characters", {
        chars.size().shouldBe 500
    }
    
    and "the output should be alphanumerical", {
        chars.each { c ->
            assert c in (('a'..'z') + ('A'..'Z') + ('0'..'9'))
        }
    }
}

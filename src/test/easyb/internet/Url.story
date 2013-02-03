package internet

import faker.Internet;

shared_stories "../shared/stories.shared"


narrative 'url', {
	as_a 'developer'
	i_want 'to be able to generate fake urls'
}

scenario "url", {
    when "I call Internet.url()", {
        url = Internet.url()
    }
    
    then "the output must be an url with a random domain name, and a random slug attached", {
        assert url ==~ /http:\/\/\w+\.\w+\/\w+(-|_\.)\w+/
    }
}

scenario "slug with no words or glue",{
	when "I call Internet.slug()",{
        slug = Internet.slug()
    }
    
    then "the output must consist of 2 random words, separated by _, . or -", {
        assert slug ==~ /^[a-z]+(_|\.|\-)[a-z]+$/
    }
}

scenario "slug with custom glue",{
	when "I call Internet.slug(null, '+')",{
        slug = Internet.slug(null, '+')
    }
    
    then "the output must consist of 2 random words, separated by given glue", {
        assert slug ==~ /^[a-z]+\+[a-z]+$/
    }
}

scenario "slug with words, no glue",{
	when "I call Internet.slug('Foo bAr baZ')",{
        slug = Internet.slug("Foo bAr baZ")
    }
    
    then "the ouput must consist of the given words, glued with _, . or -, and lowercase", {
        assert slug ==~ /^foo(_|\.|\-)bar(_|\.|\-)baz$/
    }
}

scenario "slug with words and glue",{
    when "I call Internet.slug('Foo bAr baZ', '+')",{
        slug = Internet.slug("Foo bAr baZ", '+')
    }
    
    then "the output must consist of the given words, glued with given glue, and lowercase", {
        assert slug ==~ /^foo\+bar\+baz$/
    }
}

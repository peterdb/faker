package internet

import faker.Internet;

description "A slug is the part of a URL which identifies a page using human-readable keywords"

scenario "slug with no words or glue",{
	when "a slug is created with no parameters",{
		slug = Internet.slug()
	}
	
	then "2 random words must be generated, separated by _, . or -", {
		assert slug ==~ /^[a-z]+(_|\.|\-)[a-z]+$/
	}
}

scenario "slug with custom glue",{
	when "a slug is created with glue",{
		slug = Internet.slug(null, '+')
	}
	
	then "2 random words must be generated, separated by given glue", {
		assert slug ==~ /^[a-z]+\+[a-z]+$/
	}
}

scenario "slug with words, no glue",{
	when "a slug is created with words",{
		slug = Internet.slug("Foo bAr baZ")
	}
	
	then "the slug must consist of given words, glued with _, . or -, and lowercase", {
		assert slug ==~ /^foo(_|\.|\-)bar(_|\.|\-)baz$/
	}
}

scenario "slug with words and glue",{
	when "a slug is created with words and glue",{
		slug = Internet.slug("Foo bAr baZ", '+')
	}
	
	then "the slug must consist of given words, glued with given glue, and lowercase", {
		assert slug ==~ /^foo\+bar\+baz$/
	}
}
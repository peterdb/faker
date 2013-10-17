Faker [![Build Status](https://travis-ci.org/peterdb/faker.png)](https://travis-ci.org/peterdb/faker)
=====

This is a Groovy port of Ruby Faker library for generating fake data.

It can be used to create real-looking test data, to populate your database with more than one or two records while you're doing development.

As Faker is written in Groovy, it can also be called from "normal" Java code.

Usage
-----
```groovy
import faker.*

def name = Name.name() // Rosalyn Murazik
def text = Lorem.paragraph() // Corporis omnis omnis nulla. Dolore ut a dolore. ...
def email = Internet.email() // deborah@crist.org
```

Internationalization
----
Faker uses ResourceBundles to organize strings (like first names) and formats (postal codes), allowing you to get different results by switching locales.

For example: the following code fragment changes the locale to french.

```groovy
import faker.*

Faker.locale = Locale.FRENCH
```

Ruby Faker yml files
----
yml files from Ruby Faker can be used as is (as a matter of fact, the yml files included in this project are the Ruby Faker ones). Underscore-style names (e.g. first_name) are automatically converted to camelcase-style names (e.g. firstName) to make this work.

Build
----
Faker uses [Maven](http://maven.apache.org/download.html "Maven").

    mvn clean install

Contributing
----
If you'd like to contribute code or formats/data for another locale, fork the project at github, make your changes, then send a pull request.

This project uses the following technologies:
- [Groovy](http://groovy.codehaus.org/ "Groovy"): programming language
- [Maven](http://maven.apache.org/download.html "Maven"): build tool
- [Spock](https://code.google.com/p/spock/ "Spock"): testing
- [YAML](http://en.wikipedia.org/wiki/Yaml "YAML"): data format

If you make changes, please include a Spock test.

License
----
This code is free to use under the terms of the MIT license.

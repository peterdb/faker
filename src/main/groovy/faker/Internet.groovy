package faker

class Internet {
    private static Faker.Base delegate = new Faker.Base(Internet)
    
    private static IPv6_SPACE
    
    /**
     * Generate an email address with a random domain name.
     * @param name optional, used to generate the local part. If not given, a random user name will be generated using {@link #userName(String)}
     * @return the generated email address
     */
    public static String email(String name = null) {
        return [userName(name), domainName()].join('@')
    }
    
    public static String freeEmail(String name = null) {
        return [userName(name), fetch('internet.free_email') ].join('@')
    }
    
    public static String safeEmail(String name = null) {
        return [userName(name), 'example.'+ ["org", "com", "net"].sample()].join('@')
    }

    public static String userName(String name = null) {
        if(name) {
            return name.scan(~/\w+/).shuffle().join(['.', '_'].sample()).toLowerCase()
        }
        
        return fixUmlauts([
          { Name.firstName().replaceAll(~/\W/, '').toLowerCase() },
          { [Name.firstName(), Name.lastName()].collect { n -> n.replaceAll(~/\W/, '') }.join([".", "_"].sample()).toLowerCase() }
        ].sample().call())
    }
      
    public static String domainName() {
        return [fixUmlauts(domainWord()), domainSuffix() ].join('.')
    }
    
    private static String fixUmlauts(String s) {
        return s.replaceAll(~/[äöüß]/) { String match ->
            switch (match.toLowerCase()) {
                case "ä": return 'ae'
                case "ö": return 'oe'
                case "ü": return 'ue'
                case "ß": return 'ss'
            }
        }
    }
      
    public static String domainWord() {
        return Company.name().split(' ').first().replaceAll(~/\W/, '').toLowerCase()
    }
      
    public static String domainSuffix() {
        return delegate.fetch('internet.domain_suffix')
    }
      
    public static String IPv4Address() {
        def ary = (2..254)
        
        return [ary.sample(), ary.sample(), ary.sample(), ary.sample()].join('.')
    }

    public static String IPv6Address() {
        if(!IPv6_SPACE) {
            IPv6_SPACE = 0..65535
        }
        
        return (1..8).collect { IPv6_SPACE.sample() }.collect{ n -> Integer.toString(n, 16) }.join(":")
    }
      
    public static String url() {
        return "http://${domainName()}/${slug()}"
    }

    /**
     * A slug is the part of a URL which identifies a page using human-readable keywords
     * @return a slug
     */
    public static String slug(String words = null, String glue = null) {
        if(!glue) {
             glue = ["-", "_", "."].sample()
        }
        
        (words ?: Lorem.words(2).join(' ')).replaceAll(' ', glue).toLowerCase()
    }
}

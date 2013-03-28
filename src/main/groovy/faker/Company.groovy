package faker

import faker.internal.FakerSupport;


class Company {
    private static FakerSupport support = new FakerSupport(Company)
    
    public static String name() {
        support.getString('company.name')
    }

    public static String suffix() {
        support.getString('company.suffix')
    }

    /**
     * Generate a buzzword-laden catch phrase.
     * 
     * @return a catch phrase
     */
    public static String catchPhrase() {
        support.getObject('company.buzzwords').collect { list -> list.sample() }.join(' ')
    }

    /**
     * When a straight answer won't do, BS to the rescue!
     * 
     * @return bs
     */
    public static String bs() {
        support.getObject('company.bs').collect { list -> list.sample() }.join(' ')
    }

}

package faker

import faker.internal.FakerSupport;


class Name {
    private static FakerSupport support = new FakerSupport(Name)

    public static String name() {
        return support['name.name']
    }
    
    public static String firstName() {
        return support['name.first_name']
    }

    public static String lastName() {
        return support['name.last_name']
    }

    public static String prefix() {
        return support['name.prefix']
    }

    public static String suffix() {
        return support['name.suffix']
    }

    public static String title() {
        return support['name.title.descriptor'] + ' ' + support['name.title.level'] + ' ' + support['name.title.job']
    }
}

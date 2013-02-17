package faker

import faker.internal.FakerSupport;


class Name {
    private static FakerSupport support = new FakerSupport(Name)

    public static String name() {
        return support.parse('name.name')
    }
    
    public static String firstName() {
        return support.fetch('name.first_name')
    }

    public static String lastName() {
        return support.fetch('name.last_name')
    }

    public static String prefix() {
        return support.fetch('name.prefix')
    }

    public static String suffix() {
        return support.fetch('name.suffix')
    }

    public static String title() {
        return support.fetch('name.title.descriptor') + ' ' + support.fetch('name.title.level') + ' ' + support.fetch('name.title.job')
    }
}

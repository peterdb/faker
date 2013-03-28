package faker

import faker.internal.FakerSupport;


class Name {
    private static FakerSupport support = new FakerSupport(Name)

    public static String name() {
        return support.getString('name.name')
    }
    
    public static String firstName() {
        return support.getString('name.first_name')
    }

    public static String lastName() {
        return support.getString('name.last_name')
    }

    public static String prefix() {
        return support.getString('name.prefix')
    }

    public static String suffix() {
        return support.getString('name.suffix')
    }

    public static String title() {
        return support.getString('name.title.descriptor') + ' ' + support.getString('name.title.level') + ' ' + support.getString('name.title.job')
    }
}

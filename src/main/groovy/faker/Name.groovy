package faker


class Name {
    private static Faker.Base delegate = new Faker.Base(Name)

    public static String name() {
        return delegate.parse('name.name')
    }
    
    public static String firstName() {
        return delegate.fetch('name.first_name')
    }

    public static String lastName() {
        return delegate.fetch('name.last_name')
    }

    public static String prefix() {
        return delegate.fetch('name.prefix')
    }

    public static String suffix() {
        return delegate.fetch('name.suffix')
    }

    public static String title() {
        return delegate.fetch('name.title.descriptor') + ' ' + delegate.fetch('name.title.level') + ' ' + delegate.fetch('name.title.job')
    }
}

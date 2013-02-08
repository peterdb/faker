package faker.def

class I18nDefinitions implements Definitions {

    private ResourceBundle bundle
    
    @Override
    public Object getAt(String key) {
        return bundle.getObject(key)
    }

}

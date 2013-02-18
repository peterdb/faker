package faker.internal

class Bundles {
    // either a ResourceBundle, a Map or a something that responds to getAt
    @Delegate
    private final List bundles = []

    def Object getAt(String key) {
        // LIFO
        bundles.reverse().findResult { bundle ->
            return find(bundle, key)
        }
    }

    private static Object find(def bundle, String key) {
        def result = bundle

        key.split("\\.").each { it ->
            result = result?.getAt(it)
        }
        
        return result
    }
}

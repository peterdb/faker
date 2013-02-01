package faker.i18n

import java.util.Map;
import java.util.ResourceBundle;

/**
 * {@link ResourceBundle} implementation that's backed by a {@link Map}.
 * 
 * @author peter
 */
class MapBasedResourceBundle extends ResourceBundle {

    final Map<String, Object> map

    public MapBasedResourceBundle(Map<String, Object> map) {
        this.map = map
    }

    public Enumeration<String> getKeys() {
        // not supported
        throw new UnsupportedOperationException()
    }

    protected Object handleGetObject(String key) {
        def tmp = map
        
        key.split("\\.").each { it ->
            tmp = tmp?.get(it)
        }

        return tmp
    }
}

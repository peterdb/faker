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
        assert map != null, "map cannot be null"
        
        this.map = map
    }

    public Enumeration<String> getKeys() {
        return map.keySet().iterator() as Enumeration
    }

    protected Object handleGetObject(String key) {
        def result = map

        key.split("\\.").each { it ->
            result = result?.getAt(it)
        }
        
        return result
    }
}

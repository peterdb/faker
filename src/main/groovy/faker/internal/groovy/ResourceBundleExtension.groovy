package faker.internal.groovy

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Category(ResourceBundle)
class ResourceBundleExtension {
    Object getAt(String key) {
        return getObject(key)
    }
}

package faker.i18n

import java.util.Locale;
import java.util.ResourceBundle;

import org.yaml.snakeyaml.Yaml


class YamlResourceBundleControl extends ResourceBundle.Control {
	@Override
	public ResourceBundle newBundle(String baseName, Locale locale,
			String format, ClassLoader loader, boolean reload)
			throws IllegalAccessException, InstantiationException,
	IOException {
		String bundle = toBundleName(baseName, locale);
		String resource = toResourceName(bundle, format);
		
		def input = loader.getResourceAsStream(resource)
		if(input != null) {
			def map = loadMap(input)
			def localeKey = toTag(locale)

			return new MapBasedResourceBundle(map[localeKey])
		}

		return null
	}

	private Map loadMap(InputStream input) {
		return new Yaml().load(input.getText("utf-8"))
	}
	
	private String toTag(Locale locale) {
		if(locale == Locale.ROOT) {
			return "en"
		}
		
		return locale.toLanguageTag().replaceAll("_", "-").toLowerCase()
	}

	@Override
	public String toBundleName(String baseName, Locale locale) {
		return baseName + "." + toTag(locale)
	}

	@Override
	public List<String> getFormats(String baseName) {
		return ["yml"]
	}
}

package faker.specs.support

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.SpecInfo

class LocalizedExtension extends AbstractAnnotationDrivenExtension<Localized> {
    def locale
    def localizedFeatures = []

    void visitSpecAnnotation(Localized annotation, SpecInfo spec) {
        locale = toLocale(annotation.value())
    }

    void visitSpec(SpecInfo spec) {
        spec.addListener(new LocalizedRunListener(locale))
    }
	
	static Locale toLocale(String s) {
		return new Locale(*(s.split("_")))
	}
}
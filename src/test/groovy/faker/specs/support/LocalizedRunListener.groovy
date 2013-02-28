package faker.specs.support

import org.junit.runner.Description;
import org.spockframework.runtime.AbstractRunListener
import org.spockframework.runtime.model.FeatureInfo
import org.spockframework.runtime.model.SpecInfo

class LocalizedRunListener extends AbstractRunListener {

    def originalLocale
    def locale
    
    LocalizedRunListener(locale) {
        assert locale
        
        this.locale = locale
    }

    void beforeSpec(SpecInfo spec) {
        originalLocale = Locale.default
        Locale.default = locale
    }

    void afterSpec(SpecInfo spec) {
        Locale.default = originalLocale
    }
}
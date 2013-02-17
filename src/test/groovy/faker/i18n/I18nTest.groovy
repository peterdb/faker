package faker.i18n;

import static org.junit.Assert.*;

import org.junit.Test;

import faker.internal.groovy.I18nMethods;

class I18nTest {

    @Test
    public void withLocale_withClosureSuccess_shouldSetLocaleInForClosureAndResetLocaleWhenDone() {
        Locale nl_BE = new Locale("nl", "BE")
        Locale.setDefault(nl_BE)
        
        assert nl_BE == Locale.getDefault(), "sanity check fails"
        
        def result = I18nMethods.localized(Locale.ENGLISH) {
            assert Locale.ENGLISH == Locale.getDefault(), "locale not changed for closure"
            
            return "finished"
        }
        
        assert result == "finished", "closure was not run"
        
        assert nl_BE == Locale.getDefault(), "locale not reset to original locale"
    }
    
    @Test
    public void withLocale_withClosureFail_shouldSetLocaleInForClosureAndResetLocaleWhenDone() {
        Locale nl_BE = new Locale("nl", "BE")
        Locale.setDefault(nl_BE)
        
        assert nl_BE == Locale.getDefault(), "sanity check fails"
        
        try {
            I18nMethods.localized(Locale.ENGLISH) {
                assert Locale.ENGLISH == Locale.getDefault(), "locale not changed for closure"
                
                throw new RuntimeException("closure fail")
            }
        } catch (RuntimeException e) {
            assert "closure fail" == e.message
        }
        
        assert nl_BE == Locale.getDefault(), "locale not reset to original locale"
    }
    
}

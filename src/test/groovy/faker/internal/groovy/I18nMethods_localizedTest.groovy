package faker.internal.groovy;

import static org.junit.Assert.*;

import org.junit.Test;

import faker.internal.groovy.I18nMethods;

class I18nMethods_localizedTest {

    @Test
    public void localized_withClosureSuccess_shouldSetLocaleInForClosureAndResetLocaleWhenDone() {
        use(I18nMethods) {
            Locale nl_BE = new Locale("nl", "BE")
            Locale.default = nl_BE
            
            assert nl_BE == Locale.default, "sanity check fails"
            
            def result = localized(Locale.ENGLISH) {
                assert Locale.ENGLISH == Locale.default, "locale not changed for closure"
                
                return "finished"
            }
            
            assert result == "finished", "closure was not run"
            
            assert nl_BE == Locale.default, "locale not reset to original locale"
        }
    }
    
    @Test
    public void localized_withClosureFail_shouldSetLocaleInForClosureAndResetLocaleWhenDone() {
        use(I18nMethods) {
            Locale nl_BE = new Locale("nl", "BE")
            Locale.default = nl_BE
            
            assert nl_BE == Locale.default, "sanity check fails"
            
            try {
                localized(Locale.ENGLISH) {
                    assert Locale.ENGLISH == Locale.default, "locale not changed for closure"
                            
                            throw new RuntimeException("closure fail")
                }
            } catch (RuntimeException e) {
                assert "closure fail" == e.message
            }
            
            assert nl_BE == Locale.default, "locale not reset to original locale"
        }
    }
    
}

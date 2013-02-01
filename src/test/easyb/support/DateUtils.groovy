package support

import java.text.DateFormat
import java.text.SimpleDateFormat

import org.codehaus.groovy.runtime.DefaultGroovyMethods

class DateUtils {

    static <T> T asType(String self, Class<T> type) {
        if(Date == type) {
            return new Date().parse("dd/MM/yyyy", self)
        }
        
        DefaultGroovyMethods.asType(self, type)
    }
    
    static <T> T asType(List self, Class<T> type) {
        if(Date == type && self.size() == 3) {
            Calendar cal = Calendar.getInstance()
            cal.clear()
            cal.set(self[0], self[1] - 1, self[2])
            
            return cal.getTime()
        }
        
        DefaultGroovyMethods.asType(self, type)
    }
    
}

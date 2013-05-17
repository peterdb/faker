package faker.internal.groovy

import org.codehaus.groovy.runtime.DefaultGroovyMethods

public class ListExtension {
    private static final Random rnd = new Random()
    
    /**
     * Choose a random element or n random elements from the array.
     * 
     * @param self the list we will sample
     * @return a sample, or <code>null</code> if the list is empty
     */
    public static Object sample(List self, Random random = rnd) {
        self ? self[random.nextInt(self.size())] : null
    }
    
    /**
     * Choose n random elements from a list. No duplicate elements will be selected,
     * unless they appear in the original list.
     * 
     * @param self the list we will sample
     * @param n the number of elements
     * @return a list with n random elements, or an empty list if the source list was empty
     */
    public static List sample(List self, int n, Random random = rnd) {
        assert n, "n must be > 0"
        
        if(self.empty) {
            return []
        }
        
        if(n > self.size()) {
            n = self.size()
        }
        
        def indexes = new ArrayList(0..self.size()-1)
        
        return (1..n).collect { 
            def index = indexes.sample(random)
            indexes.remove((Object)index)
            return self[index]
        }
    }
    
    public static List shuffle(List self) {
        def result = new ArrayList(self)
        
        Collections.shuffle(result, rnd)
        
        return result
    }
	
	public static <T> T asType(List self, Class<T> type) {
		if(Date == type && self.size() == 3) {
			Calendar cal = Calendar.instance
			cal.clear()
			cal.set(self[0], self[1] - 1 /* months are 0-based */, self[2])

			return cal.time
		}

		DefaultGroovyMethods.asType(self, type)
	}
}

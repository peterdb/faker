package faker.internal.groovy;

import static org.junit.Assert.*

import org.junit.Test

class ListExtension_sampleTest {

    @Test
    public void sample_withEmptyList_shouldReturnNull() {
        use(ListExtension) {
            assert [].sample() == null
        }
    }
    
    @Test
    public void sample_withEmptyListAndNumberOfItems_shouldReturnEmptyList() {
        use(ListExtension) {
            assert [].sample(5) == []
        }
    }
    
    @Test
    public void sample_allItems_shouldReturnAllItems() {
        use(ListExtension) {
            assert ((1..5).sample(5) as Set) == (1..5 as Set)
        }
    }
    
}

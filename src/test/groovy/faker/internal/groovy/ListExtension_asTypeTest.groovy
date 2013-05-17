package faker.internal.groovy;

import static org.junit.Assert.*

import org.junit.Test

class ListExtension_asTypeTest {

	@Test
	public void sample_withEmptyList_shouldReturnNull() {
		use(ListExtension) {
			def cal = Calendar.instance
			cal.time = [2000, 1, 1] as Date
			
			assert cal.get(Calendar.YEAR) == 2000
			assert cal.get(Calendar.MONTH) == 0
			assert cal.get(Calendar.DAY_OF_MONTH) == 1
		}
	}
}

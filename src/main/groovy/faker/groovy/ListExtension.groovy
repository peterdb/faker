package faker.groovy

@Category(List)
class ListExtension {
	private static final Random rnd = new Random()
	
	def sample() {
		this[rnd.nextInt(this.size())]
	}
	
	def sample(int count) {
		assert count, "count must be > 0"
		
		(1..count).collect { sample() }
	}
	
	def shuffle() {
		def result = new ArrayList(this)
		
		Collections.shuffle(result, rnd)
		
		return result
	}
}

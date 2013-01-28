package faker

class AddressTest {
	public static void main(String[] args) {
		10.times {
			println Address.latitude() + "," + Address.longitude()
		}
		
		25.times {
			println Address.streetAddress(new Random().nextBoolean())
		}
	}
}

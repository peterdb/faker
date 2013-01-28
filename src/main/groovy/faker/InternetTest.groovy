package faker

class InternetTest {
	public static void main(String[] args) {
		10.times {
			println Internet.IPv6Address()
		}
		10.times {
			println Internet.IPv4Address()
		}
	}
}

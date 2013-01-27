package faker

class TTT {
	public static void main(String[] args) {
		10.times {
			println Faker.Name.firstName
			println Faker.Name.title
		}
		
		println Eval.me("", Faker.Name.class, '${firstName}')
	}
}

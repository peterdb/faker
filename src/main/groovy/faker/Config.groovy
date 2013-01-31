package faker

class Config {
	def static locale = Locale.getDefault()
	
	public static void setLocale(Locale l) {
		this.locale = l
		
		Faker.reload()
	}
}

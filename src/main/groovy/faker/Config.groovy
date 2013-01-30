package faker

class Config {
	def locale = Locale.getDefault()
	
	public void setLocale(Locale l) {
		this.locale = l
		
		Faker.reload()
	}
}

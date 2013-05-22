package faker.cli

class CliRunner {
	public static void main(String[] ignored) {
		String[] args = ["address", "-f", "streetAddress", "-n", "10", "--locale", "nl_BE"]
		
		CLI.main(args);
	}
}

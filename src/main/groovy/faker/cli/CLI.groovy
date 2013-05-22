package faker.cli
import faker.Address
import faker.Name

class CLI {
	public static void main(String[] args) {
		String ttt = "ttt"

		CliBuilder cli = new CliBuilder(usage: ttt)
		cli.stopAtNonOption = false
		cli.with {
			f(required: true, args:1, argName:'faker_type', 'the faker data type')
			n(required: false, args:1, longOpt:'number', argName:'count', "the number of items to generate", type:Integer)
			l(required: false, args:1, longOpt:'locale', argName:'locale', 'the locale')
			//help("Prints this message")
		}

		def map = [
			"name": Name,
			"address": Address
		]

		OptionAccessor options = cli.parse(args)

		if(options) {
			println options.arguments()
			
			if(options.f) {
				def n = options.n ? options.n as int : 1

				Class receiver = map[options.arguments()[0]]
				String method = options.f

				n.times {
					println receiver."$method"()
				}
			}
		}
	}
}

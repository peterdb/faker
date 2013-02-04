package faker.cli
import faker.Address
import faker.Name

class CLI {
    public static void main(String[] args) {
        String ttt = "ttt"
        
        CliBuilder cli = new CliBuilder(usage: ttt)
        cli.stopAtNonOption = false
        cli.with {
            f(args:1, argName:'faker_type', 'the faker data type')
            help("Prints this message")
        }
        
        def map = [
            "name": Name,
            "address": Address
            ]
        
        OptionAccessor options = cli.parse(args)
        if(options.help) {
            println cli.usage()
        } else if(options.f) {
            Class receiver = map[options.arguments()[0]]
            String method = options.f
            
            println receiver."$method"()
        }
        
        println cli.usage()
    }
}

package faker.specs.internet

import spock.lang.Specification
import faker.Internet
import faker.specs.support.Localized

@Localized
class IPAddressesTest extends Specification {

    def ipv4(def ip) {
        expect:
        "the output should consist of 4 parts"
        assert ip.split('\\.').size() == 4
        "each part must be an octet"
        assert ip.split('\\.').collect { octet -> octet as int }.max() <= 255
        
        where:
        ip << (1..1000).collect { Internet.IPv4Address() }
    }

    def ipv6(def ip) {
        expect:
        "the output should consist of 8 parts"
        assert ip.split(':').size() == 8
        "each part must be a hex value"
        assert ip.split(':').collect { hex -> Integer.parseInt(hex, 16) }.max() <= 65535
        
        where:
        ip << (1..1000).collect { Internet.IPv6Address() }
    }
}
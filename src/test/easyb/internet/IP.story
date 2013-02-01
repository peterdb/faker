package internet

import faker.Internet;

scenario "ipv4",{
    given "1000 ipv4 addresses", {
        ips = (1..1000).collect { Internet.IPv4Address() }
    }
    
    then "they consist of 4 parts", {
        ips.each { ip ->
            ip.split('\\.').size().shouldBe 4
        }
    }
    
    then "and each part is an octet", {
        ips.each { ip ->
            assert ip.split('\\.').collect { octet -> octet as int }.max() <= 255
        }
    }
}

scenario "ipv6",{
    given "1000 ipv6 addresses", {
        ips = (1..1000).collect { Internet.IPv6Address() }
    }
    
    then "they exist of 8 parts", {
        ips.each { ip ->
            ip.split(":").size().shouldBe 8
        }
    }
    
    then "and each part is a valid hex", {
        ips.each { ip ->
            assert ip.split(':').collect { hex -> Integer.parseInt(hex, 16) }.max() <= 65535
        }
    }
}
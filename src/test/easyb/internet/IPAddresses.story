package internet

import faker.Internet;

description """
In order to generate fake data
As a developer
I want to be able to create fake ip addresses
"""

scenario "ipv4",{
    when "I call Internet.IPv4Address()", {
        ips = (1..1000).collect { Internet.IPv4Address() }
    }
    
    then "the output should consist of 4 parts", {
        ips.each { ip ->
            ip.split('\\.').size().shouldBe 4
        }
    }
    
    and "each part must be an octet", {
        ips.each { ip ->
            assert ip.split('\\.').collect { octet -> octet as int }.max() <= 255
        }
    }
}

scenario "ipv6",{
    given "I call Internet.IPv6Address()", {
        ips = (1..1000).collect { Internet.IPv6Address() }
    }
    
    then "the output should consist of 8 parts", {
        ips.each { ip ->
            ip.split(":").size().shouldBe 8
        }
    }
    
    and "each part must be a hex value", {
        ips.each { ip ->
            assert ip.split(':').collect { hex -> Integer.parseInt(hex, 16) }.max() <= 65535
        }
    }
}
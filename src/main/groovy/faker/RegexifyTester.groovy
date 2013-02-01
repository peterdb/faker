package faker

class RegexifyTester {
    public static void main(String[] args) {
//        println (~/\d/).toString()
//        println '\\d'.replaceAll(~/\\d/) { (0..9).sample() }
//        
//        println (~/\d/).toString().replaceAll(~/\\d/) { (0..9).sample() }
        
        println Name.regexify(~/\d\d\w/)
        10.times {
            def ttt = Name.regexify(~/^[A-PR-UWYZ0-9][A-HK-Y0-9][AEHMNPRTVXY0-9]?[ABEHMNPRVWXY0-9]? {1,2}[0-9][ABD-HJLN-UW-Z]{2}$/)
            assert ttt ==~/^[A-PR-UWYZ0-9][A-HK-Y0-9][AEHMNPRTVXY0-9]?[ABEHMNPRVWXY0-9]? {1,2}[0-9][ABD-HJLN-UW-Z]{2}$/
        }
    }
}

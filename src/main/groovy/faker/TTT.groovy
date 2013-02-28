package faker

class TTT {
    public static void main(String[] args) {
        def isWord = { assert it ==~ /[a-zA-Z]+/ }
        
        forAll(isWord, [ { Strings.alphabetic(10) } ])
    }
    
    public static void forAll(Closure check, List generators) {
        100.times { 
            def generator = generators.sample()
            
            def data = generator()
            
            def result = check(data)
            
            if(result instanceof Boolean) {
                assert result
            }
        }
    }
}

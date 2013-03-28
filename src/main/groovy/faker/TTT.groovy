package faker

class TTT {
    public static void main(String[] args) {
        Random rnd = new Random()
        
        100.times {
//            println (Numbers.d(-90, +90))
//            println (Numbers.d(1000))
            println Money.money(1200, 1500)
        }
    }
}

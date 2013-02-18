package faker

import groovy.time.TimeCategory;

class TTT {
    public static void main(String[] args) {
        use(TimeCategory) {
            def today = new Date()
            
            1000.times {
                Date day = Moment.birthday(25)
                
                def fake = new GregorianCalendar(today[Calendar.YEAR], day[Calendar.MONTH], day[Calendar.DAY_OF_MONTH])
                def age = now[Calendar.YEAR] - day[Calendar.YEAR] - (fake > today ? 1 : 0)
                
                if(age != 25) {
                    println "fuck! $age"
                }
            }
        }
    }
}

package faker

import groovy.time.TimeCategory;

class Dates {

    public static Date birthDay(int age = 18) {
        assert age >= 0
        
        use(TimeCategory) {
            return Moment.date((age + 1).years.ago + 1.day, age.years.ago)
        }
    }

    public static Date birthDay(List age) {
        assert age
        
        return birthDay(age.sample())
    }
    
    public static void main(String[] args) {
        10.times {
            println birthDay(0)
        }
    }

}

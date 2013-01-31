package faker

import groovy.time.Duration;
import groovy.time.TimeCategory;

class Moment {
	private static Faker.Base delegate = new Faker.Base(Moment)

	public static void main(String[] args) {
		use(TimeCategory) {
			println Moment.date()
			println Moment.date(2.years.ago)
			println Moment.date(1.year.ago, 1.year.from.now)
		}
	}

	// after is arbitrarily set to 1/1/1970 if not explicitly given
	// before is set to today if not set
	// both are inclusive
	public static Date date(Date after = null, Date before = null) {
		use(TimeCategory) {
			Calendar cal = Calendar.getInstance()

			if(!after) {
				cal.clear()
				cal.set(1970, 0, 1)
				after = cal.getTime()
			}

			if(!before) {
				before = new Date()
			}

			Duration duration = before - after
			
			return after + Moment.delegate.rnd.nextInt(duration.days + 1)
		}
	}

	//	// before and after are the number of seconds into the day and both are inclusive
	//	// ondate is set to today if not explicitly stated
	//	def self.time(ondate = nil, after = nil, before = nil)
	//	  ondate ||= Date.today
	//	  after ||= 0
	//	  before ||= (60 * 60 * 24)
	//	  interval = (before - after).to_i
	//	  Time.gm(ondate.year, ondate.month, ondate.day) + after + rand(interval + 1)
	//	end
}

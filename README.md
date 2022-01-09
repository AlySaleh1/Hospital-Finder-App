# Hospital-Finder-App

This Andriod app uses the current device location to identify the nearest Hospital with available space for patients requiring emergency intensive care.
All the main code I have written is under [this directory](https://github.com/AlySaleh1/Hospital-Finder-App/tree/master/app/src/main/java/com/example/hospitalfinder)

### Inspiration behind the project
Many hospitals located in developing nations face a common issue: not being able to accommodate patients requiring emergency intensive care due to running out of intensive care units. Intensive care units are defined as "areas of the hospital where seriously ill patients receive specialized care such as intensive monitoring and advanced life support" [source](https://www.doh.wa.gov/ForPublicHealthandHealthcareProviders/HealthcareProfessionsandFacilities/HealthcareAssociatedInfections/HAIReports/TypesofHospitalUnits#:~:text=Intensive%20care%20units%20(ICUs)%20are%20areas%20of%20the%20hospital%20where%20seriously%20ill%20patients%20receive%20specialized%20care%20such%20as%20intensive%20monitoring%20and%20advanced%20life%20support).

For example, Let's say John just witnessed one of his friends fall down the stairs. John puts his friend in his car and rushes to the nearest hospital. The hospital refuses to take in John's friend because they are already working at maximum capacity. John needs to go to a different hospital that has available hospital beds. John might run into the same problem multiple times before finding a hospital that would accept his friend. His friend needs urgent care, and time is important.
**The Hospital Finder app's purpose is to tell people in John's situation where the nearest hospital is with available capacity to save valuable time.**

### How I built the app
The app was built in Java, using Android Studio. It uses the device's location and multiple hospitals' locations to identify the distance between the user and the hospital.

### How does the app know the capacity levels at each hospital?
The app currently does not update the capacities of each hospital in real-time. This feature can be added in the future if there is a centralized database with all the hospitals' capacities in a city.

For the purposes of this demo, I added 4 Montreal to [Hospital.java](https://github.com/AlySaleh1/Hospital-Finder-App/blob/master/app/src/main/java/com/example/hospitalfinder/Hospital.java)

# randgenerate
Random number generator with hardware correction
We use signals from Android smartphone sensors to correct standard Java values random = ThreadLocalRandom.current().nextInt(1, 90)
We get values from one channel of the gravity sensor, select one digit of the number (two or three is better), turn this value into a number and make the same number of "empty" calls to hreadLocalRandom.current before reading the random number.
This is a simple example. It is necessary to use several different sensors (magnetometer, gyroscope), several channels, a light sensor, etc. You can also use some bits of the timer and binary operations.
The values of this generator will not be completely random. The quality of the generator can be improved if the choice of sensors for the number of "empty" cycles is also made according to the values from the random number generator.
The improved random number generator can be used as a server that broadcasts a stream of random numbers. A remote client, connecting to such a server, expects a predetermined sequence of numbers, after which it writes down a sequence of numbers that will be used as a cipher pad. The same is done on the server side. Such an algorithm allows you to work with the Vernam code and provide a commercial level of encryption reliability (at least).

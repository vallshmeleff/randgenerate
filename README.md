# randgenerate
Random number generator with hardware correction
We use signals from Android smartphone sensors to correct standard Java values random = ThreadLocalRandom.current().nextInt(1, 90)
We get values from one channel of the gravity sensor, select one digit of the number (two or three is better), turn this value into a number and make the same number of "empty" calls to hreadLocalRandom.current before reading the random number.
This is a simple example. It is necessary to use several different sensors (magnetometer, gyroscope), several channels, a light sensor, etc. You can also use some bits of the timer and binary operations.

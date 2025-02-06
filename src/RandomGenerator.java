import java.util.HashSet;
import java.util.Random;

public class RandomGenerator {
    private static String stateCodes[] = {"UP", "MH", "DL", "GJ", "UK", "RJ", "PB", "MP"};
    private static HashSet<String> hashSet = new HashSet<>();
    public static void main(String[] args) throws InterruptedException {
        //generate car number like DL 1234
        for(int i=0;i<10;i++) {
            //generating 10 random nos for testing
            System.out.println(randomGenerator());
            System.out.println(randomGeneratorWithoutUsingRandom());
            Thread.sleep(1);
        }
        System.out.println(2/3);
    }

    private static String randomGenerator(){
        Random random = new Random();
        while(true){
            String randomState = stateCodes[random.nextInt(stateCodes.length)];
            int randomCode = random.nextInt(9000) + 1000;
            String carNumber = randomState + " " + randomCode;

            if(!hashSet.contains(carNumber)) {
                hashSet.add(carNumber);
                return carNumber;
            }
        }
    }

    private static String randomGeneratorWithoutUsingRandom() {
        while(true){
            long seed = System.currentTimeMillis();
            seed ^= seed << 21;
            seed ^= seed >>> 28;
            seed ^= seed << 14;
            int randomNumber = (int) ((Math.abs(seed) % (10000 - 1000)) + 1000);
            int randomNumberForState = (int) ((Math.abs(seed) % stateCodes.length));

            String carNumber = stateCodes[randomNumberForState] + " " + randomNumber;
            if(!hashSet.contains(carNumber)) {
                hashSet.add(carNumber);
                return carNumber;
            }
        }
    }
}

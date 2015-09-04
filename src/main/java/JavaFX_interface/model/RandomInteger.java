package JavaFX_interface.model;

import encryption.hashing.HashingMD5;

import java.util.Random;

/**
 * Created by Вова on 23.08.2015.
 */
public  class RandomInteger {

    public static String main(String... aArgs){
        Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(999999999);
            System.out.println(randomInt);
            String x =String.valueOf(randomInt);

            HashingMD5 hashingMD5 = new HashingMD5();
            String num1 =hashingMD5.toHashCode(x);
            System.out.println(num1);
        return num1;

    }
}

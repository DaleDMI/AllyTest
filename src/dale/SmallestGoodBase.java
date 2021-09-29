package dale;

import java.math.BigInteger;

public class SmallestGoodBase {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please supply an integer between 3 and 10^18.");
            System.exit(-99);
        }
        BigInteger n = new BigInteger(args[0]);

        BigInteger THREE = BigInteger.valueOf(3);
        BigInteger TENtotheEIGHTEEN = BigInteger.TEN.pow(18);
        if (THREE.compareTo(n) > -1 && TENtotheEIGHTEEN.compareTo(n) < 1) {
            System.out.println("The integer must be between 3 and 10^18.");
            System.exit(-98);
        }

        BigInteger smallestGoodBase = getSmallestGoodBase(n);
        System.out.println("Smallest Good Base: " + smallestGoodBase);
    }

    /*
        n = k^0 + k^1 + ... k^m

        min # digits 2
        n(10) = 11(n-1)

        max # digits sqr(n) rounded down +1
        31(10) will have a potential maximum of 6 digits and minimum of 2 digits

        n(10) = 1 + b + b^2 + ... b ^ (floor(sqr(n)) + 1)

                      = 3^0 + 3^1 + 3^2 + 3^3
                          1     4    16         64
        31(10) = m(4) = 4^0 + 4^1 + 4^2 but not 4^3
        31(10) = u(5) = 5^0 + 5^1 + 5^2   !!!!
                      = 6^0 + 6^1
                      = 30^0 + 30^1
     */
    private static BigInteger getSmallestGoodBase(BigInteger n) {
        BigInteger largestBase = n.subtract(BigInteger.ONE);
        BigInteger smallestGoodBase = largestBase;
        for(BigInteger base = BigInteger.TWO; base.compareTo(largestBase) < 0 ; base = base.add(BigInteger.ONE)) {
            BigInteger digitTotal = BigInteger.ZERO;
            BigInteger prevDigitVal = BigInteger.ONE;
            for(int digit = 1; prevDigitVal.compareTo(n) < 1; digit++) {
                digitTotal = digitTotal.add(prevDigitVal);
                if (digitTotal.compareTo(n) == 0) {
                    smallestGoodBase = base;
                    break;
                } else if (digitTotal.compareTo(n) == 1) {
                    break;
                }
                prevDigitVal = base.pow(digit);
            }
            if (digitTotal.compareTo(n) == 0) {
                break;
            }
        }
        return smallestGoodBase;
    }
}
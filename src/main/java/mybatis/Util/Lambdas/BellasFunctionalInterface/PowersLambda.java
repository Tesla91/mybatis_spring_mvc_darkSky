package mybatis.Util.Lambdas.BellasFunctionalInterface;

/**
 * Created by nicola on 7/28/17.
 */
public class PowersLambda {
    public static void main(String[] args) {
        /**
         * This is the lambda block.
         */
        long lambdaStart = System.nanoTime();
        BellasPowers lambdaPowers = (x, y) -> Math.pow(x,y);

        double a = lambdaPowers.bellaAction(3,2);
        double b = lambdaPowers.bellaAction(6.9, 3.7);
        double c = lambdaPowers.bellaAction(15,2);
        long lambdaEnd = System.nanoTime();
        System.out.println("lambda = " + (lambdaEnd - lambdaStart));

        /**
         *This is the anonymous inner class.
         */
        long anonInnerStart = System.nanoTime();

        BellasPowers anonPowers = new BellasPowers() {
            @Override
            public double bellaAction(double x, double y) {
                return Math.pow(x,y);
            }
        };

        double d = anonPowers.bellaAction(3,2);
        double e = anonPowers.bellaAction(6.9, 3.7);
        double f = anonPowers.bellaAction(15,2);
        long anonInnerEnd = System.nanoTime();
        System.out.println("anonInner = " + (anonInnerEnd - anonInnerStart));

        /**
         * This is the standard interface implementation.
         */

        long standardStart = System.nanoTime();
        BellasPowersImpl standardPowers = new BellasPowersImpl();


        standardPowers.bellaAction(3,2);
        standardPowers.bellaAction(6.9,3.7);
        standardPowers.bellaAction(15,2);
        long standardEnd = System.nanoTime();
        System.out.println("standard = " + (standardEnd - standardStart));

    }
}

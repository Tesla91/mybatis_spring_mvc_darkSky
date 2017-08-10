package mybatis.Util.Lambdas;

/**
 * Created by nicola on 7/28/17.
 */
public class MacieLambda {
    public static void main(String[] args) {
        MacieInterface maciePowers = (x, y) -> Math.pow(x,y);

        double z = maciePowers.macieAction(3,2);
        double a = maciePowers.macieAction(6.9, 3.7);
        System.out.println("3 ^ 2 is " + z);
        System.out.println("6.9 ^ 3.7 is " + a);

        MacieInterface annonInner = new MacieInterface() {
            @Override
            public double macieAction(double x, double y) {
                return Math.pow(x,y);

            }

        };
       double val = annonInner.macieAction(6,9);
       System.out.println("6 ^ 9 is " + val);
    }
}

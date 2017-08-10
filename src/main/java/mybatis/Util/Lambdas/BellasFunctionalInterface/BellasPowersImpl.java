package mybatis.Util.Lambdas.BellasFunctionalInterface;

/**
 * Created by nicola on 7/28/17.
 */
public class BellasPowersImpl implements BellasPowers {
    @Override
    public double bellaAction(double x, double y) {
    return Math.pow(x,y);
    }
}

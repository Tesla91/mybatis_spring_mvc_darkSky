package mybatis.Util.Lambdas.FactorialLambda;

/**
 * Created by nicola on 7/28/17.
 */
public class NumericFuncImpl implements NumericFunc {
    @Override
    public int func(int n) {
        int result = 1;

        for(int i=1; i <= n; i++)
            result = i * result;

        return result;
    }
}

import com.study.shop.util.DaoUtil;

/**
 * Created by 傲然 on 2017/2/9.
 */
public class BaseTest {
    public BaseTest() {
        DaoUtil.inject(this);
    }
}

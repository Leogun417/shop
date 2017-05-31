import com.study.shop.dao.IGoodDao;
import com.study.shop.model.DaoInject;
import org.junit.Test;

/**
 * Created by 傲然 on 2017/5/26.
 */
public class TestGoodDao extends BaseTest {
    IGoodDao goodDao;

    @DaoInject
    public void setGoodDao(IGoodDao goodDao) {
        this.goodDao = goodDao;
    }

    @Test
    public void testUpdateBy() {
        goodDao.updatePriceByCategory(0.8, 12, "category_id");
    }

}

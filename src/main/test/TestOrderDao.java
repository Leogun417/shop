import com.study.shop.dao.IGoodDao;
import com.study.shop.dao.IOrderDao;
import com.study.shop.dao.IUserDao;
import com.study.shop.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 傲然 on 2017/3/9.
 */
public class TestOrderDao extends BaseTest {
    IOrderDao orderDao;
    IUserDao userDao;
    IGoodDao goodDao;
    @DaoInject
    public void setGoodDao(IGoodDao goodDao) {
        this.goodDao = goodDao;
    }

    @DaoInject
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @DaoInject
    public void setOrderDao(IOrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Test
    public void testAddOrder() {
        Order order = new Order();
        User user = userDao.loadById(5);
        order.setPrice(100);
        order.setBuyDate(new Date());
        order.setStatus(1);
        ArrayList<GoodInCart> goodList = new ArrayList<>();
        GoodInCart goodInCart = new GoodInCart();
        goodInCart.setNumber(11);
        goodInCart.setPrice(100);
        goodInCart.setGood(goodDao.loadById(24));
        goodInCart.setOrder(order);
        goodList.add(goodInCart);
        order.setGoodList(goodList);
        orderDao.add(user, 34, order);
    }

    @Test
    public void testFindOrder() {
        SystemContext.setPageSize(15);
        SystemContext.setOffset(0);
        Pager<Order> orderPager = orderDao.find("", 1);
        List<Order> datas = orderPager.getDatas();
        for (Order order : datas) {
            System.out.println(order.getAddress().getAddress());
        }
    }
}

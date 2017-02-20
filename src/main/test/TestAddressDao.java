import com.study.shop.dao.IAddressDao;
import com.study.shop.dao.IUserDao;
import com.study.shop.model.Address;
import com.study.shop.model.DaoInject;
import org.junit.Test;

import java.util.List;

/**
 * Created by 傲然 on 2017/2/5.
 */
public class TestAddressDao extends BaseTest {
    IUserDao userDao;
    IAddressDao addressDao;

    @DaoInject
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @DaoInject
    public void setAddressDao(IAddressDao addressDao) {
        this.addressDao = addressDao;
    }

    /* @Before
    public void init() {
        userDao = (IUserDao) DaoUtil.createFactory().createDao("userDao");
        addressDao = (IAddressDao) DaoUtil.createFactory().createDao("addressDao");
    }*/

    @Test
    public void testAdd() {
        Address address = new Address();
        address.setAddress("成都");
        address.setPhone("1842");
        address.setPostcode("615000");
        address.setUser(userDao.loadById(1));
        addressDao.add(1, address);
    }

    @Test
    public void testDelete() {
        int id = 2;
        addressDao.delete(id);
    }

    @Test
    public void testList() {
        List<Address> list = addressDao.list(1);
        for (Address address : list) {
            System.out.println(address.getAddress());
        }
    }

    @Test
    public void testLoadById() {
        System.out.println(addressDao);
        System.out.println(addressDao.loadById(1));
    }
}

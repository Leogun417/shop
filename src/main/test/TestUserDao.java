import com.study.shop.dao.UserDao;
import com.study.shop.model.*;
import org.junit.Test;

import java.util.List;

/**
 * Created by 傲然 on 2017/2/3.
 */
public class TestUserDao extends BaseTest{

    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }
    @DaoInject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /*@Before
    public void init() {
        userDao = (UserDao) DaoUtil.createFactory().createDao("userDao");
    }*/

    @Test
    public void testAdd() {
        User user = new User();
        user.setUsername("Dream@qq.com");
        user.setPassword("123");
        user.setNickname("dream");
        user.setType(1);
        userDao.add(user);
    }

    @Test
    public void testDelete() {
        int id = 5;
        userDao.delete(id);
    }

    @Test
    public void testUpdate() {
        User user = userDao.loadById(5);
        user.setNickname("Dream");
        userDao.update(user);
    }

    @Test
    public void testLogin() {
        if (null != userDao.login("leo@qq.com", "123")) {
            System.out.println("成功");
        }
    }

    @Test
    public void testFind() {
        SystemContext.setPageSize(15);
        SystemContext.setOffset(0);
        SystemContext.setOrder("desc");
        SystemContext.setSortBy("id");
        Pager<User> pager = userDao.find("");
        for (User user : pager.getDatas()) {
            System.out.println(user.getId());
        }
    }

    @Test
    public void testLoadById() {
        int id = 1;
        User user = userDao.loadById(id);
        List<Address> addrList = user.getAddrList();
        for (Address address : addrList) {
            System.out.println(address.getAddress());
        }
    }
}

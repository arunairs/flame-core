package cn.blinkmind.flame.core;

import cn.blinkmind.flame.repository.entity.User;
import org.testng.annotations.Test;

public class TmpTest {

    @Test
    public void test() {
        User user=new User();
        user.setUsername("333");
        System.out.println(user);
    }
}

package cn.xdev.auth.test;

import org.junit.Test;

/**
 * @Author Felix.Hsu (felix@x-dev.cn)
 * @Date 2017/9/2913:18
 */
public class UUID {

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            String o = cn.xdev.core.util.ApplicationUtils.randomUUID();
            System.out.println(o);
        }
    }
}

package dev.sanket;

import org.springframework.beans.factory.InitializingBean;

public class NonTomcatBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this.getClass().getName() + " is loaded");
    }
}

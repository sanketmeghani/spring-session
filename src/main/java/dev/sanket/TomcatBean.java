package dev.sanket;

import org.springframework.beans.factory.InitializingBean;

public class TomcatBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this.getClass().getName() + " is loaded");
    }

}

package dev.sanket;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public class DefaultProfileBean implements EnvironmentAware, InitializingBean {

    @Override
    public void setEnvironment(Environment environment) {

        System.out.println("Active Profiles: ");

        for (String profile : environment.getActiveProfiles()) {
            System.out.println(profile);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(this.getClass().getName() + " is loaded");
    }
}

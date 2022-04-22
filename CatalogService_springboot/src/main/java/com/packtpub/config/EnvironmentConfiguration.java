package com.packtpub.config;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.support.ResourcePropertySource;

@Configuration
public class EnvironmentConfiguration {

    @Autowired
    private ConfigurableEnvironment environment;

    @PostConstruct
    public void setup() throws IOException {
        setupDefaultProfiles();

        String[] activeProfiles = environment.getActiveProfiles();
        if (activeProfiles.length > 1) {
            throw new RuntimeException("More than one profiles are active: " + Arrays.toString(activeProfiles));
        }

        Profile activeProfile = Profile.of(activeProfiles[0]) ;
        String propertiesFilename = "application-" + activeProfile.getId() + ".properties";
        
        System.out.println("<EnvironmentConfiguration> - " + propertiesFilename);

        environment.getPropertySources().addFirst(new ResourcePropertySource(activeProfile.name(), "classpath:" + propertiesFilename));
    }

    private void setupDefaultProfiles() {
        if (environment.getActiveProfiles().length == 0) {
            environment.addActiveProfile(Profile.getDefault().name());
        }
    }

}

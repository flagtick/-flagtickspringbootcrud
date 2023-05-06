package com.flagtick;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.flagtick.repository")
@EntityScan(basePackages = "com.flagtick.entity")
public class JpaConfiguration {
}
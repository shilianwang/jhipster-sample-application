package cn.ivisionchina.center.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(cn.ivisionchina.center.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.domain.PersistentToken.class.getName(), jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.domain.User.class.getName() + ".persistentTokens", jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.domain.Region.class.getName(), jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.domain.Country.class.getName(), jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.domain.Location.class.getName(), jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.domain.Department.class.getName(), jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.domain.Department.class.getName() + ".employees", jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.domain.Task.class.getName(), jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.domain.Task.class.getName() + ".jobs", jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.domain.Employee.class.getName(), jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.domain.Employee.class.getName() + ".jobs", jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.domain.Job.class.getName(), jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.domain.Job.class.getName() + ".tasks", jcacheConfiguration);
            cm.createCache(cn.ivisionchina.center.domain.JobHistory.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}

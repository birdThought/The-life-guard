package com.lifeshs.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.bind.PropertiesConfigurationFactory;
import org.springframework.boot.env.PropertySourcesLoader;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.PropertySources;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.lang.reflect.Constructor;

/**
 * @Author Yue.Li
 * @Date 2017/5/9.
 * 配置文件绑定到配置类
 */
@Configuration
public class ApplicationConfiguration implements ResourceLoaderAware {

    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Bean
    public TestLYConfig testLYConfig() {
        return bindPropertiesToTarget(TestLYConfig.class, null, "classpath:config/testly-configuration.yml");
    }

    private <T> T bindPropertiesToTarget(Class<T> clazz, String prefix, String... locations) {
        try {
            Constructor<T> constructor = clazz.getConstructor();
            T newInstance = constructor.newInstance();

            PropertiesConfigurationFactory<Object> factory = new PropertiesConfigurationFactory<Object>(newInstance);
            factory.setPropertySources(loadPropertySources(locations));
            factory.setConversionService(new DefaultConversionService());
            if (StringUtils.isNotBlank(prefix)) {
                factory.setTargetName(prefix);
            }
            factory.bindPropertiesToTarget();
            return newInstance;

        } catch (Exception ex) {
            String targetClass = ClassUtils.getShortName(clazz);
            throw new BeanCreationException(clazz.getSimpleName(), "Could not bind properties to " + targetClass + " (" + clazz.getSimpleName() + ")", ex);
        }
    }

    private PropertySources loadPropertySources(String[] locations) {
        try {
            PropertySourcesLoader loader = new PropertySourcesLoader();
            for (String location : locations) {
                Resource resource = this.resourceLoader.getResource(location);
                loader.load(resource);
            }
            return loader.getPropertySources();
        }
        catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

}

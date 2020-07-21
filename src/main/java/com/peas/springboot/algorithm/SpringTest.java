package com.peas.springboot.algorithm;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;

/**
 * @Description
 * @Author 7287
 * @Date 2020/6/4 16:58
 * @Version V1.0
 **/
public class SpringTest {
    public static void main(String[] args) throws IOException {
//        String classpath = "classpath:resources/*.xml";
//        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
//        pathMatchingResourcePatternResolver.getResources(classpath);

        ClassPathResource resource = new ClassPathResource("resources/spring.xml"); // <1>
        ClassPathResource resource1 = new ClassPathResource("resources/spring-test.xml");
//        String path = resource.getFile().getPath();
//        URL url = resource.getURL();
//        Resource resource1 = new DefaultResourceLoader().getResource("resources/test.xml");
//        String path1 = resource1.getFile().getPath();
//
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory(); // <2>
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory); // <3>
        reader.loadBeanDefinitions(resource);
        reader.loadBeanDefinitions(resource1);
    }
}

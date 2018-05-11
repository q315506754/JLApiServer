package com.jiangli.api.conf

import org.springframework.beans.factory.BeanCreationException
import org.springframework.boot.bind.PropertiesConfigurationFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.core.convert.support.DefaultConversionService
import org.springframework.core.env.MutablePropertySources
import org.springframework.core.env.PropertiesPropertySource
import org.springframework.util.ClassUtils
import org.springframework.util.StringUtils
import java.io.File
import java.io.FileInputStream
import java.util.*

/**
 *
 *
 * @author Jiangli
 * @date 2018/5/11 10:02
 */
object ExtUtil{

}

fun ConfigurationPropertiesBindingPostProcessor.postProcess(bean: Any, dir: File, file: String) {
    val propFile = File(dir, file)
    if (!propFile.exists()) {
        return
    }

    var annotation: ConfigurationProperties = AnnotationUtils
            .findAnnotation(bean.javaClass, ConfigurationProperties::class.java)!!

    val factory = PropertiesConfigurationFactory(bean)
    val mutablePropertySources = MutablePropertySources()

    val properties = Properties()
    properties.load(FileInputStream(propFile))
    mutablePropertySources.addLast(PropertiesPropertySource(file, properties))

    factory.setPropertySources(mutablePropertySources)

//        factory.setValidator(determineValidator(bean))
    factory.setConversionService(DefaultConversionService())
    if (annotation != null) {
        factory.setIgnoreInvalidFields(annotation.ignoreInvalidFields)
        factory.setIgnoreUnknownFields(annotation.ignoreUnknownFields)
        factory.setExceptionIfInvalid(annotation.exceptionIfInvalid)
        factory.setIgnoreNestedProperties(annotation.ignoreNestedProperties)
        if (StringUtils.hasLength(annotation.prefix)) {
            factory.setTargetName(annotation.prefix)
        }
    }
    try {
        factory.bindPropertiesToTarget()
    } catch (ex: Exception) {
        val targetClass = ClassUtils.getShortName(bean.javaClass)
        throw BeanCreationException("bean", "Could not bind properties to "
                + targetClass, ex)
    }
}
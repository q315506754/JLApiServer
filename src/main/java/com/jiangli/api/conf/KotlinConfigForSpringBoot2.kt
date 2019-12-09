package com.jiangli.api.conf

import com.jiangli.api.utils.BeanHelper
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.core.env.MutablePropertySources
import org.springframework.core.env.PropertiesPropertySource
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
    val javaClass = this.javaClass
    val field = javaClass.getDeclaredField("configurationPropertiesBinder")
    field.isAccessible = true
    val configurationPropertiesBinder = field.get(this)

    val javaClass2 = configurationPropertiesBinder.javaClass
    val field2 = javaClass2.getDeclaredField("propertySources")
    field2.isAccessible = true
    val propertySources = field2.get(configurationPropertiesBinder) as MutablePropertySources

    val properties = Properties()
        properties.load(FileInputStream(propFile))
    propertySources.addFirst(PropertiesPropertySource(file, properties))

    var annotation: ConfigurationProperties = AnnotationUtils
                    .findAnnotation(bean.javaClass, ConfigurationProperties::class.java)!!
    val prefix = annotation.prefix

    println(propertySources)
    val map :MutableMap<String ,Any> =java.util.HashMap()
    properties.forEach { t, u ->
        var key = t.toString()
        key = key.replace("$prefix.", "")

        map.put(key,u)
    }
//    map.putAll(properties)

    BeanHelper.populate(bean,map)

//    postProcessBeforeInitialization(bean, "applicationConfig")
//    var annotation: ConfigurationProperties = AnnotationUtils
//                .findAnnotation(bean.javaClass, ConfigurationProperties::class.java)!!
//
//    if (annotation != null) {
//        bind(bean, beanName, annotation)
//    }

    //    this.configurationPropertiesBinder

//    var annotation: ConfigurationProperties = AnnotationUtils
//            .findAnnotation(bean.javaClass, ConfigurationProperties::class.java)!!
//
//    val factory = PropertiesConfigurationFactory(bean)
//    val mutablePropertySources = MutablePropertySources()
//
//    val properties = Properties()
//    properties.load(FileInputStream(propFile))
//    mutablePropertySources.addLast(PropertiesPropertySource(file, properties))
//
//    factory.setPropertySources(mutablePropertySources)
//
////        factory.setValidator(determineValidator(bean))
//    factory.setConversionService(DefaultConversionService())
//    if (annotation != null) {
//        factory.setIgnoreInvalidFields(annotation.ignoreInvalidFields)
//        factory.setIgnoreUnknownFields(annotation.ignoreUnknownFields)
//        factory.setExceptionIfInvalid(annotation.exceptionIfInvalid)
//        factory.setIgnoreNestedProperties(annotation.ignoreNestedProperties)
//        if (StringUtils.hasLength(annotation.prefix)) {
//            factory.setTargetName(annotation.prefix)
//        }
//    }
//    try {
//        factory.bindPropertiesToTarget()
//    } catch (ex: Exception) {
//        val targetClass = ClassUtils.getShortName(bean.javaClass)
//        throw BeanCreationException("bean", "Could not bind properties to "
//                + targetClass, ex)
//    }
}
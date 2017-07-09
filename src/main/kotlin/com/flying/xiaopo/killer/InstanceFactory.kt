package com.flying.xiaopo.killer

import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KClass

/**
 * @author wupanjie on 2017/7/8.
 */
class InstanceFactory {
  val instances = ConcurrentHashMap<KClass<*>, Any>()

  inline fun <reified T> retrieveInstance(
      definition: InjectionDefinition<*>,
      clazz: KClass<*>): T? {
    var instance = findInstance<T>(clazz)
    if (instance == null) {
      instance = createInstance(definition, clazz)
    }

    return instance
  }

  inline fun <reified T> findInstance(clazz: KClass<*>): T? = instances[clazz] as? T

  inline fun <reified T> createInstance(definition: InjectionDefinition<*>, clazz: KClass<*>): T? {
    try {
      val instance = definition.inject.invoke() as Any
      instances[clazz] = instance
      return instance as T
    } catch (e: Exception) {
      return null
    }
  }
}
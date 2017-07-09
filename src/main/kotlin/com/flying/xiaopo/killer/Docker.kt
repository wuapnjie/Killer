package com.flying.xiaopo.killer

/**
 * @author wupanjie on 2017/7/8.
 */
class Docker {
  val injectionRegistry = InjectionRegistry()

  val instanceFactory = InstanceFactory()

  inline fun <reified T> get(): T
      = getOptional<T>() ?: throw InstanceNotFoundException("Can not resolve the instance of ${T::class}")

  inline fun <reified T> getOptional(): T? {
    val definition = injectionRegistry.find(T::class)
    definition?.let {
      return instanceFactory.retrieveInstance(definition, T::class)
    }
    return null
  }

  fun accept(definition: InjectionDefinition<*>) {
    injectionRegistry.declare<Any>(definition)
  }

}
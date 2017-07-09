package com.flying.xiaopo.killer

import kotlin.collections.HashSet
import kotlin.collections.filter
import kotlin.collections.firstOrNull
import kotlin.collections.plusAssign
import kotlin.reflect.KClass

/**
 * @author wupanjie on 2017/7/8.
 */
class InjectionRegistry {
  val injectionDefinitions = HashSet<InjectionDefinition<*>>()

  inline fun <reified T> declare(
      noinline inject: () -> T,
      clazz: KClass<*> = T::class) {
    var definition = find(clazz)
    definition?.let {
      injectionDefinitions.remove(it)
    }
    definition = InjectionDefinition(inject, clazz)
    injectionDefinitions += definition
  }

  inline fun <reified T> declare(def: InjectionDefinition<*>) {
    val found = find(def.clazz)
    found?.let {
      injectionDefinitions.remove(it)
    }
    injectionDefinitions += def
  }

  fun find(clazz: KClass<*>)
      = injectionDefinitions.filter { it.clazz == clazz }.firstOrNull()

}
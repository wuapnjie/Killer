package com.flying.xiaopo.killer

/**
 * @author wupanjie on 2017/7/8.
 */
abstract class Module {

  val provided = arrayListOf<InjectionDefinition<*>>()

  inline fun <reified T> provide(noinline inject: () -> T) {
    provided += InjectionDefinition(inject, T::class)
  }

  abstract fun declare()
}
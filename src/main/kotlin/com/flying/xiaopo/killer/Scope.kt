package com.flying.xiaopo.killer

import kotlin.reflect.KClass

/**
 * 注入作用域
 * @author wupanjie on 2017/7/8.
 */
class Scope(val clazz: KClass<*>? = null) {
  companion object {
    val default = Scope()
  }
}
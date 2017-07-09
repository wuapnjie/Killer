package com.flying.xiaopo.killer

import kotlin.reflect.KClass

/**
 * @author wupanjie on 2017/7/8.
 */
class InjectionDefinition<out T>(val inject: () -> T, val clazz: KClass<*>) {

}
package com.flying.xiaopo.killer

/**
 * @author wupanjie on 2017/7/8.
 */
class Killer private constructor(scope: Scope) {
  lateinit var docker: Docker

  companion object {
    val dockers = HashMap<Scope, Docker>()
    fun with(scope: Scope = Scope.default): Killer = Killer(scope).apply {
      docker = dockers[scope] ?: Docker()
      dockers[scope] = docker
    }
  }

  fun appear(vararg modules: Module): Docker {
    modules.forEach {
      it.declare()
      it.provided.forEach { docker.accept(it) }
    }

    return docker
  }

}
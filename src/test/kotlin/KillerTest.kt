import com.flying.xiaopo.killer.Killer
import com.flying.xiaopo.killer.Module
import org.junit.Test

/**
 * @author wupanjie on 2017/7/9.
 */
class ServiceA {
  fun printService() {
    println("ServiceA")
  }
}

class ModuleA : Module() {
  override fun declare() {
    provide { ServiceA() }
  }
}

class KillerTest {
  @Test
  fun killer() {
    val module = ModuleA()
    val docker = Killer.with().appear(module)
    docker.get<ServiceA>().printService()
  }
}
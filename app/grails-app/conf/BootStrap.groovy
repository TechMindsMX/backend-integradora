import com.tim.one.integradora.UserMarshaller
import grails.converters.JSON

class BootStrap {

  def init = { servletContext ->
    JSON.createNamedConfig('user') {
      it.registerObjectMarshaller(new UserMarshaller())
    }
  }
  def destroy = {
  }
}

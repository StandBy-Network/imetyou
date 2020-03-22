package i.met.you.ws;

import javax.jws.WebService;


@WebService(endpointInterface = "i.met.you.ws.IMYWebService")
public class IMCWebService implements IMYWebService{
	
	@Override
	public String helloWorld(){
		return "Hello World";
	}

}

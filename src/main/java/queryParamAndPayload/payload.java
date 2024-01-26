package queryParamAndPayload;

import java.util.HashMap;
import java.util.Map;

public class payload {

	public static Map<String,Object> postPayload(String name, String job ){
		Map<String,Object> payload = new HashMap<String,Object>();
		payload.put("name",name);
		payload.put("job", job);
		
		return payload;
    }
	
	
	
}

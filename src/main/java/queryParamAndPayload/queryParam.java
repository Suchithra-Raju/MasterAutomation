package queryParamAndPayload;

import java.util.HashMap;
import java.util.Map;

public class queryParam {
	
	
	public static Map<String,Object> queryParamWithoutContainer(String page ){
		Map<String,Object> queryParam = new HashMap<String,Object>();
		queryParam.put("page",page);
		return queryParam;
	}

}

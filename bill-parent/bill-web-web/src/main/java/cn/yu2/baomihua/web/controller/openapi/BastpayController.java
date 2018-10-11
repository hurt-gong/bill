package cn.yu2.baomihua.web.controller.openapi;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.yu2.baomihua.web.JsonResult;
import cn.yu2.baomihua.web.controller.BaseController;

@Controller
@RequestMapping("/openapi/")
public class BastpayController extends BaseController {

	@ResponseBody
	@RequestMapping(value = "/getToken")
	public JsonResult loadTeacher() {

		logger.info("认证");
		String param = getParamByReader();
		logger.info(param);
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "000");
		map.put("2", "111");
		map.put("3", "222");
		map.put("4", "333");
		JsonResult result = new JsonResult();
		result.setCode(0);
		result.setData(map);
		result.setMsg("success");
		return result;
	}
	
	// 获取参数
	public String getParamByReader() {
		String registInfo = "";
		try {

			BufferedReader bufferReader = this.request.getReader();
			StringBuffer buffer = new StringBuffer();
			String line = " ";
			while ((line = bufferReader.readLine()) != null) {
				buffer.append(line);
			}
			registInfo = buffer.toString();
			System.out.println(registInfo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return registInfo;
	}
}

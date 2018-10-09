package cn.yu2.baomihua.web.controller.upload.convert;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

public class FileConverterFactory implements DisposableBean {

	private static final Logger logger = LoggerFactory.getLogger(FileConverterFactory.class);

	private Map<String, Converter> converterMap = new HashMap<String, Converter>();

	ExecutorService newCachedThreadPool;

	public FileConverterFactory() {
		newCachedThreadPool = Executors.newCachedThreadPool();
	}

	public Map<String, Converter> getConverterMap() {
		return converterMap;
	}

	public void setConverterMap(Map<String, Converter> converterMap) {
		this.converterMap = converterMap;
	}

	public void doConvert(final String source) {
		if (StringUtils.isBlank(source))
			return;
		else
			newCachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					String key = FilenameUtils.getExtension(source.toUpperCase());
					if (converterMap.containsKey(key)) {
						Converter converter = converterMap.get(key);
						try {
							converter.convert(source);
						} catch (Exception e) {
							logger.error("文件转换失败，转换器：" + converter.getClass().getName() + "，文件地址：" + source, e);
						}
					}
				}
			});
	}

	@Override
	public void destroy() throws Exception {
		newCachedThreadPool.shutdownNow();
	}
}
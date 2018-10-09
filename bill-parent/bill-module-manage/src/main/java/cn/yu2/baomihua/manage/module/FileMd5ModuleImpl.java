package cn.yu2.baomihua.manage.module;

import javax.annotation.Resource;

import cn.yu2.baomihua.core.rpc.AbstractModule;
import cn.yu2.baomihua.core.rpc.Response;
import cn.yu2.baomihua.manage.entity.FileMd5;
import cn.yu2.baomihua.manage.service.IFileMd5Service;


public class FileMd5ModuleImpl extends AbstractModule implements IFileMd5Module {

	@Resource(name = "fileMd5ServiceImpl")
	private IFileMd5Service fileMd5Service;


	@Override
	public Response<Boolean> addFileMd5( FileMd5 fileMd5 ) {
		return result(fileMd5Service.addFileMd5(fileMd5));
	}


	@Override
	public Response<FileMd5> getFileMd5ByMd5( String md5 ) {
		return success(fileMd5Service.selectByMd5(md5));
	}

}

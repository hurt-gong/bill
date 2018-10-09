package cn.yu2.baomihua.manage.service.impl;

import org.springframework.stereotype.Service;

import cn.yu2.baomihua.core.service.BaseServiceImpl;
import cn.yu2.baomihua.manage.entity.FileMd5;
import cn.yu2.baomihua.manage.mapper.FileMd5Mapper;
import cn.yu2.baomihua.manage.service.IFileMd5Service;


/**
 * 
 * 文件md5资源 service
 * 
 * @author 8
 *
 */

@Service
public class FileMd5ServiceImpl extends BaseServiceImpl<FileMd5Mapper, FileMd5>implements IFileMd5Service {

	@Override
	public int addFileMd5( FileMd5 fileMd5 ) {

		/**
		 * 如果存在更新，否则插入新记录。
		 */
		/*int rlt = baseMapper.updateSelectiveById(fileMd5);
		if ( rlt >= 1 ) {
			return rlt;
		}*/

		return baseMapper.addFileMd5(fileMd5);
	}


	@Override
	public FileMd5 selectByMd5( String md5 ) {
		return baseMapper.selectById(md5);
	}


}

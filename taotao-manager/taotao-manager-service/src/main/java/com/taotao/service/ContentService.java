package com.taotao.service;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {
	public EasyUIResult getContentList(long catId, Integer page, Integer rows);
	TaotaoResult insertContent(TbContent content);
}

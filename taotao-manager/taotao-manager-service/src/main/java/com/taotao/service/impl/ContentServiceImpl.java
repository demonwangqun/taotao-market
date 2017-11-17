package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.ContentService;

/**
 * 内容管理
 * 
 * @author home
 * 
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_CONTENT_SYNC_URL}")
	private String REST_CONTENT_SYNC_URL;

	@Override
	public EasyUIResult getContentList(long catId, Integer page, Integer rows) {

		// 根据category_id查询列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(catId);
		// 分页处理
		PageHelper.startPage(page, rows);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		// 取分页信息
		PageInfo<TbContent> pageInfo = new PageInfo<>(list);
		EasyUIResult result = new EasyUIResult(pageInfo.getTotal(), list);
		return result;

	}

	@Override
	public TaotaoResult insertContent(TbContent content) {
		// 补全pojo
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		// 添加缓存同步逻辑
		try {
			HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL
					+ content.getCategoryId());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return TaotaoResult.ok();
	}

}

package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;

/**
 * 商品规格参数模板
 * 
 * @author home
 * @param <EasyUIResult>
 * 
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	

	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		// 判断是否查询到结果
		if (list != null && list.size() > 0) {
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();

	}

	@Override
	public TaotaoResult insertItemParam(TbItemParam itemParam) {
		// 补全pojo
		itemParam.setCreated(new Date());
		itemParam.setUpdated(new Date());
		//插入到规格参数模板表中
		itemParamMapper.insert(itemParam);
		return TaotaoResult.ok();
	}

	@Override
	public EasyUIResult getItemParamList(Integer page, Integer rows) throws Exception {
		//分页处理
		PageHelper.startPage(page, rows);
		//查询规格列表
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		//取分页信息
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		//返回结果
		EasyUIResult result = new EasyUIResult(pageInfo.getTotal(), list);
		
		return result;
	}

}

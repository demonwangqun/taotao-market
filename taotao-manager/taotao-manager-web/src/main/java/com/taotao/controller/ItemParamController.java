package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;

/**
 * 商品规格参数模板管理Controller
 * @author home
 *
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamSerivce;
	
	
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId){
	
		TaotaoResult result =  itemParamSerivce.getItemParamByCid(itemCatId);
	    
		
		return result;
	}
	
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult insertTtemParam(@PathVariable Long cid ,String paramData){
		//创建pojo对象
		TbItemParam itemParam =new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
	    TaotaoResult result = 	itemParamSerivce.insertItemParam(itemParam);
	                                                
		return result;
	}
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIResult getItemParamList(@RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "30") Integer rows) throws Exception {
		//查询列表
		EasyUIResult result = itemParamSerivce.getItemParamList(page, rows);
		return result;
	}
}

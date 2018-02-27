/**
 * 系统项目名称
 * com.pk10.active.console.controller
 * BaseController.java
 * 
 * 2017年6月9日-下午2:28:19
 *  2017金融街在线公司-版权所有
 *
 */
package com.pk10.active.console.controller;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.pk10.active.console.common.bean.EasyUIResult;
import com.pk10.active.console.common.bean.Result;
import com.pk10.active.console.service.BaseService;

/**
 *
 * BaseController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月9日 下午2:28:19
 * 
 * @version 1.0.0
 *
 */ 
public class BaseController<T,S> { 

	
	@Autowired
	BaseService<T> baseService;
	
	@SuppressWarnings("unused")
	private Class<T> clazz;
	
	
	@SuppressWarnings("unchecked")
	protected S getService(){
		return (S) baseService;
	}
	
  @SuppressWarnings("unchecked")
    public BaseController() {
        super();
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType ptype = (ParameterizedType) type;
        this.clazz = (Class<T>) ptype.getActualTypeArguments()[0];
    }

	
	@GetMapping("all")
	public ResponseEntity<List<T>> findAll(){
		return ResponseEntity.ok(baseService.queryAll());
	}
	@GetMapping("list")
	public ResponseEntity<List<T>> findList(T t){
		return ResponseEntity.ok(baseService.queryListByWhere(t));
	}
	@GetMapping("page") 
	public ResponseEntity<EasyUIResult> findByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "30") Integer rows,String[] sort,String[] order, T t) throws Exception{
		PageInfo<T> pageInfo = baseService.queryListByPageAndOrder(t, page, rows,sort,order);
		 EasyUIResult easyUIResult = new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
		return ResponseEntity.ok(easyUIResult);
	}
	@PutMapping
	public Result<Boolean> update(@RequestBody T t) throws Exception{
		baseService.updateByIdSelective(t);
		return Result.success(true);
	}     
	
	@PostMapping 
	public Result<Boolean> save(@RequestBody T t) throws Exception{
		baseService.saveSelective(t);
		return Result.success(true);
	}
	@DeleteMapping("{ids}")
	public Result<Boolean> delete(@PathVariable("ids") String ids){
		baseService.deleteByIds(Arrays.asList(ids.split(",")));
		return Result.success(true);
	}
	@GetMapping("{id}")
	public T getById(@PathVariable("id") Serializable id){
		return baseService.queryByID(id);
	} 
}

package cn.com.chenyixiao.rrs.dao;

import java.util.List;

public interface BaseDAO<T> {
	
	public void add(T entity);
	public void update(T entity);
	public T get(T entity, Long id);
	public List<T> getAll();
	public void delete(T entity, Long id);
	public void deleteAll();
}

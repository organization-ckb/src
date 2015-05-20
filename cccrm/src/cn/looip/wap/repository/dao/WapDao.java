package cn.looip.wap.repository.dao;



import org.springframework.stereotype.Repository;



@Repository
public interface WapDao {
	
	public Integer getUserName(String username);
	public Integer getLogin(String username,String password);
}

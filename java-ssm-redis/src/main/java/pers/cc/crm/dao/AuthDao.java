package pers.cc.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.LanguageDriver.SimpleSelectInExtendedLanguageDriver;

import pers.cc.crm.entity.Auth;

/**
 * Created by cc on 2016/12/7.
 */
public interface AuthDao {

	@Select("select * from sys_auth where authId=#{authId}")
	Auth getAuthWithAuthId(String authId);

	@Lang(SimpleSelectInExtendedLanguageDriver.class)
	@Select("select * from sys_auth where parentId=#{parentId} and authId in (#{authIds})")
	List<Auth> getAuthWithParentId(@Param("parentId") String parentId, @Param("authIds") List<String> authIds);

	@Select("select * from sys_auth where parentId=#{parentId}")
	List<Auth> getTopNodeTreeAuthWithParentId(String parentId);
}

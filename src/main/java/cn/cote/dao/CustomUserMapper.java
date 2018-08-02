package cn.cote.dao;

import cn.cote.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

@Mapper
public interface CustomUserMapper {
    String findPassWordByName(User user);
    Set<String> findRoles(User user);
}

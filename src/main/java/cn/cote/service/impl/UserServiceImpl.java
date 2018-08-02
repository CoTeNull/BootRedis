package cn.cote.service.impl;

import cn.cote.dao.RolesPermissionsMapper;
import cn.cote.dao.TUserMapper;
import cn.cote.dao.UserRolesMapper;
import cn.cote.pojo.*;
import cn.cote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private TUserMapper tUserMapper;

    @Override
    public String findPassWordByName(String userName) {
        TUserExample example = new TUserExample();
        TUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<TUser> list =tUserMapper.selectByExample(example);
        String password =list.get(0).getUserPassword();
        if(password != null){
            return password;
        }else{return null;}
    }

    @Autowired
    private UserRolesMapper userRolesMapper;

    @Override
    public Set<String> findRolesByUserName(String userName) {

        UserRolesExample example = new UserRolesExample();
        UserRolesExample.Criteria criteria = example.createCriteria();
        criteria.andUserNameEqualTo(userName);
        List<UserRoles> list = userRolesMapper.selectByExample(example);
        List<String> list1 = new ArrayList<>();
        if(list.size() != 0){
           for(UserRoles userRoles:list){
               list1.add(userRoles.getRolesName());
           }
            Set<String> rolesSet = new HashSet<>(list1);
            return rolesSet;
        }else{
            return null;
        }

    }

    @Autowired
    private RolesPermissionsMapper rolesPermissionsMapper;

    @Override
    public Set<String> findPermissionsByUserName(String userName) {
        Set<String> str = findRolesByUserName(userName);
        Set<String> resout = new HashSet<>();
        if(str != null) {
            RolesPermissionsExample example = new RolesPermissionsExample();
            RolesPermissionsExample.Criteria criteria = example.createCriteria();
            List<String> result = new ArrayList<>(str);
            criteria.andRolesNameIn(result);
            List<RolesPermissions> list = rolesPermissionsMapper.selectByExample(example);

            if (list != null){
                for (RolesPermissions rp : list){
                    resout.add(rp.getPermissionsName());
                }
            }
        }
        return resout;

    }
}

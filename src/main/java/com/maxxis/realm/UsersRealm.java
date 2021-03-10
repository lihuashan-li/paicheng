package com.maxxis.realm;

import com.maxxis.domain.Permission;
import com.maxxis.domain.Role;
import com.maxxis.domain.Users;
import com.maxxis.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UsersRealm extends AuthorizingRealm {
    @Autowired
    private IUserService iUserService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        执行授权逻辑
//        获取到登录用户
        Users users= (Users) principalCollection.getPrimaryPrincipal();
//            根据登录用户的id查出来该用户的角色信息
        List<Role> roles= null;
        try {
            roles = iUserService.findRoleById(users.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //用来装上面的角色的名字的set容器,遍历每一个角色
        Set<String> set = new HashSet<>();  //角色名字
        Set<String> permsSet = new HashSet<>();
        List<Permission> permissions=null;
        for (Role role : roles){
            set.add(role.getRoleName());
            //接从数据库中查询角色对应的权限，根据角色的id查询到该角色对应的权限
              int rid= role.getId();
            try {
                permissions= iUserService.findPermissionByrid(rid);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            System.out.println("用户的权限是"+permissions);
            for (Permission permission : permissions){
                permsSet.add(permission.getPermissionName());
            }
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.setRoles(set);
        info.setStringPermissions(permsSet);
//        System.out.println("用户的角色信息"+roles);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//       执行认证逻辑
        Users users=new Users();
//        1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        try {
            users = iUserService.findByUsername(token.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (users==null){

//                用户名不存在
            return null;
//                shiro底层会抛出UnknownAccountException
        }
        //        判断密码
        return new SimpleAuthenticationInfo(users, users.getPASSWORD(), "");


    }
}

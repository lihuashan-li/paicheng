package com.maxxis.config;

import com.maxxis.realm.UsersRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration //声明这是一个配置类
public class ShiroConfig {
//    创建shiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean GetShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager){
//     过滤器
        ShiroFilterFactoryBean shiroFilterFactoryBean =new ShiroFilterFactoryBean();
//        设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String,String> filterMap=new LinkedHashMap<String, String>();



        //设置不拦截的资源
  filterMap.put("/static/**","anon");
//        filterMap.put("/css/**","anon");
//        filterMap.put("/img/**","anon");
//        filterMap.put("/plugins/**","anon");
      filterMap.put("/pages/login.jsp","anon");
       filterMap.put("/user/login","anon");
        filterMap.put("/user/loginReWrite","anon");


             //    设置资源授权
        filterMap.put("/test/findTestByTestItem","perms[BC]");
        filterMap.put("/test/findTestByTestItemRR","perms[RR]");
        filterMap.put("/test/findTestByTestItemHSU","perms[HSU]");
        filterMap.put("/test/findTestByTestItemSStiffness","perms[Stiffness]");
        filterMap.put("/test/findTestByTestItemSP","perms[SP]");
        filterMap.put("/test/findTestByTestItem3D","perms[3D]");
        filterMap.put("/user/findAll","perms[用户管理]");
        filterMap.put("/role/findAll","perms[角色管理]");
        filterMap.put("/permission/findAll","perms[资源权限管理]");
        filterMap.put("/pages/WeiTuoChaXun.jsp","perms[委托查询]");
//        filterMap.put("/item:kaishi","perms[SP开始]");
//        filterMap.put("/item:wancheng","perms[SP完成]");
//        filterMap.put("/item:caozuo","perms[SP操作]");
//        filterMap.put("/item:quxiao","perms[SP取消]");
//        filterMap.put("/item:shunweipaicheng","perms[SP顺位排程]");
//        filterMap.put("/item:chaweipaicheng","perms[SP插位排程]");
//        filterMap.put("/item:daipaiquxiao","perms[SP待排取消]");
//        filterMap.put("/item:gongshiweihu","perms[SP工时维护]");
//        filterMap.put("/item:paichenggengxin","perms[SP排程更新]");
//        filterMap.put("/item:yijianpaicheng","perms[SP一键排程]");
//        filterMap.put("/item:yipaipiliangcaozuo","perms[SP已排批量操作]");
//        filterMap.put("/item:yipaipiliangcaozuoDaiPai","perms[SP待排批量操作]");


        //设置拦截的资源
         filterMap.put("/**","authc");



         //设置拦截以后跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/pages/login.jsp");


        //设置没有权限以后跳转的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/pages/403.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;

    }
//    创建DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager GetDefaultWebSecurityManager(UsersRealm usersRealm){
//        安全管理器
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
//        关联Realm
        defaultWebSecurityManager.setRealm(usersRealm);
        return  defaultWebSecurityManager;
    }
//    创建Realm
    @Bean
    public UsersRealm  usersRealm(){
        UsersRealm usersRealm =new UsersRealm();
        return usersRealm;
    }




}

package com.maxxis.controller;

import com.github.pagehelper.PageInfo;
import com.maxxis.domain.Role;
import com.maxxis.domain.Users;
import com.maxxis.service.IUserService;
import org.apache.jasper.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@SuppressWarnings("unchecked")
    @RequestMapping("/user")
public class userController {
//   ModelAndView modelAndView=new ModelAndView();

   //登录的方法
    @RequestMapping("/login")
   public  String Login(String username, String password,Model model) {


//获取Subject
        Subject subject = SecurityUtils.getSubject();
////       封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//       执行登录方法
        try {
            subject.login(token);
            return "main";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
          model.addAttribute("msg", "密码错误");
            return "login";
        }

    }

    class ReturnUserInfo{
        public String loginInfo;
        public List<Role> roleList;
    }

    @RequestMapping("/loginReWrite")
    @ResponseBody
    public ReturnUserInfo LoginReWrite(String username, String password) {
        ReturnUserInfo returnUserInfo = new ReturnUserInfo();
        String loginInfo = "";
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            Users user = (Users) subject.getPrincipal();
            user = iUserService.findById(user.getId());
            List<Role> roleList = user.getRoles();
            returnUserInfo.roleList = roleList;
            loginInfo = "success";
        } catch (UnknownAccountException e) {
            loginInfo = "用户名不存在!";
        } catch (IncorrectCredentialsException e) {
            loginInfo = "密码错误!";
        } catch (Exception e) {
            loginInfo = "登录发生错误!";
        }
        returnUserInfo.loginInfo = loginInfo;

        return returnUserInfo;
    }

    //用户退出的方法
    @RequestMapping("/logout")
    public String logout(){
    Subject subject = SecurityUtils.getSubject();
      subject.logout();
      return "login";
    }

    ModelAndView modelAndView =new ModelAndView();
    @Autowired
    private IUserService iUserService;

    //  用户管理的方法
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") int page, @RequestParam(name = "size", required = true, defaultValue = "10") int size)throws Exception{
       List<Users> users= iUserService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(users);
       modelAndView.addObject("userList",pageInfo);
       modelAndView.setViewName("user-list");
       return modelAndView;
}

//添加用户的方法
    @RequestMapping("/save")
    public String save(Users users)throws Exception{
        iUserService.save(users);
        return "redirect:/user/findAll";
    }

//    用户详情
    @RequestMapping("/findById")
    public  ModelAndView findById(int id) throws  Exception{
      Users users=  iUserService.findById(id);
      modelAndView.addObject("user",users);
      modelAndView.setViewName("user-show");
        System.out.println(users);
      return  modelAndView;
    }

//    删除用户
    @RequestMapping("/delete")
    public String delete(int id) throws  Exception{

    iUserService.delete(id);
    return  "redirect:/user/findAll";
    }


//    搜索
    @RequestMapping("/findByUserUsername")
    public  ModelAndView  findByUserUsername(String username) throws  Exception{
        System.out.println(username);
        List<Users> users = iUserService.findByUserUsername(username);
        PageInfo pageInfo = new PageInfo(users);
        modelAndView.addObject("userList",pageInfo);
        modelAndView.setViewName("user-list");

        return modelAndView;
    }
@RequestMapping("/findUserByIdAndAllRole")
public  ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true)int userId)throws  Exception{
//           查询出这个用户
             Users users=  iUserService.findById(userId);

//            根据id查询出来这个用户没有的角色
               List<Role> roles=iUserService.findUserByIdAndAllRole(userId);
               modelAndView.addObject("user",users);
               modelAndView.addObject("roleList",roles);
               modelAndView.setViewName("user-role-add");

             return  modelAndView;
}

//用户添加角色
    @RequestMapping("/addRoleToUser")
    public  String addRoleToUser(int userId,@RequestParam(name = "ids",required = true) int[] roleids)throws  Exception{
        iUserService.addRoleToUser(userId,roleids);
        return "redirect:/user/findAll";
    }





}

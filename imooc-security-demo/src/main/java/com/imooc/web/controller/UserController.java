/**
 * 
 */
package com.imooc.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.imooc.security.core.support.SimpleResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;
import com.imooc.security.app.social.AppSingUpUtils;
import com.imooc.security.core.properties.SecurityProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author zhailiang
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
	@Autowired
	private AppSingUpUtils appSingUpUtils;
	
	@Autowired
	private SecurityProperties securityProperties;

	/**
	 * 使用手机号注册
	 * @param mobile
	 * @param code
	 * @return
	 */
	@PostMapping("/registBymobile")
	public SimpleResponse registByMobile(String mobile,String code){
		//这里添加手机号的注册逻辑
        return new SimpleResponse("注册成功");
	}
	@PostMapping("/registByEmail")
    public SimpleResponse registByEmail(String email ,String code){
		//这里添加邮箱的注册逻辑
		return new SimpleResponse("注册成功");
	}

	/**
	 * 微信等第三方登陆
	 */
	@PostMapping("/regist")
	public void regist(User user, HttpServletRequest request) {
		
		//不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
		String userId = user.getUsername();
		//providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
		appSingUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
	}
	
	@GetMapping("/me")
	public Object getCurrentUser(Authentication user, HttpServletRequest request) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		
//		String token = StringUtils.substringAfter(request.getHeader("Authorization"), "bearer ");
//		
//		Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
//					.parseClaimsJws(token).getBody();
//		
//		String company = (String) claims.get("company");
//		
//		System.out.println(company);
		
		return user;
	}

	@PostMapping
	@ApiOperation(value = "创建用户")
	public User create(@Valid @RequestBody User user) {

		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());

		user.setId("1");
		return user;
	}

	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user, BindingResult errors) {

		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());

		user.setId("1");
		return user;
	}

	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id) {
		System.out.println(id);
	}

	@GetMapping
	@JsonView(User.UserSimpleView.class)
	@ApiOperation(value = "用户查询服务")
	public List<User> query(UserQueryCondition condition,
			@PageableDefault(page = 2, size = 17, sort = "username,asc") Pageable pageable) {

		System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getSort());

		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}

	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@ApiParam("用户id") @PathVariable String id) {
//		throw new RuntimeException("user not exist");
		System.out.println("进入getInfo服务");
		User user = new User();
		user.setUsername("tom");
		return user;
	}

}

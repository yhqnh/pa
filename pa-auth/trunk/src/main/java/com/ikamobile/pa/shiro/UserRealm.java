package com.ikamobile.pa.shiro;

import com.ikamobile.pa.thrift.client.ShiroServiceClientProxy;
import com.ikamobile.pa.thrift.server.acceptor.PaUserDto;
import com.ikamobile.pa.thrift.server.acceptor.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.thrift.TException;
import org.springframework.util.StringUtils;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/6.
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    private ShiroServiceClientProxy shiroServiceClientProxy;

    public void setShiroServiceClientProxy(ShiroServiceClientProxy shiroServiceClientProxy) {
        this.shiroServiceClientProxy = shiroServiceClientProxy;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        if (username == null) {
            throw new AccountException("用户名不能为空");
        }

        AuthenticationInfo info = null;
        PaUserDto paUserDto = null;
        try {
            paUserDto = getPaUserDto(username);
        }catch (TException te){
            log.error("获取用户信息",te);
            throw new RuntimeException("获取用户信息错误");
        }

        if (StringUtils.isEmpty(paUserDto)) {
            throw new UnknownAccountException("账号不存在[" + username + "]");
        }

        String password = paUserDto.getPasswd();
        SimpleAuthenticationInfo saInfo = new SimpleAuthenticationInfo(paUserDto, password, getName());

        saInfo.setCredentialsSalt(ByteSource.Util.bytes(paUserDto.getSalt()));


        info = saInfo;
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        PaUserDto paUserDto = (PaUserDto) principals.getPrimaryPrincipal();
        if( !StringUtils.isEmpty(paUserDto) ) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //添加用户权限
//            for( Role role : user.getRoles() ) {
//                info.addRole(role.getName());
//                info.addStringPermissions( role.getPermissions() );
//            }
            return info;
        } else {
            return null;
        }
    }

    private PaUserDto getPaUserDto(String username) throws TException{
        PaUserDto paUserDto = shiroServiceClientProxy.createProxy().getPaUser(username);
        if(StringUtils.isEmpty(paUserDto)){
            return null;
        }
        return paUserDto;
    }
}

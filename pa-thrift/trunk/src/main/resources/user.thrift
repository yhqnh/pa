include "common.thrift"
namespace java com.ikamobile.pa.thrift.server.acceptor

struct UserDto {
    // 主键id
    1: string id,
    //手机号
    2: string phoneNumber,
    //微信开放平台id
    3: string weixinOpenId,
    // 设备序列号
    4: string deviceNo,

}

struct UserParam {
    // 主键id
    1: string id,
    //手机号
    2: string phoneNumber,
    //微信开放平台id
    3: string weixinOpenId,
    // 密码
    4: string password,
    // 设备序列号
    5: string deviceNo,
}

service UserThriftService{

    UserDto get(1: string phone) throws (1: common.TBusinessException bussinessException)

    UserDto login(1: string phoneNumber,2: string verifyCode) throws (1: common.TBusinessException bussinessException)
}
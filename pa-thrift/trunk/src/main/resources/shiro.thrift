include "common.thrift"
namespace java com.ikamobile.pa.thrift.server.acceptor

enum PaUserType {
   dispatcher = 1,
   user = 2,
   driver = 3
}

struct PaUserMiniDto {
    // 主键id
    1: string id,
    // 用户名
    2: string username,
    // 用户类型
    3: PaUserType paUserType,
}

struct PaUserDto {
    // 主键id
    1: string id,
    // 用户名
    2: string username,
    // 密码
    3: string passwd,
    // 调料
    4: string salt,
    // 用户类型
    5: PaUserType paUserType,
}

service ShiroThriftService{

    PaUserDto getPaUser(1: string username) throws (1: common.TBusinessException bussinessException)
}
include "common.thrift"
namespace java com.ikamobile.pa.thrift.server.acceptor



struct DispatcherParam{
    /**
     * uuid id
     */    
    1: string id,
    /**
     * 用户名
     */
    2: string loginName,
    /**
     * 手机号
     */
    3: string phoneNumber,
    /**
     * 密码
     */
    4: string password,
}

struct DispatcherDto {
    /**
     * uuid id
     */
    1: string id,
    /**
     * 用户名
     */
    2: string loginName,
    /**
     * 手机号
     */
    3: string phoneNumber,
    /**
     * 密码
     */
    4: string password,
}

service DispatcherThriftService {
    list<DispatcherDto> getAll() throws (1: common.TBusinessException bussinessException)
}
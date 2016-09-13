include "common.thrift"
namespace java com.ikamobile.pa.thrift.server.acceptor

service VerityCodeThriftService{

    /**
    * 获取验证码
    **/
    common.OperateResponse get(1: string phoneNumber,2: string type) throws (1: common.TBusinessException bussinessException)

    /**
    * 验证码是否合法
    **/
    common.OperateResponse isValid(1: string phoneNumber,2: string type,3: string varityCode) throws (1: common.TBusinessException bussinessException)
}
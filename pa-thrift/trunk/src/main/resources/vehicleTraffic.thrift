include "common.thrift"
include "driver.thrift"
namespace java com.ikamobile.pa.thrift.server.acceptor

service VehicleTrafficThriftService{

    /**
    * 更新车辆状态
    **/
    common.OperateResponse refreshTraffic() throws (1: common.TBusinessException bussinessException)
}
include "common.thrift"
include "driver.thrift"
namespace java com.ikamobile.pa.thrift.server.acceptor

enum VehicleType {
    /**
     * MEET-接机，TRANSPORT-送机
    **/
    MEET = 1,TRANSPORT = 2,
}

enum VehicleStatus {
    /**
     * ENABLE-可用，DISABLE-不可用
    **/
    ENABLE = 1,DISABLE = 2,
}

struct VehicleUpdateParam{
    /**
     *  id
     */
    1: string id,
    /**
     * MEET-接机，TRANSPORT-送机
     */
    2: VehicleType type,
    /**
    * 状态(enable-可用，disable-不可用)
    **/
    3:VehicleStatus status,
}

struct VehicleDto{
    /**
     *  id
     */
    1: string id,
    /**
     * 自编码
     */
    2: string code,
    /**
     * 车牌号
     */
    3: string number,
    /**
     * MEET-接机，TRANSPORT-送机
     */
    4: VehicleType type,
    /**
     * 座位数
     */
    5: i32 seats,
    /**
    * 已坐人数
    **/
    6: i32 tokenCount,
    /**
    * 司机
    **/
    7:driver.DriverDto driverDto,
    /**
    * 状态
    **/
    8:VehicleStatus status,
}

struct FilterDto {
    /**
     * 自编码
     */
    1: string code,
    /**送机
     * MEET-接机，TRANSPORT-送机
     */
    2: VehicleType type,
    /**
    * 状态(enable-可用，disable-不可用)
    **/
    3:VehicleStatus status,
}

struct VehiclePagerDto {
    1:common.PagerInfoDto pagerInfoDto,

    2:list<VehicleDto> vehicleDtoList,
}

service VehicleThriftService{

    /**
    * 获取待派车辆
    **/
    list<VehicleDto> findWaitSendList(1: i64 expectBoardTime) throws (1: common.TBusinessException bussinessException)

    /**
    * 获取所有车辆
    **/
    VehiclePagerDto getAllList(1:common.PagerDto pagerDto) throws (1: common.TBusinessException bussinessException)


    /**
    * 根据过滤条件获取车辆
    **/
    VehiclePagerDto getFilterList(1:common.PagerDto pagerDto,2:FilterDto filterDto) throws (1: common.TBusinessException bussinessException)

    /**
    * 更新车辆
    **/
    common.OperateResponse update(VehicleUpdateParam vehicleUpdateParam) throws (1: common.TBusinessException bussinessException)
}
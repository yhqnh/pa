include "common.thrift"
include "user.thrift"
include "position.thrift"
include "vehicle.thrift"
include "driver.thrift"
namespace java com.ikamobile.pa.thrift.server.acceptor

struct PassengerDto {
    1: string id,
    /**
     * 姓名
     */
    2: string name,
    /**
     * 性别（0-未知，1-男，2-女） todo 换枚举
     */
    3: i32 gender,
    /**
     * 证件类型（NI-身份证）
     */
    4: string certificateType,

    //增加类型描述
    5: string certificateTypeDesc,
    /**
     * 证件号码
     */
    6: string certificateNumber,
    /**
     * 生日
     */
    7: string birthday,
    /**
     * 订单关联id
     */
    8: string orderId,
    /**
     * 年龄
     */
    9: i32 age,
}

struct OrderDto {

}


struct OrderDetailDto {
    // 主键id
    1: string id,
    // 自编码
    2: string code,
    // 状态信息 todo 改枚举
    3: string status,
    // 预订人信息
    4: user.UserDto booker,
    // 乘客信息
    5: list<PassengerDto> passengers,
    // 上车地点
    6: position.PositionDto aboardPosition,
    //抵达地点
    7: position.PositionDto arrPosition,
    //航班号
    8: string flightNumber,
    //航班起飞时间
    9: i64 flightDepTime,
    //车辆信息
    10: vehicle.VehicleDto vehicle,
    //司机信息
    11: driver.DriverDto driver,
    //是否是线上订单
    12: bool online,
    //铁航接送单单号
    13: string voucherCode,
    //预计上车时间
    14: i64 expectBoardTime,
    //确切上车时间
    15: i64 actualBoardTime,
    //抵达机场时间
    16: i64 arrTime,
    //下单时间
    17: i64 createTime,
    //操作日志
    18: list<common.OperateLogDto> operateLogs,
    //操作人姓名
    19: string operatorName,

    
}

struct OrderCreateParam{
        /**
         * 预订人手机号
         */
        1: string mobile,
    
        /**
         * 乘客信息 <b>至少上传 姓名、证件类型、证件号码</b>
         */
        2: list<PassengerDto> passengers,
    
        /**
         * 上车地点
         */
        3: position.PositionDto aboardPosition,
    
        /**
         * 下车地点
         */
        4: position.PositionDto arrPosition,
    
        /**
         * 航班号
         */
        5: string flightNumber,
    
        /**
         * 航班起飞时间
         */
        6: i64 flightDepTime,
    
        /**
         * 铁航接送单单号 <b>非必填</b>
         */
        7: string voucherCode,
        /**
         * 是否是线上单
         **/
        8: bool isOnline = true,
        /**
        * 操作人id 用于记录线下创建订单的操作员
        **/
        9: string operatorId,
}

struct OrderQuerySorterEnum {
    1: common.Sorter createTime,
    2: common.Sorter flightDepTime,
    3: common.Sorter areaId,
}

struct OrderSearchParam{
    // 主键id
    1: string id,
    // 自编码
    2: string code,
    // 状态信息
    3: list<string> status,
    // 预订人手机号
    4: string bookerMobile,
    //航班号
    5: string flightNumber,
    //航班起飞时间下限
    6: i64 flightDepTimeFloor,
    //航班起飞时间上限
    7: i64 flightDepTimeTop,
    //是否是线上订单
    8: string forOnline,
    //铁航接送单单号
    9: string voucherCode,
    //下单时间 下限
    10: i64 createTimeFloor,
    // 下单时间 上限
    11: i64 createTimeTop,
    //操作人Id
    12: string operatorId,
    //车辆自编号
    13: string vehicleCode,
}

struct OrderQueryParam {

    //分页相关信息
    1:common.PagerDto pager,
    //排序规则
    2:OrderQuerySorterEnum sorter,
    //搜索条件
    3:OrderSearchParam searchParam,
}
struct DispatchParamDto{
    //客服确认的上车地点
    1: position.PositionDto aboardPosition,
    // 飞机起飞时间
    2: i64 flightDepTime,
    // 预计上车时间
    3: i64 expectBoardTime,
    // 车辆自编号
    4: string vehicleCode,
    // 订单id
    5: string orderId,

}


struct OrderPageResponse{
    // 分页信息
    1:common.PagerInfoDto pagerInfo,
    // 数据列表
    2: list<OrderDetailDto> pageContent,
}

service ThriftOrderService{

    /**
     * 创建订单
     * @param param 订单创建参数
     **/
    OrderDetailDto create(1: OrderCreateParam param) throws (1: common.TBusinessException bussinessException),

    /**
    * 订单查询接口
    * @param param 查询订单列表参数
    **/
    OrderPageResponse listOrder(1:OrderQueryParam param) throws (1: common.TBusinessException bussinessException),


    /**
    * 获取指定订单详情
    * @param orderId 订单id
    **/
    OrderDetailDto orderDetail(1:string orderId),


    /**
    * 取消订单 改接口client项目用，
    * @param orderId 订单id
    * @param mobile 预订人手机号
    **/
    common.OperateResponse cancelOrder(1: string orderId,2: string mobile)  throws (1: common.TBusinessException bussinessException),

    /**
    * 调度者取消订单
    * @param orderId 订单id
    * @param operatorId 预订人手机号
    **/
    common.OperateResponse dispactherCancelOrder(1: string orderId,2: string operatorId)  throws (1: common.TBusinessException bussinessException),

    /**
    * 司机确认某个订单的乘客 已上车
    * @param orderId 订单id
    **/
    common.OperateResponse driverConfirmPassengerAboarded(1: string orderId,2: string driverId)  throws (1: common.TBusinessException bussinessException),
    /**
     * 通过验证，接受订单
     * @param orderId目标订单id
     **/
    common.OperateResponse accept(1: string orderId,2: string operatorId)  throws (1: common.TBusinessException bussinessException),

    /**
     * 验证未通过，拒绝订单
     * @param orderId目标订单id
     **/
    common.OperateResponse deny(1: string orderId ,2: string operatorId)  throws (1: common.TBusinessException bussinessException),

    /**
     * 分配车辆给订单
     * @param 分配车辆的参数
     **/
    common.OperateResponse dispatch(1: DispatchParamDto param ,2: string operatorId)  throws (1: common.TBusinessException bussinessException),

    /**
     * 分配车辆给订单
     * @param 分配车辆的参数
     **/
    common.OperateResponse unDispatch(1: string orderId,2: string taskId,3: string operatorId)  throws (1: common.TBusinessException bussinessException),


    /**
     * 订单锁定
     * @param 分配车辆的参数
     **/
    common.OperateResponse lockOrder(1: string orderId,2: string operatorId)  throws (1: common.TBusinessException bussinessException),


    /**
     * 订单解锁
     * @param 分配车辆的参数
     **/
    common.OperateResponse unlock(1: string orderId,2: string operatorId)  throws (1: common.TBusinessException bussinessException),
}
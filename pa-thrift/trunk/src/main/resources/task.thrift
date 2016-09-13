include "common.thrift"
include "user.thrift"
include "position.thrift"
include "order.thrift"
include "driver.thrift"
include "vehicle.thrift"
namespace java com.ikamobile.pa.thrift.server.acceptor

struct TaskDetailDto{
/**
     * 任务ID
     */
    1: string id,
    /**
     * 任务编号
     */
    2: string code,
    /**
     * 状态 1-未开始 2-进行中 3-已结束
     */
    3: i32 status,
    /**
     * 司机信息
     */
    4: driver.DriverDto driver,
    /**
     * 分配的车辆信息
     */
    5: vehicle.VehicleDto vehicle,
    /**
     * 乘客数量
     */
    6: i32 psgCount,
    /**
     * 最早起飞时间
     */
    7: i64 earlistFlightTime,
    /**
     * 任务开始时间
     */
    8: i64 startTime,
    /**
     * 任务完成时间
     */
    9: i64 finishTime,
    /**
     * 创建时间
     */
    10: i64 createTime,
    /**
     * 更新时间
     */
    11: i64 updateTime,

    /**
     * 任务中的订单详情
     */
    12: list<order.OrderDetailDto> orders,

}

struct TaskListDto{
    /**
     * id
     */
    1: string id,

    /**
     * code
     */
    2: string code,

    /**
     * 状态
     */
    3: string status,

    /**
     * 订单数量
     */
    4: i32 orderCount,

    /**
     * 人员数量
     */
    5: i32 passageCount,

    /**
     * 最早起飞时间
     */
    6: i64 earliestFlightTime,

    /**
     * 任务开始时间
     */
    7: i64 startTime,

    /**
     * 完成时间
     */
    8: i64 finishTime,

    /**
     * 车牌号码
     */
    9: string vehicleNo,

    /**
     * 车辆ID
     */
    10: string vehicleId,

    /**
     * 司机名称
     */
    11: string driverName,

    /**
     * 司机ID
     */
    12: string driverId,

    /**
     * 司机联系电话
     */
    13: string driverTel,

     /**
     * 司机其他联系电话
     */
    14: string driverOtherTel,
}

struct TaskListResponse{
    //分页信息
    1:common.PagerInfoDto pager,
    //列表数据
    2:list<TaskListDto> data,

}

service TaskThriftService{
    /**
    *   获取任务详情
    **/
    TaskDetailDto getTaskDetail(1:string taskId)  throws (1: common.TBusinessException bussinessException),

    /**
    *   获取车辆当前任务
    **/
    TaskDetailDto getDriverCurrentTask(1:string driverId) throws (1: common.TBusinessException bussinessException),

    /**
    *   获取为开始任务列表
    **/
    TaskListResponse getPendingList(1:common.PagerDto param,2:string operatorId) throws (1: common.TBusinessException bussinessException),

    /**
    * 获取为开始任务数量
    **/
    i32 getPendingCount() throws (1: common.TBusinessException bussinessException),

    /**
    * 开始进行任务
    **/
    common.OperateResponse startTask(1:string taskId,2:string operatorId,3:i64 updateTime) throws (1: common.TBusinessException bussinessException),
    /**
    * 完成任务，全部送达
    **/
    common.OperateResponse finishTask(1:string taskId,2:string driverId) throws (1: common.TBusinessException bussinessException),
    /**
    * 确认orderId的订单乘客上车
    **/
    common.OperateResponse confirmPassengerAboard(1:string orderId,2:string operatorDirverId)throws (1: common.TBusinessException bussinessException),

    /**
    * 确认orderId的订单乘客miss
    **/
    common.OperateResponse confirmPassengerMiss(1:string orderId,2:string operatorDirverId)throws (1: common.TBusinessException bussinessException),

}
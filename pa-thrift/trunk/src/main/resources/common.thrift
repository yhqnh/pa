namespace java com.ikamobile.pa.thrift.common


/**
 * <h2>PA Thrift业务异常</h2><br />
 */
exception TBusinessException {
    1: required i32 code,
    2: string message,
}

struct PagerDto {
    1: i32 pageIndex,
    2: i32 pageSize,
}

struct PagerInfoDto {
    // 本页获取条数
    1: i32 pageIndex,
    // 每页总条数
    2: i32 pageSize,
    // 总共数据条数
    3: i32 totalPageNum,
    // 总共数据条数
    4: i32 totalRowNum,
}



struct OperateLogDto{
    /**
     * 主键
     */
     1: string id,
    /**
     * order：订单  task：任务
     */
     2: string targetType,
    /**
     * 目标ID
     */
     3: string targetId,
    /**
     * 操作人员名称
     */
     4: string operator,
    /**
     * 操作内容
     */
     5: string operation,
    /**
     * 日志显示级别 0:调度可见，1：司机可见，2：乘客可见
     */
     6: i32 level,
    /**
     * 创建时间
     */
     7: i64 createTime,
}

enum OperateCode {
    success = 0,fail = -1
}

/**
 * <h2>PA通用操作返回结果</h2><br />
 */
struct OperateResponse{
    /** success  **/
    1: OperateCode operateCode = OperateCode.success,
    /** 消息  **/
    2:string message = "操作成功",
}
/**
* <h2>排序枚举</h2><br/>
**/
enum Sorter {
    //正序
    ASC = 1,
    //到序
    DESC = 2
}


enum SystemEnum{

}
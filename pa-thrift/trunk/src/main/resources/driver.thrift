include "common.thrift"
namespace java com.ikamobile.pa.thrift.server.acceptor



 struct DriverDto {

    /**
     *
     * db_column: id
     */
    1: string id,
    /**
     * 登陆名
     * db_column: login_name
     */
    2: string loginName,
    /**
     * 密码
     * db_column: password
     */
    3: string password,
    /**
     * 姓名
     * db_column: name
     */
    4: string name,
    /**
     * 手机号
     * db_column: phone_number
     */
    5: string phoneNumber,
    /**
     * 其他联系号码
     * db_column: phone_number_other
     */
    6: string phoneNumberOther,
    /**
     * 所在城市
     * db_column: city_id
     */
    7: string cityId,
    /**
     * 所在区
     * db_column: area
     */
    8: string area,
    /**
     * 性别
     * db_column: sex
     */
    9: string sex,
    /**
     * 年龄
     * db_column: age
     */
    10: i32 age,
    /**
     * 调料
     * db_column: age
     */
    11: string salt,

}

service DriverThriftService {
    DriverDto login(1: string phoneNumber,2: string password) throws (1: common.TBusinessException bussinessException)
}

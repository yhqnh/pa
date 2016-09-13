include "common.thrift"
namespace java com.ikamobile.pa.thrift.server.acceptor
/**
 * 位置交换系统 thrift定义
 **/
struct PositionDto {
    /**
     * 经度
     */
    1: double longitude,
    /**
     * 上车地点维度
     */
    2: double latitude,
    /**
     * 所属区域id
     */
    3: AreaDto area,
    /**
     * 地点描述
     */
    4: string positionDesc,
}

struct AreaDto {

    1: string id,
    /**
     * 地点描述
     */
    3: string name,
    /**
     * 地点描述
     */
    4: string code,
}

service PositionThriftService{
    /*
    * 获取区域所在城市的所有列表
    * @param areaCode 当前定位区域code
    */
    list<AreaDto> getAreas(1:string areaCode),
    /*
    * 获取城市的所有区域列表
    * @param areaCode 当前定位区域code
    */
    list<AreaDto> getAreasByCityId(1:string cityId);

    list<AreaDto> getAllArea();

}
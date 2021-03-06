package com.suixingpay.mapper;

import com.suixingpay.pojo.PatentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 詹文良
 * @program: patent-pool-3th
 * @description: 专利信息表的 mapper 接口
 * <p>
 * Created by jalr on 2019/11/21.
 */

@Mapper
public interface PatentInfoMapper {
    /**
     * 插入专利信息服务
     *
     * @param patentInfo 专利信息实体
     * @return 0 插入失败，1 插入成功
     */
    Integer insertPatentInfo(PatentInfo patentInfo);

    /**
     * 模糊查询专利，为搜索专利提供的服务
     *
     * @param patentInfo 专利信息实体
     * @return 模糊搜索专利的实体集合，如果为空就返回一个空的 LIST
     */
    List<PatentInfo> selectPatentFuzzy(PatentInfo patentInfo);

    /**
     * 根据专利的 id, 更新专利的各种信息
     *
     * @param patentInfo 专利信息实体
     * @return 0 表示更新失败, 1 表示更新成功
     */
    Integer updatePatentInfoById(PatentInfo patentInfo);

    /**
     * 专利池的初始化查询，返回已被领取的专利
     * @param patentInfo 专利实体
     * @return 专利实体集合或空 LIST
     */
    List<PatentInfo> selectPatentPoolReceive(PatentInfo patentInfo);

    /**
     * 专利池的初始化，返回未被领取的专利
     * @return 专利实体集合或空 LIST
     */
    List<PatentInfo> selectPatentPoolNoReceive();

    /**
     * 任意用户动态判断的是否需要认领者限制的已认领专利搜索页的模糊查询
     * @param patentInfo 专利信息实体
     * @return 不空模糊搜索专利的实体集合，如果为空就返回一个空的 LIST
     */
    List<PatentInfo> selectPatentAnyUserReceive(PatentInfo patentInfo);

    /**
     * 任意用户的未认领专利搜索页的模糊搜索，一个条件，并加上日期排序
     * 1. 专利状态未认领 （current_status = 2)
     *
     * @param patentInfo 专利信息实体
     * @return 不空模糊搜索专利的实体集合，如果为空就返回一个空的 LIST
     */
    List<PatentInfo> selectPatentAllUserAndNoReceive(PatentInfo patentInfo);

    /**
     * 传入指定的状态码集合以及用户 id 来查询该范围内的专利信息记录，这是为张提供的服务 mapper
     *
     * @param currentStatusList 传入的状态码集合
     * @param userId            转入的用户 id
     * @return 搜索专利的实体集合，如果为空就返回一个空的 LIST
     */
    List<PatentInfo> selectPatentByStatusList(@Param("currentStatusList") List<Integer> currentStatusList,
                                              @Param("userId") Integer userId);

}

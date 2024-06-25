package com.itheima.service;
import com.itheima.domain.Record;
import entity.PageResult;

/**
 * 停泊记录接口
 */
public interface RecordService {
    //新增停泊记录
    Integer addRecord(Record record);
//查询停泊记录
PageResult searchRecords(Record record, Integer pageNum, Integer pageSize);
}

package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.domain.Record;
import com.itheima.mapper.RecordMapper;
import com.itheima.service.RecordService;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;

/**
 * 新增借阅记录
 * @param record 新增的借阅记录
 */
@Override
public Integer addRecord(Record record) {
    return recordMapper.addRecord(record);
}

/**
 * 查询借阅记录
 *
 * @param record   停泊记录的查询条件
 * @param pageNum  当前页码
 * @param pageSize 每页显示数量
 */
@Override
public PageResult searchRecords(Record record, Integer pageNum, Integer pageSize) {
    // 设置分页查询的参数，开始分页
    PageHelper.startPage(pageNum, pageSize);
    Page<Record> page= recordMapper.searchRecords(record);
    return new PageResult(page.getTotal(),page.getResult());
}

}

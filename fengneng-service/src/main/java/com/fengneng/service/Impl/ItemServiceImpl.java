package com.fengneng.service.Impl;

import com.fengneng.mapper.TbItemMapper;
import com.fengneng.pojo.TbItem;
import com.fengneng.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liuxu on 2018/5/20.
 */
@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem findItemById(long itemId) {
        return itemMapper.selectByPrimaryKey(itemId);
    }
}

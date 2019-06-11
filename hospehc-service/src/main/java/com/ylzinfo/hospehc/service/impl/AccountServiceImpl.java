package com.ylzinfo.hospehc.service.impl;



import com.alibaba.dubbo.config.annotation.Service;
import com.ylzinfo.hospehc.dao.AccountDao;
import com.ylzinfo.hospehc.pojo.Account;
import com.ylzinfo.hospehc.service.AccountService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Account)表服务实现类
 *
 * @author lmr
 * @since 2019-01-08 21:11:12
 */
@Component
//必须指定interfaceName，否则aop会导致dubbo消费端获取失败
@Service(version = "1.0.0",interfaceName = "com.ylzinfo.hospehc.service.AccountService",filter = "traceIdFilter")
public class AccountServiceImpl implements AccountService {
    @Resource
    private AccountDao accountDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Account queryById(Integer id) {
        return this.accountDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Account> queryAllByLimit(int offset, int limit) {
        return this.accountDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @Override
    public Account insert(Account account) {
        this.accountDao.insert(account);
        return account;
    }

    /**
     * 修改数据
     *
     * @param account 实例对象
     * @return 实例对象
     */
    @Override
    public Account update(Account account) {
        this.accountDao.update(account);
        return this.queryById(account.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.accountDao.deleteById(id) > 0;
    }

    @Override
    public Account login(String tno, String password) {
        return this.accountDao.queryByAccount(tno,password);
    }

}
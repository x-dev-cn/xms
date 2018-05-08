package cn.xdev.auth.vip.service.impl;

import cn.xdev.auth.vip.dao.SysVipMapper;
import cn.xdev.auth.vip.model.SysVip;
import cn.xdev.auth.vip.model.SysVipExample;
import cn.xdev.auth.vip.service.SysVipService;
import cn.xdev.auth.vo.datatables.DTParameter;
import cn.xdev.auth.vo.datatables.DTResult;
import cn.xdev.core.generic.GenericDao;
import cn.xdev.core.generic.GenericServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Felix.Hsu (felix@x-dev.cn)
 * @Date 2017/9/1910:18
 */
@Service
public class SysVipServiceImpl extends GenericServiceImpl<SysVip, Integer> implements SysVipService {

    @Resource
    private SysVipMapper sysVipMapper;

    @Override
    public SysVip authentication(SysVip user) {
        SysVip vip = selectByMobile(user.getMobile());
        if (vip != null) {
            user.setPassword(new Md5Hash(user.getPassword(), vip.getCredentialsSalt(), 2).toString());
            return sysVipMapper.authentication(user);
        }
        return null;
    }

    @Override
    public SysVip selectByMobile(String mobile) {
        SysVipExample example = new SysVipExample();
        example.createCriteria().andMobileEqualTo(mobile);
        final List<SysVip> list = sysVipMapper.selectByExample(example);
        if (list.size() > 0)
            return list.get(0);
        else
            return new SysVip();
    }

    @Override
    public SysVip createUser(SysVip vip) {
        sysVipMapper.insertSelective(vip);
        return vip;
    }

    @Override
    public SysVip updateUser(SysVip vip) {
        sysVipMapper.updateByPrimaryKeySelective(vip);
        return vip;
    }

    @Override
    public void deleteUser(Integer vipId) {
        sysVipMapper.deleteByPrimaryKey(vipId);
    }

    @Override
    public int changePassword(Integer userId, String oldPassword, String newPassword) {
        SysVip user = sysVipMapper.selectByPrimaryKey(userId);
        if (new Md5Hash(oldPassword, user.getCredentialsSalt(), 2).toString().equals(user.getPassword())) {
            user.setPassword(new Md5Hash(newPassword, user.getCredentialsSalt(), 2).toString());
            return sysVipMapper.updateByPrimaryKeySelective(user);
        } else {
            return -1;
        }
    }

    @Override
    public SysVip findOne(Integer userId) {
        SysVipExample example = new SysVipExample();
        example.createCriteria().andIdEqualTo(userId);
        final List<SysVip> list = sysVipMapper.selectByExample(example);
        if (list.size() > 0)
            return list.get(0);
        else
            return new SysVip();
    }

    @Override
    public List<SysVip> findAll() {
        return sysVipMapper.selectByExample(new SysVipExample());
    }

    @Override
    public int updateByDisable(Integer id) {
        SysVip vip = new SysVip();
        vip.setId(id);
        vip.setLocked(false);
        return sysVipMapper.updateByPrimaryKeySelective(vip);
    }

    @Override
    public int updateByEnable(Integer id) {
        SysVip vip = new SysVip();
        vip.setId(id);
        vip.setLocked(true);
        return sysVipMapper.updateByPrimaryKeySelective(vip);
    }

    @Override
    public DTResult selectListByDataTables(DTParameter parameter, String mobile, boolean locked) {
        SysVipExample example = new SysVipExample();
        SysVipExample.Criteria criteria = example.createCriteria();

        if (!mobile.isEmpty()) {
            criteria.andMobileLike("%" + mobile + "%");
        }

        List<String> slist = new ArrayList<>();
        List<String> flist = new ArrayList<>();

        if (!parameter.getSearch().equals("")) {
            flist.add("mobile");
            slist.add(parameter.getSearch());

            flist.add("realname");
            slist.add(parameter.getSearch());

            criteria.multiColumnOrClauseLike(flist, slist);
        }

        PageHelper.startPage(parameter.getStart() / parameter.getLength() + 1, parameter.getLength());

        String order = "id desc";
        if (!parameter.getOrder_column().isEmpty() && !parameter.getOrder_dir().isEmpty()) {
            order = parameter.getOrder_column() + " " + parameter.getOrder_dir() + "," + order;
        }
        example.setOrderByClause(order);

        List<SysVip> list = sysVipMapper.selectByExample(example);
        PageInfo page = new PageInfo(list);

        DTResult result = new DTResult();
        result.setDraw(parameter.getDraw());
        result.setRecordsTotal(page.getTotal());
        result.setRecordsFiltered(page.getTotal());
        result.setData(list);

        return result;
    }

    @Override
    public GenericDao<SysVip, Integer> getDao() {
        return sysVipMapper;
    }
}

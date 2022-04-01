package com.qks.makerSpace.service.Impl;

import com.qks.makerSpace.dao.QuitDao;
import com.qks.makerSpace.entity.database.Quit;
import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.QuitService;
import com.qks.makerSpace.util.JWTUtils;
import com.qks.makerSpace.util.MyResponseUtil;
import io.jsonwebtoken.Jwt;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class QuitServiceImpl implements QuitService {

    public final QuitDao quitDao;

    public QuitServiceImpl(QuitDao quitDao) {
        this.quitDao = quitDao;
    }


    /**
     * 企业提交科技园退租审核
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> Quit(Map<String, Object> map) throws ServiceException {

        Quit quit = new Quit();
        quit.setId(UUID.randomUUID().toString());
        quit.setName(map.get("name").toString());
        quit.setTime(map.get("time").toString());
        quit.setRoom(map.get("room").toString());
        quit.setAgent(map.get("agent").toString());
        quit.setPhone(map.get("phone").toString());
        quit.setReason(map.get("reason").toString());
        quit.setRealTime(map.get("realTime").toString());
        quit.setUsername(map.get("username").toString());
        quit.setAccount(map.get("account").toString());
        quit.setOpeningBank(map.get("openingBank").toString());

        int describe = quitDao.selectDescribeByName(map.get("name").toString());
        if (describe == 0) throw new ServiceException("未查询到该企业名对应的账号");
        quit.setQuitDescribe(describe);
        quit.setAdminAudit("待审核");
        String time = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date());
        quit.setSubmitTime(time);

        if (quitDao.insertQuit(quit) > 0)
            return MyResponseUtil.getResultMap(null,0,"success");
        else
            throw new ServiceException("提交出错了，请重试");
    }

    /**
     * 获取某个企业最新的退租审核表（包含审核情况）
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> GetQuit(Map<String, Object> map) throws ServiceException {
        String name = map.get("name").toString();
        Quit quit = quitDao.selectQuitByName(name);
        if (quit != null) return MyResponseUtil.getResultMap(quit,0,"success");
        else throw new ServiceException("查询失败");
    }

    /**
     * 管理员获取所有未审核退租审核表
     * @param token
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> GetAllQuit(String token) throws ServiceException {
        if (!JWTUtils.parser(token).get("name").toString().equals("admin"))
            throw new ServiceException("请求主题错误");
        List<Quit> list = quitDao.getAllQuit();
        if (list.size() != 0)
            return MyResponseUtil.getResultMap(list,0,"success");
        else
            throw new ServiceException("暂时没有待审核的退租申请");
    }

    /**
     * 管理员获取获取所有审核表（按时间排序）
     * @param token
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> AllQuit(String token) throws ServiceException {
        List<Quit> list = quitDao.allQuit();
        if (list.size() != 0)
            return MyResponseUtil.getResultMap(list,0,"success");
        else
            throw new ServiceException("暂时没有退租申请");
    }

    /**
     * 管理员同意退租
     * @param token
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> AgreeQuit(String token, Map<String, Object> map) throws ServiceException {
        if (!JWTUtils.parser(token).get("name").toString().equals("admin"))
            throw new ServiceException("请求主题错误");
        String id = map.get("id").toString();
        String name = quitDao.selectNameById(id);
        String creditCode = quitDao.getCreditCode(name);
        int describe = quitDao.getDescribe(id);
        if (quitDao.agree(id) < 0 || quitDao.updateAlive(name) < 0)
            throw new ServiceException("跟新状态出错");

        if (describe == 2 || describe == 4) {
            if (quitDao.updateNewAlive(creditCode) < 0)
                throw new ServiceException("更新主表状态失败");
        }
        else if (describe == 3) {
            if (quitDao.updateOldAlive(creditCode) < 0)
                throw new ServiceException("更新旧主表状态失败");
        }
        else throw new ServiceException("出现了一些奇怪的问题");

        quitDao.updateFormAlive(creditCode);

        return MyResponseUtil.getResultMap(null,0,"success");
    }

    /**
     * 管理员不同意退租
     * @param token
     * @param map
     * @return
     * @throws ServiceException
     */
    @Override
    public Map<String, Object> DisagreeQuit(String token, Map<String, Object> map) throws ServiceException {

        if (!JWTUtils.parser(token).get("name").toString().equals("admin"))
            throw new ServiceException("请求主题错误");

        String id = map.get("id").toString();
        if (quitDao.disagree(id) > 0)
            return MyResponseUtil.getResultMap(null,0,"success");
        else throw new ServiceException("授权失败");


    }
}
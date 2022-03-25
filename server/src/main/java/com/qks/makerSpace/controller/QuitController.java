package com.qks.makerSpace.controller;

import com.qks.makerSpace.exception.ServiceException;
import com.qks.makerSpace.service.QuitService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class QuitController {

    public final QuitService quitService;

    public QuitController(QuitService quitService) {
        this.quitService = quitService;
    }

    /**
     * 企业提交科技园退租审核
     * @param map
     * @return
     */
    @RequestMapping(value = "user/quit", method = RequestMethod.POST)
    private Map<String, Object> Quit(@RequestBody Map<String, Object> map) throws ServiceException {
        return quitService.Quit(map);
    }

    /**
     * 获取某个企业最新的退租审核表（包含审核情况）
     * @param map
     * @return
     */
    @RequestMapping(value = "quit", method = RequestMethod.POST)
    private Map<String, Object> GetQuit(@RequestBody Map<String, Object> map) throws ServiceException {
        return quitService.GetQuit(map);
    }

    /**
     * 管理员获取所有未审核退租审核表
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "admin/quit", method = RequestMethod.GET)
    private Map<String, Object> GetAllQuit(HttpServletRequest httpServletRequest) throws ServiceException {
        return quitService.GetAllQuit(httpServletRequest.getHeader("token"));
    }

    /**
     * 管理员获取获取所有审核表（按时间排序）
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "admin/allQuit", method = RequestMethod.GET)
    private Map<String, Object> AllQuit(HttpServletRequest httpServletRequest) throws ServiceException {
        return quitService.AllQuit(httpServletRequest.getHeader("token"));
    }

    /**
     * 管理员同意退租
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/admin/agreeQuit", method = RequestMethod.GET)
    private Map<String, Object> AgreeQuit(HttpServletRequest httpServletRequest, @RequestBody Map<String, Object> map) throws ServiceException {
        return quitService.AgreeQuit(httpServletRequest.getHeader("token"), map);
    }

    /**
     * 管理员不同意退租
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/admin/disagreeQuit", method = RequestMethod.GET)
    private Map<String, Object> DisagreeQuit(HttpServletRequest httpServletRequest, @RequestBody Map<String, Object> map) throws ServiceException {
        return quitService.DisagreeQuit(httpServletRequest.getHeader("token"), map);
    }


}
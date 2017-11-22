package pers.cc.crm.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import pers.cc.crm.entity.CodeRules;
import pers.cc.crm.service.CodeRulesCategoryService;
import pers.cc.crm.service.CodeRulesService;
import pers.cc.crm.util.DateUtil;
import pers.cc.crm.util.PropertiesUtil;
import pers.cc.crm.util.ResopnseUtil;
import pers.cc.crm.util.StringUtil;

@Controller
@RequestMapping("/rule")
public class rulesController {

    private CodeRules codeRulesTemp;

    @Resource
    CodeRulesCategoryService codeRulesCategoryService;

    @Resource
    CodeRulesService codeRulesService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public void rules(
            /* @RequestBody JSONObject data, */HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            System.out.println("cc");
        }
        // 通过树顶点父id
        String topNodeParentId = PropertiesUtil.getValue("common.properties",
                "topNodeParentId");
        /*
         * List<CodeRulesCategory> categorieList = codeRulesCategoryService
         * .getCodeRulesCategoryWithParentId(topNodeParentId); String
         * topTreeNodeAuthCodeRulesCategoryId =
         * categorieList.get(0).getCodeRulesCategoryId();
         */
        JSONArray codeRulesTree = new JSONArray();
        if (StringUtil.isNotEmpty(topNodeParentId)) {
            codeRulesTree = codeRulesCategoryService
                    .getCodeRulesCategoryTreeWithParentId(topNodeParentId);
        }
        // 将menuTree放入全局变量
        request.getSession().getServletContext().setAttribute("codeRulesTree",
                codeRulesTree);
        JSONObject result = new JSONObject();
        result.put("jsp", "/admin/rule/edit.jsp");
        ResopnseUtil.write(response, result);
    }

    @RequestMapping(value = "/addRule", method = RequestMethod.POST)
    public void addRules(CodeRules codeRules, HttpServletResponse response,
            HttpServletRequest request) throws Exception {
        JSONObject result = new JSONObject();
        String rulesId = UUID.randomUUID().toString().replaceAll("-", "")
                .toUpperCase();
        codeRules.setRulesId(rulesId);
        codeRules.setCreatTIme(DateUtil.getNowDateyyyyMMdd());
        List<String> codeRulesNameList = codeRulesService.getAllCodeRulesName();
        if (codeRulesNameList.contains(codeRules.getRulesName())) {
            result.put("error", "编码名称已存在!");
            ResopnseUtil.write(response, result);
        }
        codeRulesTemp = codeRules;
        request.getSession().getServletContext().setAttribute("segmentQuantity",
                codeRules.getSegmentQuantity());
        result.put("success", "请插入编码规则!");
        ResopnseUtil.write(response, result);
    }

    @RequestMapping(value = "/addSegment", method = RequestMethod.POST, consumes = "application/json")
    public void addRules(@RequestBody JSONArray data,
            HttpServletResponse response, HttpServletRequest request)
            throws Exception {
        JSONObject result = new JSONObject();
        System.out.println(data.size());
        String segmentId = UUID.randomUUID().toString().replaceAll("-", "")
                .toUpperCase();
        try {
            codeRulesService.addCodeRules(codeRulesTemp);
        } catch (Exception e) {
            result.put("error", "插入编码规则失败!");
            System.out.println(e.getMessage());
            e.printStackTrace();
            ResopnseUtil.write(response, result);
        }

        result.put("success", "插入编码段位成功!");
        ResopnseUtil.write(response, result);
    }
}

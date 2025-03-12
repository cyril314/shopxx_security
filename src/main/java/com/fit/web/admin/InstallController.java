package com.fit.web.admin;

import com.fit.base.BaseController;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/1/10
 */
@Controller
@RequestMapping("/admin/install")
public class InstallController extends BaseController {

    @RequestMapping("/license")
    public String license(HttpServletRequest req) {
        if (isInstalled()) {
            req.setAttribute("errorMessages", "SHOP++已完成安装，请勿重复操作！");
            return ERROR;
        }
        return "license";
    }

    // 检测是否已安装
    private boolean isInstalled() {
        try {
            String systemConfigFilePath = getClass().getResource("/shopxx.xml").getPath();
            File systemConfigFile = new File(systemConfigFilePath);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(systemConfigFile);
            Node isInstalledNode = document.selectSingleNode("/shopxx/systemConfig/isInstalled");
            if (isInstalledNode != null && isInstalledNode.getText().equalsIgnoreCase("false")) {
                return false;
            }
        } catch (Exception e) {
        }
        return true;
    }
}

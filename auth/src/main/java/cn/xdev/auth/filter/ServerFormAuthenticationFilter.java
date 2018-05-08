package cn.xdev.auth.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>User: Felix.Hsu
 * <p>Date: 14-3-16
 * <p>Version: 1.0
 */
public class ServerFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        String fallbackUrl = (String) getSubject(request, response)
                .getSession().getAttribute("authc.fallbackUrl");
        if (StringUtils.isEmpty(fallbackUrl)) {
            fallbackUrl = getSuccessUrl();
        }
        WebUtils.redirectToSavedRequest(request, response, fallbackUrl);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        Subject subject = getSubject(request, response);
        return subject.isAuthenticated();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String backUrl = httpRequest.getRequestURL().toString();//request.getParameter("backUrl");
        if (httpRequest.getQueryString() != null && !httpRequest.getQueryString().isEmpty()) {
            backUrl += "?" + httpRequest.getQueryString();
        }
        saveRequest(request, backUrl, getDefaultBackUrl(WebUtils.toHttp(request)));
        redirectToLogin(request, response);
        return false;
    }

    protected void saveRequest(ServletRequest request, String backUrl, String fallbackUrl) {
        String code = ServletRequestUtils.getStringParameter(request, "code", "");
//        if (!code.equals("")) {
//            PropertiesUtil propertiesUtil = new PropertiesUtil("/mp.properties");
//            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + propertiesUtil.getProperty("appId") +
//                    "&secret=" + propertiesUtil.getProperty("appSecret") + "&code=" + code + "&grant_type=authorization_code";
//            JSONObject json = HttpUtils.loadJSON(url);
//            SecurityUtils.getSubject().getSession().setAttribute("wechat.openid", json.get("openid"));
//        }

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        session.setAttribute("authc.fallbackUrl", fallbackUrl);
        session.setAttribute("authc.backUrl", backUrl);
        SavedRequest savedRequest = new SavedRequest(httpRequest);
        session.setAttribute(WebUtils.SAVED_REQUEST_KEY, savedRequest);
    }

    private String getDefaultBackUrl(HttpServletRequest request) {
        String scheme = request.getScheme();
        String domain = request.getServerName();
        int port = request.getServerPort();
        String contextPath = request.getContextPath();
        StringBuilder backUrl = new StringBuilder(scheme);
        backUrl.append("://");
        backUrl.append(domain);
        if ("http".equalsIgnoreCase(scheme) && port != 80) {
            backUrl.append(":").append(String.valueOf(port));
        } else if ("https".equalsIgnoreCase(scheme) && port != 443) {
            backUrl.append(":").append(String.valueOf(port));
        }
        backUrl.append(contextPath);
        backUrl.append(getSuccessUrl());
        return backUrl.toString();
    }

}

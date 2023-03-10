package com.nwu.graduationalbum.util;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: NwuGraduationAlbum
 * @description: CAS工具类
 * @author: TD.Miracle
 * @create: 2022-05-12 12:17
 **/
public class CASUtil {
    public static String getAccountNameFromCas(HttpServletRequest request) {
        Assertion assertion = (Assertion) request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        if (assertion != null) {
            AttributePrincipal principal = assertion.getPrincipal();
            return principal.getName();
        } else return null;
    }
}

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/1/11
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row">
    <div class="btn-row">
        <div class="col-lg-4 col-md-5 col-sm-4 col-xs-3">
        </div>

        <div class="btn-toolbar col-lg-5 col-md-5 col-sm-6 col-xs-7">
            <div class="btn-group">
                <button class="btn btn-primary"  onclick="window.location.href='${param.requestUri}?pageNo=${param.prePage}${param.conditions}'">上一页</button>
            </div>
            <div class="btn-group">
                <c:forEach begin="1" end="${param.pages}" var="i">
                    <c:if test="${i==param.pageNum}">
                        <button class="btn btn-primary active" type="button" onclick="window.location.href='${param.requestUri}?pageNo=${i}${param.conditions}'">${param.pageNum}</button>
                    </c:if>
                    <c:if test="${i!=param.pageNum}">
                        <button class="btn btn-primary" type="button" onclick="window.location.href='${param.requestUri}?pageNo=${i}${param.conditions}'">${i}</button>
                    </c:if>
                </c:forEach>
            </div>

            <button class="btn btn-primary"  onclick="window.location.href='${param.requestUri}?pageNo=${param.nextPage}${param.conditions}'">下一页</button>
        </div>
    </div>
</div>


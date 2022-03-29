<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>

<div class="dropdown">
    <button class="dropbtn">
      <i class="fa fa-globe fa-lg"></i>
      <i class="fa fa-caret-down"></i>
    </button>
    <form method="post" action="">
        <div class="dropdown-content">
            <ul>
                <li>
                    <button class="en lang" type="submit" name="lang" value="en_US">
                        <span class="lang-text">
                            <fmt:message key="label.lang.en" />
                        </span>
                    </button>
                </li>
                <li>
                    <button class="ru lang" type="submit" name="lang" value="ru_RU">
                        <span class="lang-text">
                            <fmt:message key="label.lang.ru" />
                        </span>
                    </button>
                </li>
                <li>
                    <button class="by lang" type="submit" name="lang" value="by_BE">
                        <span class="lang-text">
                            <fmt:message key="label.lang.by" />
                        </span>
                    </button>
                </li>
            </ul>
        </div>
    </form>
</div>
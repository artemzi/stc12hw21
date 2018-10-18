<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%--<c:set var="error" value=""/>--%>
<%--<%--%>
<%--if (session.getAttribute("errorMessage") != null) {--%>
<%--pageContext.setAttribute("error", session.getAttribute("errorMessage"));--%>
<%--session.removeAttribute("errorMessage");--%>
<%--}--%>
<%--%>--%>

<t:default>
    <jsp:attribute name="scripts">
        <script type="application/javascript">
            $('#password, #confirm_password').on('keyup', function () {
                if ($('#password').val() == $('#confirm_password').val()) {
                    $('#password-message').html('').css('color', 'green');
                } else
                    $('#password-message').html('Passwords Not Matching').css('color', 'red');
            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <form class="form-horizontal" role="form" method="POST" action="/user/register">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <h2>Please fill all fields</h2>
                    <c:choose>
                        <c:when test="${sessionScope.errorMessage != null}">
                            <div class="alert alert-danger" role="alert">
                                <c:out value="${sessionScope.errorMessage}" />
                                <c:remove var="errorMessage" scope="session" />
                            </div>
                        </c:when>
                        <%--<c:otherwise>--%>
                        <%--</c:otherwise>--%>
                    </c:choose>
                    <hr>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="name">Your Name</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-user"></span></div>
                            <input type="text" name="name" class="form-control" id="name"
                                   placeholder="Your name" required autofocus>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                            <span class="text-danger align-middle">
                                <!-- name error message here -->
                            </span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="email">E-Mail Address</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-at"></span></div>
                            <input type="text" name="email" class="form-control" id="email"
                                   placeholder="you@example.com" required>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                            <span class="text-danger align-middle">
                                <!-- email error message here -->
                            </span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="sr-only" for="password">Password</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-key"></span></div>
                            <input type="password" name="password" class="form-control" id="password"
                                   placeholder="Password" required>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                            <span class="text-danger align-middle">
                            <!-- password error message here -->
                            </span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="sr-only" for="confirm_password">Password</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-key"></span></div>
                            <input type="password" name="confirm_password" class="form-control" id="confirm_password"
                                   placeholder="Confirm Password" required>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                            <span class="text-danger align-middle" id='password-message'>
                            <!-- password error message here -->
                            </span>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="sr-only" for="role">Choose role</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><span class="fa fa-user-circle"></span></div>
                            <select class="form-control" id="role" name="role"> <%-- TODO: remove hardcoded options; use roles records from database --%>
                                <option value="2">User</option>
                                <option value="1">Teacher</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-3"></div>
            </div>
            <div class="row" style="padding-top: 1rem">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <button type="submit" class="btn btn-success"><span class="fa fa-sign-in"></span> Register</button>
                    <a class="btn btn-link" href="/user/login">Back to Login page</a>
                </div>
            </div>
        </form>
    </jsp:body>

</t:default>
package com.github.artemzi.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TestLoginServlet {
    @Mock private HttpSession session;
    @Mock private RequestDispatcher dispatcher;
    private LoginServlet srv;

    @BeforeEach void setUp() {
        MockitoAnnotations.initMocks(this);
        srv = new com.github.artemzi.controllers.LoginServlet();
    }

    @Test void testDoGet() {
        srv = Mockito.spy(srv);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("action")).thenReturn("logout");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);

        srv.doGet(request, response);
        verify(request, atLeast(1)).getParameter("action");
        verify(srv).doGet(request, response);

        ArgumentCaptor<String> dispatcherArgument = ArgumentCaptor.forClass(String.class);
        verify(request).getRequestDispatcher(dispatcherArgument.capture());
        assertEquals("/login.jsp", dispatcherArgument.getValue());
    }

    @Test void testDoPost() {
        srv = Mockito.spy(srv);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("email")).thenReturn("demo@example.com");
        when(request.getParameter("password")).thenReturn("secret");
        when(request.getSession()).thenReturn(session);

        srv.doPost(request, response);
        verify(request, atLeast(1)).getParameter("email");
        verify(request, atLeast(1)).getParameter("password");
        verify(srv).doPost(request, response);
    }
}

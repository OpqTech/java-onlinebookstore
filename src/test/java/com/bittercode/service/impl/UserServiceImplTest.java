package com.bittercode.service.impl;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import com.bittercode.model.StoreException;
import com.bittercode.model.UserRole;
import com.bittercode.util.ConnectionProvider;

public class UserServiceImplTest {
    private ConnectionProvider mockProvider;
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;
    private HttpSession mockSession;

    @Before
    public void setUp() throws Exception {
        mockProvider = mock(ConnectionProvider.class);
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        mockSession = mock(HttpSession.class);
        when(mockProvider.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false); // Simulate no result
    }

    @Test
    public void testLogin_InvalidCredentials_ReturnsNull() throws StoreException, Exception {
        UserServiceImpl userService = new UserServiceImpl(mockProvider);
        assertNull(userService.login(UserRole.CUSTOMER, "invalid@example.com", "wrongpassword", mockSession));
    }
} 
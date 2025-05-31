package com.bittercode.service.impl;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Before;
import org.junit.Test;

import com.bittercode.model.StoreException;
import com.bittercode.util.ConnectionProvider;

public class BookServiceImplTest {
    private ConnectionProvider mockProvider;
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;

    @Before
    public void setUp() throws Exception {
        mockProvider = mock(ConnectionProvider.class);
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        when(mockProvider.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false); // Simulate no result
    }

    @Test
    public void testGetBookById_NotFound() throws StoreException, Exception {
        BookServiceImpl bookService = new BookServiceImpl(mockProvider);
        assertNull(bookService.getBookById("nonexistent-id"));
    }
} 
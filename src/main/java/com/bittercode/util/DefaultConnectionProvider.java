package com.bittercode.util;

import java.sql.Connection;
import com.bittercode.model.StoreException;

public class DefaultConnectionProvider implements ConnectionProvider {
    @Override
    public Connection getConnection() throws StoreException {
        return DBUtil.getConnection();
    }
} 
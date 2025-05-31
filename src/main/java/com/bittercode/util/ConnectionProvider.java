package com.bittercode.util;

import java.sql.Connection;
import com.bittercode.model.StoreException;

public interface ConnectionProvider {
    Connection getConnection() throws StoreException;
} 
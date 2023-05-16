package com.llw.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class JdbcUtils {

    /**
     * 数据库连接池对象 DataSource
     */
    private static DataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties属性配置文件
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            // 从流中加载数据
            properties.load(inputStream);
            // 创建 数据库连接 池
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据库连接池中的数据库连接，并且将连接放到ThreadLocal conns中，设置事务开启
     *
     * @return
     */
    public static Connection getConnection() {
        Connection conn = conns.get();
        if (conn == null) {
            try {
                //从数据库连接池中获取连接
                conn = dataSource.getConnection();
                //将连接对象存放到ThreadLocal中，供后面的jdbc使用
                conns.set(conn);
                //设置数据库连接对象的事务为手动管理，取消自动提交事务
                conn.setAutoCommit(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务并且关闭连接
     */
    public static void commitAndClose(){
        Connection conn = conns.get();
        if (conn != null) {  //如果不为空，则表示之前操作过数据库
            try {
                //提交事务
                conn.commit();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            } finally {
                try { // 关闭连接
                    conn.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        // 销毁当前ThreadLocal，否则会内存溢出
        conns.remove();
    }

    /**
     * 回滚事务并且关闭连接
     */
    public static void RollbackAndClose(){
        Connection conn = conns.get();
        if (conn != null) {  //如果不为空，则表示之前操作过数据库
            try {
                //回滚事务
                conn.rollback();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            } finally {
                try { // 关闭连接
                    conn.close();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        // 销毁当前ThreadLocal，否则会内存溢出
        conns.remove();
    }


    /**
     * 关闭连接，放回到数据库连接池
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }*/

}

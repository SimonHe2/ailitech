package com.github.ailitech.rest.dal.config;

import lombok.Data;

@Data
public abstract class BaseDruidConfig extends BaseConfig {
    private String name;
    private String url;
    private String userName;
    private String password;
    /**
     * 最大连接池数量
     */
    private Integer maxActive;
    /**
     * 初始化时建立物理连接的个数
     */
    private Integer initialSize;
    /**
     * 获取连接时最大等待时间
     */
    private Integer maxWait;
    /**
     * 最小连接池数量
     */
    private Integer minIdle;
    /**
     * 有两个含义：
     1) Destroy线程会检测连接的间隔时间，如果连接空闲时间大于等于minEvictableIdleTimeMillis则关闭物理连接。
     2) testWhileIdle的判断依据，详细看testWhileIdle属性的说明
     */
    private Integer timeBetweenEvictionRunsMillis;
    /**
     * 连接保持空闲而不被驱逐的最小时间
     */
    private Integer minEvictableIdleTimeMillis;
    /**
     * 建议配置为true，不影响性能，并且保证安全性。
     * 申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，
     * 执行validationQuery检测连接是否有效。
     */
    private Boolean testWhileIdle;
    /**
     * 申请连接时执行validationQuery检测连接是否有效
     */
    private Boolean testOnBorrow;
    /**
     * 归还连接时执行validationQuery检测连接是否有效
     */
    private Boolean testOnReturn;
    /**
     * 是否缓存preparedStatement,mysql下建议关闭
     */
    private Boolean poolPreparedStatements;
    private Integer maxOpenPreparedStatements;
    private Boolean asyncInit;
    /**
     * 用来检测连接是否有效的sql
     */
    private String validationQuery;
    /**
     * 单位：秒，检测连接是否有效的超时时间
     */
    private Integer validationQueryTimeout;
    /**
     * 连接池中的minIdle数量以内的连接，
     * 空闲时间超过minEvictableIdleTimeMillis，则会执行keepAlive操作。
     */
    private Boolean keepAlive;
    /**
     * 通过别名的方式配置扩展插件
     */
    private String filters;
    /**
    *物理连接初始化的时候执行的sql
     */
    private String connectionInitSqls;

}

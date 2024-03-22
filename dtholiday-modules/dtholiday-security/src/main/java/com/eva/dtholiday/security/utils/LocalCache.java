package com.eva.dtholiday.security.utils;

import com.eva.dtholiday.security.entity.DtHolidayUser;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/3/22 6:47
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/

public class LocalCache {

    /**
     * token-userId cache
     */
    private static Cache<String, DtHolidayUser> tokenUserIdCache = Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterAccess(2, TimeUnit.HOURS)
            .build();


    public static DtHolidayUser getUserIdByToken(String token) {
        return tokenUserIdCache.getIfPresent(token);
    }

    public static void putTokenUserIdCache(String token, DtHolidayUser tUser) {
        tokenUserIdCache.put(token, tUser);
    }
}

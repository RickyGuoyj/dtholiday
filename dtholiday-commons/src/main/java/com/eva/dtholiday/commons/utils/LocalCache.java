package com.eva.dtholiday.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @describtion
 * @copyright Copyright: 2015-2025
 * @creator guoyongjie
 * @create-time 2024/5/13 8:11
 * @department evangelion
 * @modificationHistory=========================逻辑或功能性重大变更记录
 * @modify by user :{修改人} :{修改时间}
 * @modify by reason :{原因}
 **/

public class LocalCache {

    /**
     * 岛屿编码和名称的缓存对
     */
    private static Map<Integer, String> islandNameMap = new ConcurrentHashMap<>();

    private static Map<Integer, String> islandTagNameMap = new ConcurrentHashMap<>();

    public static void putIslandName(Integer islandIndexCode, String islandCnName) {
        islandNameMap.put(islandIndexCode, islandCnName);
    }

    public static String getIslandName(Integer islandIndexCode) {
        return islandNameMap.get(islandIndexCode);
    }

    public static List<String> getTagNamesByTagIndexCodes(List<Integer> tagIndexCodeList) {
        // 根据编码列表从map中获取批量名称
        List<String> tagNames = new ArrayList<>();
        tagIndexCodeList.forEach(tagIndexCode -> {
            String tagName = islandTagNameMap.get(tagIndexCode);
            if (tagName != null) {
                tagNames.add(tagName);
            }
        });
        return tagNames;
    }

    public static void putIslandTagName(Integer tagIndexCode, String tagName) {
        islandTagNameMap.put(tagIndexCode, tagName);
    }

    public List<String> getAllIslandNames() {
        return new ArrayList<>(islandNameMap.values());
    }

    public static void deleteIslandName(Integer islandIndexCode) {
        islandNameMap.remove(islandIndexCode);
    }


}

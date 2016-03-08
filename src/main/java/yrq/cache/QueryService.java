package yrq.cache;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by renqun.yuan on 2016/3/8.
 */
public class QueryService {
    private static final Logger logger = LoggerFactory.getLogger(QueryService.class);

    /**
     * mock 获取数据的服务，将获取的结果放入到guava cache
     *
     * @param key
     * @return
     */
    public static String getValueByKey(String key) {
        logger.info("query from service, param:{}", key);
        return RandomStringUtils.randomNumeric(key.length());
    }
}

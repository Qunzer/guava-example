package yrq.cache;

import com.google.common.cache.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by renqun.yuan on 2016/3/8.
 */
public class CacheExample {
    private static final Logger logger = LoggerFactory.getLogger(CacheExample.class);

    // 具有统一的获取数据的方式， 一般会使用这个
    private static final LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.HOURS).recordStats().build(new CacheLoader<String, String>() {
        @Override
        public String load(String key) throws Exception {
            // 调用获取数据的方式
            return QueryService.getValueByKey(key);
        }
    });

    // 不具有统一获取数据的方式, 在get数据时，不同的key，可能使用不同的callable
    private static final Cache<String, String> cache = CacheBuilder.newBuilder().initialCapacity(2).maximumSize(2).build();

    public static void main(String[] args) throws ExecutionException {
        String guava = loadingCache.getUnchecked("guava");
        logger.info("guava value：{}", guava);
        loadingCache.getUnchecked("guava");
        loadingCache.getUnchecked("git");
        CacheStats stats = loadingCache.stats();
        logger.info("hit count:{}, ", stats.hitCount());
        logger.info(" hit rate:{}", stats.hitRate());
    }
}

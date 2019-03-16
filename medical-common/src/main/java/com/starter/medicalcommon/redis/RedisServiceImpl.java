package com.starter.medicalcommon.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 此类的描述是：
 *
 * @author Starter
 * @date 2019-03-16 16:32
 **/
@Service
@Slf4j
@SuppressWarnings("unchecked")
public class RedisServiceImpl implements IRedisService {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void save(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void saveAndExist(String key, Object value, Long num, TimeUnit timeUnit) {
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            redisTemplate.opsForValue().getOperations().expire(key, num, timeUnit);
        } else {
            long expire = redisTemplate.opsForValue().getOperations().getExpire(key, timeUnit);
            redisTemplate.opsForValue().set(key, value);
            redisTemplate.opsForValue().getOperations().expire(key, expire, timeUnit);
        }
    }

    @Override
    public void save(String key, Object value, Date expireDate) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.opsForValue().getOperations().expireAt(key, expireDate);
    }

    @Override
    public void save(String key, Object value, Long num, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, num, timeUnit);
    }

    @Override
    public void multiSave(Map<String, Object> mapData, Date expireDate) {
        redisTemplate.opsForValue().multiSet(mapData);
        mapData.forEach((key, value) -> redisTemplate.opsForValue().getOperations().expireAt(key, expireDate));
    }

    @Override
    public void setExpireAt(String key, Date expireDate) {
        redisTemplate.opsForValue().getOperations().expireAt(key, expireDate);
    }

    @Override
    public void setExpire(String key, Long num, TimeUnit timeUnit) {
        redisTemplate.opsForValue().getOperations().expire(key, num, timeUnit);
    }

    @Override
    public Object find(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public List<Object> multiFind(List<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public List<Object> findAll(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    @Override
    public Long increment(String key, Long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Long incrementAndExist(String key, Long delta, Long num, TimeUnit timeUnit) {
        Long resultTemp = redisTemplate.opsForValue().increment(key, delta);
        if (1L == resultTemp) {
            redisTemplate.opsForValue().getOperations().expire(key, num, timeUnit);
        }
        return resultTemp;
    }

    @Override
    public boolean isMemberOfSet(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    @Override
    public void addSetAndExist(String key, Object value, Long num, TimeUnit timeUnit) {
        if (redisTemplate.opsForSet().size(key) > 0) {
            redisTemplate.opsForSet().add(key, value);
        } else {
            redisTemplate.opsForSet().add(key, value);
            if (num > 0) {
                redisTemplate.opsForSet().getOperations().expire(key, num, timeUnit);
            }
        }
    }

    @Override
    public Set<String> findSetMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public void addZSetAndExist(String key, Object value, double score, Long num, TimeUnit timeUnit) {
        if (redisTemplate.opsForZSet().size(key) > 0) {
            redisTemplate.opsForZSet().incrementScore(key, value, score);
        } else {
            redisTemplate.opsForZSet().add(key, value, score);
            redisTemplate.opsForSet().getOperations().expire(key, num, timeUnit);
        }
    }

    @Override
    public List<ZSetOperations.TypedTuple<Object>> findZSetReverseRangeWithScores(String key, long start, long end) {
        return (List<ZSetOperations.TypedTuple<Object>>) redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end).stream().collect(Collectors.toList());
    }

    @Override
    public Double findZSetScore(String key, Object value) {
        //第三包里面如果指定的key找不到，会抛出NPM，此处做处理
        double n;
        try {
            n = redisTemplate.opsForZSet().score(key, value);
        } catch (NullPointerException e) {
            n = 0D;
        }
        return n;
    }

    @Override
    public Long countZSet(String key) {
        return redisTemplate.opsForZSet().zCard(key);
    }

    @Override
    public void addHashAndExist(String hashKey, String key, Object value, Long num, TimeUnit timeUnit) {
        if (redisTemplate.opsForHash().size(hashKey) > 0) {
            redisTemplate.opsForHash().put(hashKey, key, value);
        } else {
            redisTemplate.opsForHash().put(hashKey, key, value);
            if (num > 0) {
                redisTemplate.opsForHash().getOperations().expire(hashKey, num, timeUnit);
            }
        }
    }

    @Override
    public Object findHash(String hashKey, String key) {
        return redisTemplate.opsForHash().get(hashKey, key);
    }

    @Override
    public List<Object> findHashAll(String hashKey) {
        return redisTemplate.opsForHash().values(hashKey);
    }

    @Override
    public void addList(String key, Object value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public void addHash(String hashKey, String key, Object value, Date expireDate) {
        redisTemplate.opsForHash().put(hashKey, key, value);
        redisTemplate.opsForHash().getOperations().expireAt(hashKey, expireDate);
    }

    @Override
    public void addHash(String hashKey, String key, Object value, Long num, TimeUnit timeUnit) {
        redisTemplate.opsForHash().put(hashKey, key, value);
        redisTemplate.opsForHash().getOperations().expire(hashKey, num, timeUnit);
    }

    @Override
    public void addHash(String hashKey, String key, Object value) {
        redisTemplate.opsForHash().put(hashKey, key, value);
    }

    @Override
    public void deleteHash(String hashKey, List<String> keys, Date expireDate) {
        redisTemplate.opsForHash().delete(hashKey, keys.toArray());
        redisTemplate.opsForHash().getOperations().expireAt(hashKey, expireDate);
    }

    @Override
    public void deleteHash(String hashKey, List<String> keys, Long num, TimeUnit timeUnit) {
        redisTemplate.opsForHash().delete(hashKey, keys.toArray());
        redisTemplate.opsForHash().getOperations().expire(hashKey, num, timeUnit);
    }

    @Override
    public void deleteHash(String hashKey, List<String> keys) {
        redisTemplate.opsForHash().delete(hashKey, keys.toArray());
    }

    @Override
    public void addList(String key, Object value, Long num, TimeUnit timeUnit) {
        redisTemplate.opsForList().leftPush(key, value);
        redisTemplate.opsForList().getOperations().expire(key, num, timeUnit);
    }

    @Override
    public boolean isRedisLockFree(String key, Long ms) {
        if (StringUtils.isEmpty(key)) {
            log.error("Redis key is empty");
            return false;
        }

        Long timestamp = getRedisTime();

        try {
            //如果不存在key值则设置，并获取锁
            boolean getLock = (boolean) redisTemplate.execute((RedisCallback<Boolean>) redisConnection ->
                    redisConnection.setNX(key.getBytes(), redisTemplate.getStringSerializer().serialize(timestamp.toString())));

            if (getLock) {
                log.info("Did get redis lock : Init lock value :" + key + " " + timestamp);
                return true;
            }

            //获取当前的锁值
            String currentValue = redisTemplate.opsForValue().get(key).toString();

            //如果锁的时间值不为空且距离当前已经一段时间，则尝试获得锁并更新时间
            if ((!StringUtils.isEmpty(currentValue)) && (Long.parseLong(currentValue) + ms) < timestamp) {
                //同步操作，获取旧的锁值
                String oldValue = (String) redisTemplate.opsForValue().getAndSet(key, timestamp.toString());

                //多线程并发时，只有一条线程满足条件
                if ((!StringUtils.isEmpty(oldValue)) && oldValue.equals(currentValue)) {
                    log.info("Did get redis lock : Update lock value :" + key + " " + timestamp);
                    return true;
                }
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }

        log.info("Failed to get redis lock: " + key);
        return false;
    }

    private Long getRedisTime() {
        return (Long) redisTemplate.execute(RedisServerCommands::time);
    }
}
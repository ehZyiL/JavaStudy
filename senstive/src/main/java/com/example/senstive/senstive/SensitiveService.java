package com.example.senstive.senstive;

import com.github.houbb.sensitive.word.api.IWordAllow;
import com.github.houbb.sensitive.word.api.IWordDeny;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllowSystem;
import com.github.houbb.sensitive.word.support.deny.WordDenySystem;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class SensitiveService {
    /**
     * 敏感词命中计数统计
     */
    private static final String SENSITIVE_WORD_CNT_PREFIX = "sensitive_word";
    private volatile SensitiveWordBs sensitiveWordBs;
    @Autowired
    private SensitiveProperty sensitiveConfig;

    @PostConstruct
    public void refresh() {
        IWordDeny deny = () -> {
            List<String> sub = WordDenySystem.getInstance().deny();
            sub.addAll(sensitiveConfig.getDeny());
            return sub;
        };

        IWordAllow allow = () -> {
            List<String> sub = WordAllowSystem.getInstance().allow();
            sub.addAll(sensitiveConfig.getAllow());
            return sub;
        };
        sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordDeny(deny)
                .wordAllow(allow)
                .init();
        log.info("敏感词初始化完成!");
    }

    /**
     * 判断是否包含敏感词
     *
     * @param txt 需要校验的文本
     * @return 返回命中的敏感词
     */
    public List<String> contains(String txt) {
        if (!BooleanUtils.isTrue(sensitiveConfig.getEnable())) {
            return Collections.emptyList();
        }

        List<String> ans = sensitiveWordBs.findAll(txt);
        if (CollectionUtils.isEmpty(ans)) {
            return ans;
        }

        return ans;
    }


    /**
     * 敏感词替换
     *
     * @param txt
     * @return
     */
    public String replace(String txt) {
        if (BooleanUtils.isTrue(sensitiveConfig.getEnable())) {
            return sensitiveWordBs.replace(txt);
        }
        return txt;
    }

    /**
     * 查询文本中所有命中的敏感词
     *
     * @param txt 校验文本
     * @return 命中的敏感词
     */
    public List<String> findAll(String txt) {
        return sensitiveWordBs.findAll(txt);
    }
}

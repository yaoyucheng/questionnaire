package com.yyc.questionnaire;

import cn.hutool.http.HttpUtil;
import com.alibaba.cola.dto.MultiResponse;
import com.google.gson.reflect.TypeToken;
import com.yyc.api.QuestionnaireServiceI;
import com.yyc.config.WechatConfig;
import com.yyc.domain.utils.JsonUtils;
import com.yyc.dto.QuestionnaireInsertCmd;
import com.yyc.dto.QuestionnaireQry;
import com.yyc.dto.QuestionnaireReportCmd;
import com.yyc.dto.data.QuestionnaireDTO;
import com.yyc.questionnaire.executor.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuchengyao
 */
@Slf4j
@Service
public class QuestionnaireServiceImpl implements QuestionnaireServiceI {

    @Resource
    private QuestionnaireInsertExe questionnaireInsertExe;

    @Resource
    private QuestionnairesListExe questionnairesListExe;

    @Resource
    private QuestionnaireGetExe questionnaireGetExe;

    @Resource
    private QuestionnaireDeactivateExe questionnaireDeactivateExe;

    @Resource
    private QuestionnaireReportExe questionnaireReportExe;

    @Resource
    private QuestionnaireReportCheckExe questionnaireReportCheckExe;

    @Resource
    private WechatConfig wechatConfig;

    @Transactional
    @Override
    public void insert(@NonNull QuestionnaireInsertCmd questionnaireInsertCmd) {
        questionnaireInsertExe.insert(questionnaireInsertCmd);
    }

    @Override
    public MultiResponse<QuestionnaireDTO> listQuestionnaires(@NonNull QuestionnaireQry questionnaireInsertQry) {
        return questionnairesListExe.listQuestionnaires(questionnaireInsertQry);
    }

    @Override
    public QuestionnaireDTO getQuestionnaire(@NonNull String questionnaireCode) {
        return questionnaireGetExe.getQuestionnaire(questionnaireCode);
    }

    @Override
    public void deactivateQuestionnaire(@NonNull String questionnaireCode) {
        questionnaireDeactivateExe.deactivateQuestionnaire(questionnaireCode);
    }

    @Override
    @Transactional
    public void reportQuestionnaire(@NonNull QuestionnaireReportCmd questionnaireReportCmd) {
        questionnaireReportExe.reportQuestionnaire(questionnaireReportCmd);
    }

    @Override
    public void checkReportQuestionnaire(@NonNull String questionnaireCode) {
        questionnaireReportCheckExe.checkQuestionnaire(questionnaireCode);
    }

    @Override
    public void shareQuestionnaire(String scene) {

        String accessToken = getAccessToken();

        Map<String, Object> data = new HashMap<>();

        data.put("access_token", accessToken);
        data.put("scene", scene);
        data.put("page", "pages/detail/detail");

        String post = HttpUtil.post(WechatConfig.getUnlimitedUrl, data);
        
        System.out.println(post);
    }


    private String getAccessToken() {

        String formatUrl = String.format(WechatConfig.accessTokenUrl, wechatConfig.getAppID(), wechatConfig.getAppSecret());

        Type type = new TypeToken<Map<String, String>>() {
        }.getType();

        Map<String, String> parse = JsonUtils.parse(HttpUtil.get(formatUrl), type);

        return parse.get("access_token");
    }
}

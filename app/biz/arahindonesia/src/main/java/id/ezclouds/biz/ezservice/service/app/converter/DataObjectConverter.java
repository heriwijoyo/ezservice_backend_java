/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.app.converter;

import id.ezclouds.biz.ezservice.model.survey.QuestionForm;
import id.ezclouds.biz.ezservice.model.survey.ResponderForm;
import id.ezclouds.biz.ezservice.service.app.dataobject.BizSurveyQuestionDO;
import id.ezclouds.biz.ezservice.service.app.dataobject.BizSurveyResponderDO;
import id.ezclouds.common.util.StringUtil;

import java.util.Arrays;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: DataObjectConverter.java, v 0.1 2024‐02‐17 6:14 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public class DataObjectConverter {

    public static ResponderForm convert(BizSurveyResponderDO responderDO) {
        if (responderDO == null) { return null; }
        ResponderForm form = new ResponderForm();
        form.setId(responderDO.getId());
        form.setRequired(toBool(responderDO.getRequired()));
        form.setTypeCode(responderDO.getTypeCode());
        form.setLabel(responderDO.getLabel());
        form.setLabelError(responderDO.getLabelError());
        if (StringUtil.isNotBlank(responderDO.getOptions())) {
            form.setOptions(Arrays.asList(responderDO.getOptions().split(",")));
        }
        return form;
    }

    public static QuestionForm convert(BizSurveyQuestionDO questionDO) {
        if (questionDO == null) { return null; }
        QuestionForm questionForm = new QuestionForm();
        questionForm.setQid(questionDO.getQid());
        questionForm.setRequired(toBool(questionDO.getRequired()));
        questionForm.setTitle(questionDO.getTitle());
        questionForm.setMaxSelectionAllowed(questionDO.getMaxSelectionAllowed());
        questionForm.setHasOther(toBool(questionDO.getHasOther()));
        questionForm.setOtherRequiredCondition(questionDO.getOtherRequiredCondition());
        questionForm.setOtherHint(questionDO.getOtherHint());
        return questionForm;
    }

    private static boolean toBool(int state) {
        return state == 1;
    }
}
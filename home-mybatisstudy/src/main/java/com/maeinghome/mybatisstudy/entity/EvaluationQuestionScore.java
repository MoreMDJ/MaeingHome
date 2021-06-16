package com.maeinghome.mybatisstudy.entity;

import lombok.Data;

@Data
public class EvaluationQuestionScore {

	/**
     * 被测评人user_id,对应boc_baseuser表中的user_id
     */
    private Long baseuserId;

    /**
     * 测评id
     */
    private Long evaluationId;

    /**
     * 问题id,对应template_question表中的id
     */
    private Long questionId;

    /**
     * 他评总分
     */
    private Double othersScore;

    /**
     * 上级评价总分
     */
    private Double highLevelScore;

    /**
     * 同级评价总分
     */
    private Double sameLevelScore;

    /**
     * 下级评价总分
     */
    private Double lowerLevelScore;

    /**
     * 自评总分
     */
    private Double selfScore;

    /**
     * 参与计分的上级有效人数
     */
    private Integer highValidCount;

    /**
     * 参与计分的同级有效人数
     */
    private Integer sameValidCount;

    /**
     * 参与计分的下级有效人数
     */
    private Integer lowerValidCount;

	public EvaluationQuestionScore(Long baseuserId, Long evaluationId) {
		super();
		this.baseuserId = baseuserId;
		this.evaluationId = evaluationId;
		this.questionId = 0l;
		this.othersScore = 0d;
		this.highLevelScore = 0d;
		this.sameLevelScore = 0d;
		this.lowerLevelScore = 0d;
		this.selfScore = 0d;
		this.highValidCount = 0;
		this.sameValidCount = 0;
		this.lowerValidCount = 0;
	}

	public EvaluationQuestionScore() {
		super();
	}
}
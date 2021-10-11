package com.luongtx.oes.service;

import com.luongtx.oes.entity.Exam;
import com.luongtx.oes.entity.Question;

import java.util.List;

public interface ExamService extends BaseService<Exam> {
    List<Question> findQuestionsByExamId(Integer id);
    int findNumberOfQuestions(Integer id);
}

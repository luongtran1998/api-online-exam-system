package com.luongtx.oes.web;

import java.util.HashMap;
import java.util.Map;

import com.luongtx.oes.constants.PageConstants;
import com.luongtx.oes.dto.QuestionDTO;
import com.luongtx.oes.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/questions")
@Log4j2
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("")
    Map<String, Object> getAllExcludedCatalog(@RequestParam(value = "page", required = false) Integer page,
                                               @RequestParam(value = "size", required = false) Integer size,
                                               @RequestParam(value = "search", required = false, defaultValue = "") String searchKey,
                                               @RequestParam(value = "catalog") Long catalogId
                                               ) {

        Map<String, Object> response = new HashMap<>();
        if (page == null || size == null) {
            Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
            Page<QuestionDTO> questionDTOS = questionService.findAllExcluded(pageable, searchKey, catalogId);
            log.debug(questionDTOS.getContent());
            response.put(PageConstants.DATA, questionDTOS);
            response.put(PageConstants.TOTAL_PAGE, 1);
            return response;
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<QuestionDTO> questionDTOS = questionService.findAllExcluded(pageable, searchKey, catalogId);
        log.debug(questionDTOS.getContent());
        response.put(PageConstants.DATA, questionDTOS.getContent());
        response.put(PageConstants.TOTAL_PAGE, questionDTOS.getTotalPages());
        return response;
    }

    @PostMapping("/update")
    public void updateCatalogQuestion(@RequestBody QuestionDTO questionDTO) {
        log.debug(questionDTO);
        questionService.updateCatalogQuestion(questionDTO);
    }
}

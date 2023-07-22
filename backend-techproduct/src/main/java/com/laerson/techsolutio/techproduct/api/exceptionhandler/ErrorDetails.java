package com.laerson.techsolutio.techproduct.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetails {

    private Integer status;
    private String title;
    private OffsetDateTime time;
    private List<ProblemError> problemErrorList;

    public ErrorDetails(Integer status, String title, List<ProblemError> problemErrorList){
        this.status = status;
        this.title = title;
        this.problemErrorList = problemErrorList;
        time = OffsetDateTime.now();
    }

    public ErrorDetails(Integer status, String title){
        this.status = status;
        this.title = title;
        time = OffsetDateTime.now();
    }

    @AllArgsConstructor
    @Getter
    public static class ProblemError{

        private String field;
        private String messageUser;
        private String messageDev;
    }

}

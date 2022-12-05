package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor //final이 붙은 속성을 포함하는 생성자를 자동으로 생성하는 역할
@Controller
public class QuestionController {
    private final QuestionService questionService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList",questionList);
        return "question_list";
    }

    @RequestMapping(value="/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question",question);
        return "question_detail";
    }
    //요청 URL http://localhost:8080/question/detail/2의 숫자 2처럼 변하는 id값을 얻을 때에는 위와 같이 @PathVariable 애너테이션을 사용해야한다.
    //이 때 @RequestMapping(value = "/detail/{id}") 에서 사용한 id와 @PathVariable("id")의 매개변수 이름이 동일해야 한다.

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "question_form";
    }
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(),questionForm.getContent());
        return "redirect:/question/list"; // 질문 저장 후 질문 목록으로 이동
    }
}

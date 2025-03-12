package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.Common.EndPoint.EndPointConstant.COMMENTS;

@RestController
@RequestMapping(value = COMMENTS)
public class CommentController {

}

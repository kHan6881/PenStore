package com.example.penstore.controller;

import org.springframework.ui.Model;
import com.example.penstore.domain.Comment;
import com.example.penstore.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/comment/submit")
    public String commentPage(
            @RequestParam String goodsId,
            @RequestParam(required = false) String parentId,
            Model model
    ) {
        model.addAttribute("goodsId", goodsId);
        model.addAttribute("parentId", parentId);
        return "comment";
    }


//    @GetMapping("/goods/{goodsId}")
//    public ResponseEntity<List<Comment>> getCommentsByGoodsId(@PathVariable String goodsId) {
//        return ResponseEntity.ok(commentService.getCommentsByGoodsId(goodsId));
//    }
//
//    @GetMapping("/replies/{parentId}")
//    public ResponseEntity<List<Comment>> getRepliesByParentId(@PathVariable String parentId) {
//        return ResponseEntity.ok(commentService.getRepliesByParentId(parentId));
//    }
}
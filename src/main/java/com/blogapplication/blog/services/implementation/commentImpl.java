package com.blogapplication.blog.services.implementation;

import com.blogapplication.blog.entities.Comment;
import com.blogapplication.blog.entities.Post;
import com.blogapplication.blog.exceptions.ResourceNotFoundException;
import com.blogapplication.blog.payloads.commentDTO;
import com.blogapplication.blog.repositories.commentRepository;
import com.blogapplication.blog.repositories.postRepo;
import com.blogapplication.blog.services.commentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class commentImpl implements commentService {


    @Autowired
    private postRepo post_repo;


    @Autowired
    private commentRepository comment_repo;

     @Autowired
    private ModelMapper modelMapper;
    @Override
    public commentDTO addComment(commentDTO commentDTO, Integer postId) {
           Post receivedPost=this.post_repo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
           Comment comment=this.modelMapper.map(commentDTO,Comment.class);
           comment.setPost(receivedPost);
           //save in the DB
           Comment savedComment=this.comment_repo.save(comment);

           commentDTO savedCommentDTO = this.modelMapper.map(savedComment,commentDTO.class);
           return commentDTO;


    }

    @Override
    public void deleteComment(int commentId) {
           Comment comment = this.comment_repo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","commentID",commentId));
           this.comment_repo.delete(comment);

           
    }
}

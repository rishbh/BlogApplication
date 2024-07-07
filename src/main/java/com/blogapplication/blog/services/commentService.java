package com.blogapplication.blog.services;

import com.blogapplication.blog.payloads.commentDTO;

public interface commentService {

      public commentDTO addComment(commentDTO commentDTO, Integer postId);
      public void deleteComment(int commentId);
}

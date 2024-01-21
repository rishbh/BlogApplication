package com.blogapplication.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class categoryDTO {

     private Integer categoryId;
      private String title;
      private String description;
}

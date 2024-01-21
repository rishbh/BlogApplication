package com.blogapplication.blog.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{

    String resourceName;
    String fieldName;
    long  fieldValue;

    public ResourceNotFoundException(String resourceName,String fieldName,long fieldValue){
        super(String.format("Resource %s with fieldName %s and value %s not found",resourceName,fieldName,fieldValue));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }


}

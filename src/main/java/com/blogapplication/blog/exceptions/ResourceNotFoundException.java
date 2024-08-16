package com.blogapplication.blog.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Getter
@Setter
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
    Logger logger = LogManager.getLogger(ResourceNotFoundException.class);
    String resourceName;
    String fieldName;
    long  fieldValue;

    public ResourceNotFoundException(String resourceName,String fieldName,long fieldValue){
        super(String.format("Resource %s with fieldName %s and value %s not found",resourceName,fieldName,fieldValue));
        logger.error("Resource with resourcename "+resourceName+" and the fieldName "+fieldName+"with the value "+fieldValue+"can not found in the database");
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }


}

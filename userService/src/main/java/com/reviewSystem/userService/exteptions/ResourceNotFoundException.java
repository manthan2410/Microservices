package com.reviewSystem.userService.exteptions;

public class ResourceNotFoundException extends RuntimeException{

  public  ResourceNotFoundException()
    {
        super("User not found on server");
    }

    public ResourceNotFoundException(String message)
    {
        super(message);
    }
}

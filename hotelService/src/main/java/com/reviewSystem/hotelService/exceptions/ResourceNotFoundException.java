package com.reviewSystem.hotelService.exceptions;

public class ResourceNotFoundException extends RuntimeException {

  public  ResourceNotFoundException(){
        super("Hotel not found in DB");
    }

   public ResourceNotFoundException(String message)
    {
        super(message);
    }
}

package com.reservationapp.payload;

import javax.persistence.*;

public class UserRegistrationDto {
    private long id;
    private String name;
    private String email;
    private String password;
    @Lob
    @Column(name="profile_picture",length=1024)
    private byte[] profilePicture ;
}

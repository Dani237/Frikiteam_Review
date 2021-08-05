package com.frikiteam.review.resource;

import com.frikiteam.review.domain.model.User;
import lombok.Data;

@Data
public class CustomerResource extends User {
    private String nickName;
}

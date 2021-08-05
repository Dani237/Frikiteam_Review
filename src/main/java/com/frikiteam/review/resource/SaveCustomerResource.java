package com.frikiteam.review.resource;

import com.frikiteam.review.domain.model.User;
import lombok.Data;

@Data
public class SaveCustomerResource extends User {
    private String nickName;
}

package com.medical.services;

import com.medical.entity.ContactInfo;

import java.util.List;

public interface IContactInfoService {

    void createContactInfo(String email, String name, String phoneNumber, String comments);

    List<ContactInfo> getListContactInfos();

}

package com.medical.services.Impl;

import com.medical.entity.ContactInfo;
import com.medical.repositories.IContactInfoRepository;
import com.medical.services.IContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactInfoService implements IContactInfoService {

    @Autowired
    private IContactInfoRepository contactInfoRepository;

    @Override
    public void createContactInfo(String email, String name, String phoneNumber, String comments) {
        ContactInfo contactInfo = new ContactInfo(email, name, phoneNumber, comments);

        contactInfoRepository.save(contactInfo);
    }

    @Override
    public List<ContactInfo> getListContactInfos() {
        return contactInfoRepository.findAll();
    }
}

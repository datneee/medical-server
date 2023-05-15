package com.medical.repositories;

import com.medical.entity.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContactInfoRepository extends JpaRepository<ContactInfo, Integer> {
}

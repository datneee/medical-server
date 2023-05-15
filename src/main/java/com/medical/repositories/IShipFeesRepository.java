package com.medical.repositories;

import com.medical.entity.ShipFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShipFeesRepository extends JpaRepository<ShipFees, Integer> {

    boolean existsByVoucher(String voucher);
    ShipFees getByVoucher(String voucher);
}

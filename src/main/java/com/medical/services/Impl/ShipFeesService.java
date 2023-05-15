package com.medical.services.Impl;

import com.medical.entity.ShipFees;
import com.medical.repositories.IShipFeesRepository;
import com.medical.services.IShipFeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipFeesService implements IShipFeesService {

    @Autowired
    private IShipFeesRepository shipFeesRepository;

    @Override
    public List<ShipFees> getListShipFees() {
        return shipFeesRepository.findAll();
    }

    @Override
    public ShipFees createShipFee(String voucher, Integer fee) {
        ShipFees shipFees;
        if(shipFeesRepository.existsByVoucher(voucher)) {
            shipFees = shipFeesRepository.getByVoucher(voucher);
            shipFees.setFee(fee);
        } else {
            shipFees = new ShipFees(voucher, fee);
        }
        return shipFeesRepository.save(shipFees);
    }
}

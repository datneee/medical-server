package com.medical.services;

import com.medical.entity.ShipFees;

import java.util.List;

public interface IShipFeesService {

    List<ShipFees> getListShipFees();

    ShipFees createShipFee(String voucher, Integer fee);

}

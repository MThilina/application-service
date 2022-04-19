package com.thilinam.applicationservice.server.repository;


import com.thilinam.applicationservice.server.entity.DeviceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceDataRepository extends JpaRepository<DeviceData,Long> {
    List<DeviceData> findBySensorIdAndEventTimeStampGreaterThanEqualAndEventTimeStampLessThanEqual(String sensorId,
                                                                                              String endDate,
                                                                                              String StartDate);
}

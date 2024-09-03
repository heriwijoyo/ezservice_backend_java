/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.service;

import id.ezclouds.core.shared.converter.CoreModelConverter;
import id.ezclouds.core.shared.model.LegacyCoreArea;
import id.ezclouds.core.shared.repo.CoreAppDistrictRepository;
import id.ezclouds.core.shared.repo.CoreAppProvinceRepository;
import id.ezclouds.core.shared.repo.CoreAppRegencyRepository;
import id.ezclouds.core.shared.repo.CoreAppVillageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: LegacyCoreAreaService.java, v 0.1 2024‐02‐18 7:06 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class LegacyCoreAreaService {

    @Autowired
    private CoreAppProvinceRepository coreAppProvinceRepository;

    @Autowired
    private CoreAppRegencyRepository coreAppRegencyRepository;

    @Autowired
    private CoreAppDistrictRepository coreAppDistrictRepository;

    @Autowired
    private CoreAppVillageRepository coreAppVillageRepository;

    public List<LegacyCoreArea> getAllProvince() {
        return coreAppProvinceRepository
                .findAll()
                .stream()
                .map(CoreModelConverter::convert)
                .collect(Collectors.toList());
    }

    public List<LegacyCoreArea> getProvinceByIds(List<String> ids) {
        return coreAppProvinceRepository
                .findByIdIn(ids)
                .stream()
                .map(CoreModelConverter::convert)
                .collect(Collectors.toList());
    }

    public List<LegacyCoreArea> getRegencyByIds(List<String> ids) {
        return coreAppRegencyRepository
                .findByIdIn(ids)
                .stream()
                .map(CoreModelConverter::convert)
                .collect(Collectors.toList());
    }

    public List<LegacyCoreArea> getRegencyByProvinceIds(List<String> provinceIds) {
        return coreAppRegencyRepository
                .findByProvinceIdIn(provinceIds)
                .stream()
                .map(CoreModelConverter::convert)
                .collect(Collectors.toList());
    }

    public List<LegacyCoreArea> getDistrictByIds(List<String> ids) {
        return coreAppDistrictRepository
                .findByIdIn(ids)
                .stream()
                .map(CoreModelConverter::convert)
                .collect(Collectors.toList());
    }

    public List<LegacyCoreArea> getDistrictByRegencyIds(List<String> regencyIds) {
        return coreAppDistrictRepository
                .findByRegencyIdIn(regencyIds)
                .stream()
                .map(CoreModelConverter::convert)
                .collect(Collectors.toList());
    }

    public List<LegacyCoreArea> getVillageByIds(List<String> ids) {
        return coreAppVillageRepository
                .findByIdIn(ids)
                .stream()
                .map(CoreModelConverter::convert)
                .collect(Collectors.toList());
    }

    public List<LegacyCoreArea> getVillageByDistrictIds(List<String> districtIds) {
        return coreAppVillageRepository
                .findByDistrictIdIn(districtIds)
                .stream()
                .map(CoreModelConverter::convert)
                .collect(Collectors.toList());
    }
}
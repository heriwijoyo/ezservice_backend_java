/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.election.service.validation;

import id.ezclouds.common.facade.biz.data.BizMasterDataService;
import id.ezclouds.common.model.biz.data.VillageMasterData;
import id.ezclouds.common.util.assertion.AssertUtil;
import id.ezclouds.common.util.exception.EzErrorCode;
import id.ezclouds.common.util.exception.EzErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: PollStationValidationService.java, v 0.1 2024‐10‐11 1:25 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Service
public class PollStationValidationService {

    @Autowired
    private BizMasterDataService bizMasterDataService;

    public void validatePollStation(String orgId, String villageId, String pollStationId) {
        AssertUtil.notBlank(pollStationId, EzErrorCode.BIZ_POLL_STATION_INVALID);
        String trimmedPollStationId = pollStationId.replaceFirst("^0+(?!$)", "");
        int pollStationNumber = 0;
        try {
            pollStationNumber = Integer.parseInt(trimmedPollStationId);
        } catch (Exception e) {
            throw new EzErrorException(EzErrorCode.BIZ_POLL_STATION_INVALID);
        }
        AssertUtil.isTrue(pollStationNumber > 0, EzErrorCode.BIZ_POLL_STATION_INVALID);

        VillageMasterData masterData = bizMasterDataService.getVillageMasterDataById(orgId, villageId);
        AssertUtil.notNull(masterData, EzErrorCode.BIZ_POLL_STATION_INVALID);

        AssertUtil.isTrue(pollStationNumber <= masterData.getPollStationTotal(), EzErrorCode.BIZ_POLL_STATION_INVALID);
    }
}
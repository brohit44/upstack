package org.upgrad.upstac.testrequests.lab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.upgrad.upstac.testrequests.TestRequest;
import org.upgrad.upstac.users.User;

import javax.transaction.Transactional;

@Service
@Validated
public class LabResultService {


    @Autowired
    private LabResultRepository labResultRepository;


    private static Logger logger = LoggerFactory.getLogger(LabResultService.class);



    private LabResult createLabResult(User tester, TestRequest testRequest) {
        LabResult labResult=new LabResult();
        labResult.setTester(tester);
        labResult.setRequest(testRequest);
        return saveLabResult(labResult);
    }

    @Transactional
    LabResult saveLabResult(LabResult labResult) {
        return labResultRepository.save(labResult);
    }



    public LabResult assignForLabTest(TestRequest testRequest, User tester) {

        return createLabResult(tester, testRequest);


    }


    public LabResult updateLabTest(TestRequest testRequest, CreateLabResult createLabResult) {

        LabResult labResults=labResultRepository.findByRequest(testRequest).get();
        labResults.setBloodPressure(testRequest.getLabResult().getBloodPressure());
        labResults.setComments(testRequest.getLabResult().getComments());
        labResults.setHeartBeat(testRequest.getLabResult().getHeartBeat());
        labResults.setOxygenLevel(testRequest.getLabResult().getOxygenLevel());
        labResults.setTemperature(testRequest.getLabResult().getTemperature());
        labResults.setResult(testRequest.getLabResult().getResult());
        labResults.setUpdatedOn(testRequest.getLabResult().getUpdatedOn());

        return saveLabResult(labResults);


    }


}

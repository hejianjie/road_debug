package com.beyond.zjxt.modular.road.controller;

import com.beyond.zjxt.modular.road.entity.*;
import com.beyond.zjxt.modular.road.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/inspector")
public class InspectorController {

    @Autowired
    private VPatrolResultRepository vPatrolResultRepository;

    @Autowired
    private NationalHighwayRepository nationalHighwayRepository;

    @Autowired
    private RoadSectionRepository roadSectionRepository;

    @Autowired
    private IStakeRepository iStakeRepository;

    @Autowired
    private PatrolCarRepository patrolCarRepository;

    @Autowired
    private VNotificationRepository vNotificationRepository;

    @Autowired
    private IPatrolResultRepository iPatrolResultRepository;

    @Autowired
    private VAppraisalRepository vAppraisalRepository;

    @Autowired
    private VAcceptanceRepository vAcceptanceRepository;

    @Autowired
    private VAcceptanceDoneRepository vAcceptanceDoneRepository;

    @Autowired
    private VAppraisalDoneRepository vAppraisalDoneRepository;

    @Autowired
    private VHazardRepository vHazardRepository;

    @GetMapping("/view/patrol")
    public Map<String, Object> viewPatrol(@RequestParam("patrolResultId") Integer patrolResultId) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        map.put("data", vPatrolResultRepository.findById(patrolResultId));
        return map;
    }

    @GetMapping("/list/problems-condition")
    public Map<String, Object> viewProblem(@RequestParam("patrolResultId") Integer patrolResultId, @RequestParam("userId") Integer userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        PageRequest request = PageRequest.of(0, 50);
        map.put("problemCount", vHazardRepository.findAllByPatrolResultIdAndUserId(patrolResultId, userId, request).getTotalElements());
        List<VHazard> vHazards = new ArrayList<>();
        for (VHazard vHazard: vHazardRepository.findAllByPatrolResultIdAndUserId(patrolResultId, userId, request).getContent()) {
            vHazard.setSpecificSize(vHazard.getSpecificSize().replace(',', '*'));
            vHazards.add(vHazard);
        }
        map.put("data", vHazards);
        return map;
    }

    @GetMapping("/list/problems")
    public Map<String, Object> viewProblem(@RequestParam("userId") Integer userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        PageRequest request = PageRequest.of(0, 15, Sort.Direction.DESC, "detectTime");
        map.put("problemCount", vHazardRepository.findAllByUserId(userId, request).getTotalElements());
        map.put("data", vHazardRepository.findAllByUserId(userId, request).getContent());
        return map;
    }

    @GetMapping("/list/highway")
    public Map<String, Object> listHighway() {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        map.put("data", nationalHighwayRepository.findAll());
        return map;
    }

    @GetMapping("/list/road-section")
    public Map<String, Object> listRoadSection(@RequestParam("nationalHighwayId") Integer nationalHighwayId) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        map.put("data", roadSectionRepository.findAllByNationalHighwayId(nationalHighwayId));
        return map;
    }

    @GetMapping("/list/stake")
    public Map<String, Object> listStake() {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        map.put("data", iStakeRepository.findAll());
        return map;
    }

    @GetMapping("/list/car")
    public Map<String, Object> listCar(@RequestParam("deptId") Long deptId) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        map.put("data", patrolCarRepository.findAllByDeptId(deptId));
        return map;
    }

    @GetMapping("/list/notification")
    public Map<String, Object> listNotification() {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        PageRequest request = PageRequest.of(0, 15);
        map.put("data", vNotificationRepository.findAll(request));
        return map;
    }

    @GetMapping("/list/patrol")
    public Map<String, Object> listPatrol(@RequestParam("inspectorId") Integer inspectorId) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        PageRequest request = PageRequest.of(0, 15);
        map.put("data", vPatrolResultRepository.findAllByInspectorId(inspectorId, request));
        return map;
    }

    @PostMapping("/patrol/begin")
    public Map<String, Object> patrolBegin(IPatrolResult patrolResult) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        patrolResult.setBeginTime(new Date());
        map.put("data", iPatrolResultRepository.save(patrolResult));
        return map;
    }

    @PostMapping("/patrol/end")
    public Map<String, Object> patrolEnd(@RequestParam("patrolResultId") Integer patrolResultId) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        IPatrolResult iPatrolResult = iPatrolResultRepository.findById(patrolResultId).orElse(null);
        iPatrolResult.setEndTime(new Date());
        map.put("data", iPatrolResultRepository.save(iPatrolResult));
        return map;
    }

    @GetMapping("/patrol/daily")
    public Map<String, Object> patrolDaily(@RequestParam("inspectorId") Integer inspectorId) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        PageRequest request = PageRequest.of(0, 1);
        Page<VPatrolResult> page = vPatrolResultRepository.findAllByInspectorId(inspectorId, request);
        map.put("data", page.getContent());
        return map;
    }

    @GetMapping("/get/road-section")
    public Map<String, Object> getRoadSection(@RequestParam("patrolResultId") Integer patrolResultId) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        map.put("data", vPatrolResultRepository.findById(patrolResultId));
        return map;
    }

    @GetMapping("/patrol/query")
    public Map<String, Object> patrolQuery(@RequestParam("inspectorId") Integer inspectorId,
                                           @RequestParam(value = "highwayId", defaultValue = "") Integer highwayId,
                                           @RequestParam(value = "roadSectionId", defaultValue = "") Integer roadSectionId,
                                           @RequestParam(value = "stakeBeginId", defaultValue = "1") Integer stakeBeginId,
                                           @RequestParam(value = "stakeEndId", defaultValue = "99999") Integer stakeEndId,
                                           @RequestParam(value = "beginTime") String beginTime,
                                           @RequestParam("endTime") String endTime) {
        String iBeginTime = beginTime.substring(0, 19).replace('T',' ');
        String iEndTime = endTime.substring(0, 19).replace('T',' ');
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        map.put("totalElements", vPatrolResultRepository.findAllByParams(inspectorId, highwayId, roadSectionId, stakeBeginId, stakeEndId, iBeginTime, iEndTime).size());
        map.put("data", vPatrolResultRepository.findAllByParams(inspectorId, highwayId, roadSectionId, stakeBeginId, stakeEndId, iBeginTime, iEndTime));
        return map;
    }

    @GetMapping("/list/assessing-appraisal")
    public Map<String, Object> listAssessingAppraisal(@RequestParam("thirdPartyId") String thirdPartyId) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        List<VAppraisal> list = new ArrayList<>();
        for (VAppraisal appraisal : vAppraisalRepository.findAllByThirdPartyIdAndEvaluated(Long.parseLong(thirdPartyId), 0)) {
            appraisal.setSpecificSize(appraisal.getSpecificSize().replace(',', '*'));
            list.add(appraisal);
        }
        map.put("data", list);
        return map;
    }

    @GetMapping("/list/assessed-appraisal")
    public Map<String, Object> listAssessedAppraisal(@RequestParam("thirdPartyId") String thirdPartyId) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        map.put("evaluated", 1);
        List<VAppraisalDone> list = new ArrayList<>();
        for (VAppraisalDone appraisalDone : vAppraisalDoneRepository.findAllByThirdPartyId(Long.parseLong(thirdPartyId))) {
            appraisalDone.setSpecificSize(appraisalDone.getSpecificSize().replace(',', '*'));
            list.add(appraisalDone);
        }
        map.put("data", list);
        return map;
    }

    @GetMapping("/list/acceptance-ing")
    public Map<String, Object> listAcceptanceIng(@RequestParam("thirdPartyId") String thirdPartyId) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        List<VAcceptance> list = new ArrayList<>();
        for (VAcceptance acceptance : vAcceptanceRepository.findAllByAcceptOrganizationId(Long.parseLong(thirdPartyId))) {
            acceptance.setSpecificSize(acceptance.getSpecificSize().replace(',', '*'));
            list.add(acceptance);
        }
        map.put("data", list);
        return map;
    }

    @GetMapping("/list/acceptance-ed")
    public Map<String, Object> listAcceptanceEd(@RequestParam("thirdPartyId") String thirdPartyId) {
        Map<String, Object> map = new HashMap<>();
        map.put("statusCode", 1);
        map.put("msg", "SUCCESS");
        map.put("audited", 1);
        List<VAcceptanceDone> list = new ArrayList<>();
        for (VAcceptanceDone acceptanceDone : vAcceptanceDoneRepository.findAllByAcceptOrganizationId(Long.parseLong(thirdPartyId))) {
            acceptanceDone.setSpecificSize(acceptanceDone.getSpecificSize().replace(',', '*'));
            list.add(acceptanceDone);
        }
        map.put("data", list);
        return map;
    }
}

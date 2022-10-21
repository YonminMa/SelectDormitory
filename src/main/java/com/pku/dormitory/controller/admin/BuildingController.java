package com.pku.dormitory.controller.admin;

import com.pku.dormitory.domain.Building;
import com.pku.dormitory.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/building")
public class BuildingController {
    @Autowired
    BuildingService buildingService;

    @GetMapping()
    public List<Building> getAllBuildings() {
        List<Building> buildingList = buildingService.getAllBuildings();
        for (Building build :
                buildingList) {
            System.out.println(build.getNumber() + "," + build.getRest());
        }
        return buildingList;
    }

    @PostMapping("/save")
    public String saveBuilding(@RequestBody Building building) {
        buildingService.saveBuilding(building);
        return "保存building" + building.getNumber() + "成功";
    }

    @GetMapping("/{id}")
    public String checkRest(@PathVariable int id) {
        return buildingService.checkRest(id)+"";
    }

    @DeleteMapping("/{number}")
    public String deleteBuilding(@PathVariable int number){
        buildingService.deleteBuilding(number);
        return "删除building" + number + "成功";
    }
}

package co.ruppcstat.ecomercv1.ecomV1.feature.staff;

import co.ruppcstat.ecomercv1.ecomV1.deman.Staff;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffDTO.StaffCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffDTO.StaffResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffDTO.StaffUpdate;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.DeclareError;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/staffs")
public class StaffController {
    private final StaffService staffService;

    @PostMapping
    public StaffResponse createStaff(@RequestBody StaffCreate staffCreate) {
        return staffService.createStaff(staffCreate);
    }

    @GetMapping("/{phone}")
    public StaffResponse getStaff(@PathVariable String phone) {
        return staffService.getStaff(phone);
    }

    @GetMapping
    public Page<StaffResponse> getAllStaffs(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ) {
        return staffService.getAllStaff(pageNumber, pageSize);
    }

    @DeleteMapping("/{phone}")
    public void deleteStaff(@PathVariable String phone) {
        staffService.deleteStaff(phone);
    }

    @PatchMapping("/{phone}")
    public StaffResponse updateStaff(@PathVariable String phone, @RequestBody StaffUpdate staffUpdate) {
        return staffService.updateStaff(phone, staffUpdate);
    }
    @PatchMapping("/{phone}/stopWork")
    public StaffResponse stopWork(@PathVariable String phone) {
        return staffService.isStopWork(phone);
    }
}
